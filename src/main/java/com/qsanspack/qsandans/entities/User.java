package com.qsanspack.qsandans.entities;

import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    private int id;

    @NotBlank(message = "please enter username")
    @Size(min = 5, message = "Size should be minimum 5")
    @Column(unique = true)
    private String username;

    private String password;
    private String profilepicture;
    private String fullname;
    

    private Set<Role> authorities;
    private Set<String> questions;

    public User() {
        super();
        this.authorities = new HashSet<>();
    }

    public User(int user_id,
            @NotBlank(message = "please enter username") @Size(min = 5, message = "Size should be minimum 5") String username,
            String password, String profilepicture, String fullname, Set<Role> authorities, Set<String> questions) {

        super();

        this.id = user_id;
        this.username = username;
        this.password = password;
        this.profilepicture = profilepicture;
        this.fullname = fullname;
        this.authorities = authorities;
        this.questions = questions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

   
    public Set<String> getQuestions() {

        return questions;
    }

    public void setQs(String qs){
        if(questions == null){
            questions = new HashSet<>();
        }

        questions.add(qs);
    }

    
    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

}
