package com.bank_management.demo.controllers;


import com.bank_management.demo.entities.User;
import com.bank_management.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userSer;

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> getUserById(@RequestParam("id") Long id){
        User user = userSer.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
