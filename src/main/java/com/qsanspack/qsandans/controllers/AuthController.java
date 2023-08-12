package com.qsanspack.qsandans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.qsanspack.qsandans.entities.LoginResponseDTO;
// import com.qsanspack.qsandans.entities.LoginResponseDTO;
import com.qsanspack.qsandans.entities.RegistrationDTO;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.services.AuthenticationService;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    

    @Autowired
    private AuthenticationService service;


    @PostMapping("/registeruser")
    public String registerUser(RegistrationDTO user){
        User u = service.registerUser(user.getUsername(), user.getPassword(),user.getProfileimage(),user.getFullname());

        return "redirect:/user/home?success";
    }

    @PostMapping("/profile")
    public String profile(){
        
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //  System.out.println(auth.getDetails());

    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
    //    // UserDetails details = (UserDetails) auth.getPrincipal();
    //     User user1 = (User) auth.getPrincipal();
    //     System.out.println(user1.getAuthorities());

        return "redirect:/user/home?success";


    }
    

    @PostMapping("/login")
    public String loginUser(RegistrationDTO body){
             LoginResponseDTO user = service.loginUser(body.getUsername(), body.getPassword());
             if(!user.getUser().equals(null)){
                System.out.println(user.getUser());
                System.out.println(user.getJwt());

                return "redirect:/user/home?success";        
             }
             else{
                System.out.println("Error");
             }

             return "";
            
    }

}
