package com.bank_management.demo.respositories;

import com.bank_management.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.facebookId = ?1")
    User findByFacebookId(String id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByFacebookId(String id);

    @Query("UPDATE User u SET u.money = ?1 WHERE u.id = ?2")
    @Modifying
    void updateMoney(Double money, Long id);
}
