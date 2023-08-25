package com.qsanspack.qsandans.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.qsanspack.qsandans.entities.RegistrationDTO;
import com.qsanspack.qsandans.entities.User;

import com.qsanspack.qsandans.services.AuthenticationService;

import com.qsanspack.qsandans.services.JwtResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/registeruser")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> requestData) {
        

       ResponseEntity<User> user1 = service.registerUser(requestData.get("username"), requestData.get("password"),requestData.get("profileImage"), requestData.get("fullName"));

       //System.out.println(requestData);
         return user1 ;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody Map<String, String> requestData) {

        ResponseEntity<JwtResponse> user = service.login(requestData.get("username"), requestData.get("password"));
        return user;

    }

}
