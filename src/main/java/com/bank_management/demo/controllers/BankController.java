package com.bank_management.demo.controllers;

import com.bank_management.demo.dto.BankNewForm;
import com.bank_management.demo.entities.BankSavingBook;
import com.bank_management.demo.entities.Interestrate;
import com.bank_management.demo.entities.User;
import com.bank_management.demo.service.BankService;
import com.bank_management.demo.service.InterestRateService;
import com.bank_management.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankService bankSer;

    @Autowired
    private UserService userSer;

    @Autowired
    private InterestRateService interestRateSer;

    @GetMapping(value = "/bank/{uid}", produces = "application/json")
    public ResponseEntity<?> getBankBook(@PathVariable(name = "uid") Long uid) {
        User user = userSer.getUserById(uid);

        if ( user == null) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        BankSavingBook bankSavingBook = bankSer.getBankSavingByUser(uid);

        return new ResponseEntity<>(bankSavingBook, HttpStatus.OK);
    }

    @GetMapping(value = "/bank/new", produces = "application/json")
    public ResponseEntity<?> getForm() {
        List<Interestrate> listInter = interestRateSer.listAll();
        if ( listInter == null) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        BankSavingBook bankSavingBook = new BankSavingBook();

        BankNewForm bankNewForm =new BankNewForm();

        bankNewForm.setBankSavingBook(bankSavingBook);
        bankNewForm.setInterestrateList(listInter);

        return new ResponseEntity<>(bankNewForm, HttpStatus.OK);
    }

    @PostMapping(value = "/bank/save", produces = "application/json")
    public ResponseEntity<?> createBank(@RequestBody BankSavingBook bankSavingBook) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = df.format(date);

        bankSavingBook.setStartDate(dateString);

        bankSer.createBankSaving(bankSavingBook);

        System.out.println(bankSavingBook.getUser().getId());
        User user = userSer.getUserById(bankSavingBook.getUser().getId());

        Double oldMoney = user.getMoney();
        user.setMoney(oldMoney - bankSavingBook.getMoney());

        userSer.saveUser(user);

        return new ResponseEntity<>(bankSavingBook, HttpStatus.OK);
    }

    @GetMapping(value = "/bank/moneycurrent/{uid}",produces = "application/json")
    public ResponseEntity<?> getMoneyCurrent(@PathVariable Long uid) throws ParseException {
//        Date date = new Date();
//        String time1 = "15/6/2021";

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time1 = dtf.format(now);
        System.out.println("Local date: " +  time1);

        BankSavingBook bankSavingBook = bankSer.getBankSavingByUser(uid);
        Date date1 = df.parse(bankSavingBook.getStartDate());
        System.out.println(date1.getTime());
        Date date2 = df.parse(time1);
        System.out.println(date2.getTime());

        int day = (int) ((date2.getTime() - date1.getTime()) / 86400000);
        System.out.println("Ngày tính ra: " + day);

        Double result = bankSer.WithdrawalMoney(bankSavingBook, day);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/bank/withdrawal/{uid}", produces = "application/json")
    public ResponseEntity<?> getWithdrawal(@PathVariable Long uid) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time1 = dtf.format(now);
        System.out.println("Local date: " +  time1);

        BankSavingBook bankSavingBook = bankSer.getBankSavingByUser(uid);
        Date date1 = df.parse(bankSavingBook.getStartDate());
        Date date2 = df.parse(time1);

        int day = (int) ((date2.getTime() - date1.getTime()) / 86400000);
        System.out.println("Ngày tính ra: " + day);

        Double result = bankSer.WithdrawalMoney(bankSavingBook, day);

        bankSer.removeBank(bankSavingBook.getId());

        User user = userSer.getUserById(uid);
        System.out.println(result);
        Double moneyUser = result + user.getMoney();
        user.setMoney(moneyUser);
        userSer.saveUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
