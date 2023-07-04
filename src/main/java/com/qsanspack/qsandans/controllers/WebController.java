package com.qsanspack.qsandans.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.userrepo.UserRepo;


@Controller
public class WebController {
    
    @Autowired
    private UserRepo repo;

    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    

    @RequestMapping(value = "/Register_user", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result){
        
        if(result.hasErrors()){
            System.out.println(result);
            return "register";
        }
       
       try{
          
          
             User user1 = repo.save(user);
             System.out.println(user1);

       } catch(Exception e){
              
            return "register";

       }
       
        return "index";
        
       
                
    }

}
