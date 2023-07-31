package com.qsanspack.qsandans.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qsanspack.qsandans.entities.LoginResponseDTO;
import com.qsanspack.qsandans.entities.Role;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.repos.RoleRepo;
import com.qsanspack.qsandans.repos.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User registerUser(String username, String password) {

        String encodedPass = encoder.encode(password);
        Role userRole = roleRepo.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepo.save(new User(0, username, encodedPass, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {

        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);

            LoginResponseDTO dto = new LoginResponseDTO(userRepo.findByUsername(username).get(), token);

            return dto;

        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }

    }

}
