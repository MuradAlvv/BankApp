package com.example.rest.services;

import com.example.rest.entities.User;
import com.example.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public double getBalance(String username){
        return  userRepository.findByUsername(username).getBalance();
    }

    public User findByUsername(String toUser) {
        return userRepository.findByUsername(toUser);
    }

    public boolean transferMoneyTo(String toUser,String fromUser,double amount){
        User user1 = userRepository.findByUsername(toUser);
        User user2 = userRepository.findByUsername(fromUser);
        double balance1 = user1.getBalance();
        double balance2 = user2.getBalance();

        if(balance2<amount){
            System.out.println("you don't have enough money");
            return false;
        }else {

            user1.setBalance(balance1 + amount);
            user2.setBalance(balance2-amount);
            userRepository.save(user1);
            userRepository.save(user2);
            return true;
        }

    }
}
