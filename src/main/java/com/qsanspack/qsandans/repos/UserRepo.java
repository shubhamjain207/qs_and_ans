package com.qsanspack.qsandans.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qsanspack.qsandans.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{


    Optional<User> findByUsername(String username);
   // Optional<User> findByAuthority(String authority);

    
}
