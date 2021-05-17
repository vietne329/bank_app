package com.bank_management.demo.respositories;

import com.bank_management.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("UPDATE User u SET u.money = ?1 WHERE u.id = ?2")
    @Modifying
    void updateMoney(Double money, Long id);
}
