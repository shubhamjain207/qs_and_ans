package com.qsanspack.qsandans.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qsanspack.qsandans.entities.LoginResponseDTO;
import com.qsanspack.qsandans.entities.RegistrationDTO;
import com.qsanspack.qsandans.entities.Role;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.repos.UserRepo;






@Service
public class AuthenticationService {


    @Autowired
    private UserRepo userRepo;

    // @Autowired
    // private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User registerUser(String username, String password,String profilepicture,String fullname) {

        String encodedPass = encoder.encode(password);
       // User userRole = userRepo.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(new Role("USER"));

        return userRepo.save(new User(0,username, encodedPass,profilepicture,fullname, authorities));
    }

    
    public LoginResponseDTO loginUser(String username, String password) {

        try {

                    Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

                    String token = tokenService.generateJwt(auth);
                   

                    LoginResponseDTO dto = new LoginResponseDTO(userRepo.findByUsername(username).get(), token);

                    return dto;
                    

                     } catch (AuthenticationException e) {
                        System.out.println(e);
                        return new LoginResponseDTO(null, "");
                    }

    }

   

}
