package com.qsanspack.qsandans.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.repos.UserRepo;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private PasswordEncoder encoder;

   

    @Autowired
    private UserRepo repo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       
        
        return repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found"));
        

    }
    
}
