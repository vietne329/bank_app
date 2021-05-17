package com.bank_management.demo.respositories;

import com.bank_management.demo.common.UserRole;
import com.bank_management.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(UserRole name);
}
