package com.qsanspack.qsandans.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.qsanspack.qsandans.entities.RegistrationDTO;
import com.qsanspack.qsandans.entities.User;

@Controller
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    
    // @GetMapping("/")
    // public String helloUser(){
    //     return "User level";
    // }

    @RequestMapping(value = "/register",method=RequestMethod.GET)
    public String register(@ModelAttribute(value="user") RegistrationDTO user){
        //model.addAttribute("user",new RegistrationDTO("", ""));
        //final String uri = "http://localhost:8080/auth/employees.xml";
       
        //System.out.println("USER --------  >>>>>" + user.getUsername());
        return "register";
    }

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public String login(@ModelAttribute(value="user") RegistrationDTO user){
        //model.addAttribute("user",new RegistrationDTO("", ""));
        //final String uri = "http://localhost:8080/auth/employees.xml";
       
        //System.out.println("USER --------  >>>>>" + user.getUsername());
        return "login";
    }

    @RequestMapping(value = "/home",method=RequestMethod.GET)
    public String home(){
        //model.addAttribute("user",new RegistrationDTO("", ""));
        //final String uri = "http://localhost:8080/auth/employees.xml";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
       // UserDetails details = (UserDetails) auth.getPrincipal();
       
        System.out.println("USER=====>" + auth.getName());
       
        return "home";
    }

     @RequestMapping(value = "/profile",method=RequestMethod.GET)
    public String createProfilePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
       // UserDetails details = (UserDetails) auth.getPrincipal();
        User user1 = (User) auth.getPrincipal();
        System.out.println(user1.getUsername());
        
      // model.addAttribute("username",username1);
        //final String uri = "http://localhost:8080/auth/employees.xml";
       
        return "profile";
    }

}
 