package com.bank_management.demo.service;

import com.bank_management.demo.entities.User;
import com.bank_management.demo.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User getUserById(Long id) {

        return userRepo.findById(id).get();
    }

    public User saveUser(User user) {

        return userRepo.save(user);
    }

}
