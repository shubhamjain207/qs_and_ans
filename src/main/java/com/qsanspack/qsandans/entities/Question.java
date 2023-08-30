package com.qsanspack.qsandans.entities;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "questions")
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String questioncontent;

    public int getId() {
        return id;
    }

    public Question(int id, String questioncontent) {
        this.id = id;
        this.questioncontent = questioncontent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent;
    }

    public Question() {
    }
  
    
    
}
