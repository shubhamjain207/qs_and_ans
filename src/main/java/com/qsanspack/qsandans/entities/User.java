package com.qsanspack.qsandans.entities;


import jakarta.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;


    @NotBlank(message = "please enter username")
    @Size(min=5,message="Size should be minimum 5")
    private String user_name;

    
    private String user_email;
    private String user_password;
    
    public User(int user_id, String user_name, String user_email, String user_password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public User() {
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email
                + ", user_password=" + user_password + "]";
    }

    public String getUser_password() {
        return user_password;
    }


    

}
