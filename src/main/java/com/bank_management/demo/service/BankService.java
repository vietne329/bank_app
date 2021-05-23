package com.bank_management.demo.service;

import com.bank_management.demo.entities.BankSavingBook;
import com.bank_management.demo.entities.Interestrate;
import com.bank_management.demo.respositories.BankRepository;
import com.bank_management.demo.respositories.InterestRateRespository;
import com.bank_management.demo.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private InterestRateRespository interestRateRespo;

    @Autowired
    private UserRepository userRepos;

    public void removeBank(Long id) {
        bankRepository.deleteById(id);
    }

    public Double WithdrawalMoney(BankSavingBook bankSavingBook, int day) {

        Interestrate interestrate = interestRateRespo.findById(bankSavingBook.getInterestrate().getId()).get();
        double laiSuat = interestrate.getInterestRate();
        int time = interestrate.getTimes();

        Double result = null;

        if (day == 0) {
            return bankSavingBook.getMoney();
        }
        else if (day < (time * 30)) {
            result = caculator(bankSavingBook.getMoney(), day, laiSuat);
        } else if (day == (30 * time)) {
            result = caculator(bankSavingBook.getMoney(), day, laiSuat);
        } else {
            Double kq1 = caculator(bankSavingBook.getMoney() , (30 * time) , laiSuat);
            Double kq2 = caculator(bankSavingBook.getMoney() , (day - (30 * time)) ,  (double)laiSuat/2);
            result = kq1 + kq2;
        }
        System.out.println(result);
        return result;
    }

    public Double getMoneyOnTime(BankSavingBook bankSavingBook) {

        Interestrate interestrate = interestRateRespo.findById(bankSavingBook.getInterestrate().getId()).get();
        double laiSuat = interestrate.getInterestRate();
        int time = interestrate.getTimes();
        int day = time*30;
        Double result = null;

        result = caculator(bankSavingBook.getMoney(), day, laiSuat);

        return result;
    }

    private Double caculator(Double bankMoney, int soNgay, Double laiSuat) {

        return (bankMoney * soNgay * laiSuat/100) / 365;
    }

    public BankSavingBook getBankSavingByUser(Long id) {

        return bankRepository.findByUserId(id);
    }

    public BankSavingBook createBankSaving(BankSavingBook bankSavingBook) {
        return bankRepository.save(bankSavingBook);
    }

    public BankSavingBook getBankSavingById(Long id) {
        return bankRepository.findById(id).get();
    }
}
