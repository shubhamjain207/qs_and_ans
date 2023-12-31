package com.qsanspack.qsandans.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.query.spi.QueryEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.qsanspack.qsandans.entities.Question;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.repos.QuestionRepo;
import com.qsanspack.qsandans.repos.UserRepo;



@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private QuestionRepo repo1;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       
        User user = repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found"));;

        
        return user;
        

    }

   
    public User setQs(String username,String qs,String qsTime,String qsUser){
       
        User user= repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found"));

        if(user!=null){
            
            repo.save(user);
            repo1.save(new Question(0, qs,qsTime,qsUser));
        }


        return user;
        

    }

    
    public List<Question> getAllQs(String username){
       
        //User user= repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found"));

        return repo1.findAll();
        

    }


   
    
}
