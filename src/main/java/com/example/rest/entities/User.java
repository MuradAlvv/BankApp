package com.example.rest.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
//@RequiredArgsConstructor
public class User implements UserDetails {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private final String username;
    private final String password;
    private final String fullname;
    private final String state;
    private final String phoneNumber;

    private  double balance;

    private User(String username, String password, String fullname, String state, String phoneNumber){
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String password, String fullname, String state, String phoneNumber, double balance) {
        this(username,password,fullname,state,phoneNumber);
        this.balance = balance;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
