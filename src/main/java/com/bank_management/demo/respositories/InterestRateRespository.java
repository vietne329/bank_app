package com.bank_management.demo.respositories;

import com.bank_management.demo.entities.Interestrate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRateRespository extends JpaRepository<Interestrate,Long> {

    List<Interestrate> findAll();
}
