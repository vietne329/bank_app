package com.bank_management.demo.dto;

import com.bank_management.demo.entities.BankSavingBook;
import com.bank_management.demo.entities.Interestrate;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BankNewForm {
    private BankSavingBook bankSavingBook;
    private List<Interestrate> interestrateList = new ArrayList<>();
}
