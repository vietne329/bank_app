package com.bank_management.demo.respositories;

import com.bank_management.demo.entities.BankSavingBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankSavingBook,Long> {

    @Query("SELECT b FROM BankSavingBook b WHERE b.user.id = ?1")
    BankSavingBook findByUserId(Long id);
}
