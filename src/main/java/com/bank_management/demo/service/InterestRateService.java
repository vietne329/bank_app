package com.bank_management.demo.service;

import com.bank_management.demo.entities.Interestrate;
import com.bank_management.demo.respositories.InterestRateRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestRateService {

    @Autowired
    private InterestRateRespository interestRateRespo;

    public List<Interestrate> listAll() {

        return interestRateRespo.findAll();
    }

    public Interestrate findById(Long id) {
        return interestRateRespo.findById(id).get();
    }
}
