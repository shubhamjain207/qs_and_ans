package com.qsanspack.qsandans.entities;


import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;




@Entity
@Table(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;


    @NotBlank(message = "please enter username")
    @Size(min=5,message="Size should be minimum 5")
    @Column(unique = true)
    private String username;

    
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns={@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;


    public User() {
        super();
        this.authorities = new HashSet<>();
    }



    public User(int id,
            @NotBlank(message = "please enter username") @Size(min = 5, message = "Size should be minimum 5") String username, String password,Set<Role> authorities) {

        super();

        this.id = id;
        this.username = username;
        
        this.password = password;
        this.authorities = authorities;
    }


  



    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username  + ", password=" + password + "]";
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return (Collection<? extends GrantedAuthority>) this.authorities;
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
