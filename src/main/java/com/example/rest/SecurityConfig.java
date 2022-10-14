package com.example.rest;

import com.example.rest.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterChain;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    //IN MEMORY
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(new User("Murad", passwordEncoder().encode("1234"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        userList.add(new User("Jack",passwordEncoder().encode("2081"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//
//        return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            com.example.rest.entities.User user = userRepository.findByUsername(username);
            if(user!=null){
                return user;
            }
            throw new UsernameNotFoundException("Account with username: " + username + " doesn't exist" );
        };
    }


    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception{
        return http.authorizeRequests()
                .antMatchers("/demobank/**").hasRole("USER").and().formLogin()
                .loginPage("/login").defaultSuccessUrl("/demobank").and().build();
    }


}
