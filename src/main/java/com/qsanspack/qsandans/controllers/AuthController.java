package com.qsanspack.qsandans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsanspack.qsandans.entities.LoginResponseDTO;
// import com.qsanspack.qsandans.entities.LoginResponseDTO;
import com.qsanspack.qsandans.entities.RegistrationDTO;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    

    @Autowired
    private AuthenticationService service;


    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return service.registerUser(body.getUsername(), body.getPassword());
    }
 

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
            return service.loginUser(body.getUsername(), body.getPassword());
    }

}
