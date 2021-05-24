package com.bank_management.demo.controllers;


import com.bank_management.demo.entities.User;
import com.bank_management.demo.respositories.UserRepository;
import com.bank_management.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userSer;
    @Autowired
    UserRepository userRepository;
    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> getUserById(@RequestParam("id") Long id){
        User user = userSer.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User _tutorial = userOptional.get();
            _tutorial.setFacebookId(user.getFacebookId());
            _tutorial.setRoles(user.getRoles());
            System.out.println(_tutorial.getRoles().toString());
            return new ResponseEntity<>(userRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
