package com.qsanspack.qsandans.userrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsanspack.qsandans.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
    
}
