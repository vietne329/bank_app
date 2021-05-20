package com.bank_management.demo.controllers;


import com.bank_management.demo.common.JwtUtils;
import com.bank_management.demo.common.UserRole;
import com.bank_management.demo.dto.JwtResponse;
import com.bank_management.demo.dto.LoginRequest;
import com.bank_management.demo.dto.MessageResponse;
import com.bank_management.demo.dto.SignupRequest;
import com.bank_management.demo.entities.Role;
import com.bank_management.demo.entities.User;
import com.bank_management.demo.respositories.RoleRepository;
import com.bank_management.demo.respositories.UserRepository;
import com.bank_management.demo.service.UserDetailsImpl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles  = userDetails.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),userDetails.getUsername(),
                userDetails.getEmail(),userDetails.getFullName(),
                userDetails.getAddress(),userDetails.getDob(),userDetails.getPhone(),userDetails.getMoney(),userDetails.getIdCard(),userDetails.getFacebookId(),roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody SignupRequest signupRequest){
        if(userRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signupRequest.getUsername(),encoder.encode(signupRequest.getPassword()),
                signupRequest.getFullName(),signupRequest.getAddress(),signupRequest.getDob(),signupRequest.getPhone(),
                signupRequest.getEmail(),signupRequest.getIdCard(),signupRequest.getFacebookId());
//        Set<String> strRoles= signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

//        if(strRoles == null){
            Role userRole = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
                    .orElseThrow(()->new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
//        }else {
//            strRoles.forEach(role -> {
//                switch (role){
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
//                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
//                        roles.add(adminRole);
//                        break;
//
//                    default:
//                        Role userRole = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
//                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
//                        roles.add(userRole);
//
//                }
//            } );
//        }
        user.setRoles(roles);
        user.setMoney(5000000.0);
        User u = userRepository.save(user);
        Set<Role> list = u.getRoles();
        String str ="";
        for (Role r : list) {
            System.out.println(r.getName());
            str = r.getName().toString();
        }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!" + str));
    }


}
