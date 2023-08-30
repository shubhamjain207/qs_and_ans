package com.qsanspack.qsandans.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsanspack.qsandans.entities.Question;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

    
    
}
