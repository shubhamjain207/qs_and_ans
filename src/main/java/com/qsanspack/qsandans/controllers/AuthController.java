package com.qsanspack.qsandans.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.qsanspack.qsandans.entities.LoginResponseDTO;
// import com.qsanspack.qsandans.entities.LoginResponseDTO;
import com.qsanspack.qsandans.entities.RegistrationDTO;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.repos.UserRepo;
import com.qsanspack.qsandans.services.AuthenticationService;
import com.qsanspack.qsandans.services.JwtHelper;
import com.qsanspack.qsandans.services.JwtRequest;
import com.qsanspack.qsandans.services.JwtResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    

     @Autowired
    private UserRepo userRepo;

    // @Autowired
    // private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserDetailsService service1;


    @Autowired
    private AuthenticationService service;


    @PostMapping("/registeruser")
    public String registerUser(RegistrationDTO user){
        User u = service.registerUser(user.getUsername(), user.getPassword(),user.getProfileimage(),user.getFullname());

        return "redirect:/user/home?success";
    }

    @PostMapping("/profile")
    public void profile(){
        
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        User details = (User) authentication1.getPrincipal();

        System.out.println("==================>>>"+details.getUsername());
       


    }
    

    @PostMapping("/login")
    public  ResponseEntity<JwtResponse> loginUser(@ModelAttribute RegistrationDTO body){


        

            ResponseEntity<JwtResponse> user = service.login(body.getUsername(), body.getPassword());
             
            //    manager.authenticate(
                        
            //         new UsernamePasswordAuthenticationToken(body.getUsername(), "jain619"));

            //         UserDetails userDetails = service1.loadUserByUsername("shubham619");


            //         String token = this.helper.generateToken(userDetails);
                   
            //         JwtResponse response = JwtResponse.builder().JwtToken(token).username(userDetails.getUsername()).build();

                    
            //         return new ResponseEntity<>(response, HttpStatus.OK);

             
             
            //  if(!user.getUser().equals(null)){
            //     System.out.println(user.getUser());
            //     System.out.println(user.getJwt());

            //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
               

                return user;        
           

            
            
    }

  



}
