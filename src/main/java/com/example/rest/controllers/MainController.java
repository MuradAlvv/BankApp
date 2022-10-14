package com.example.rest.controllers;


import com.example.rest.entities.Transaction;
import com.example.rest.repositories.TransactionRepository;
import com.example.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/demobank")
public class MainController {

    private UserService userService;
    private TransactionRepository transactionRepository;

    @Autowired
    public MainController(UserService userService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }



    @GetMapping
    public String homePage(Model model,Principal principal){
        model.addAttribute("username",principal.getName());
        model.addAttribute("balance",userService.getBalance(principal.getName()));
        model.addAttribute("transaction",new Transaction());
        return "home";
    }


    @PostMapping("/")
    public String transferMoney(@ModelAttribute("transaction") Transaction transaction,Principal principal){
        transaction.setFromUser(principal.getName());
        if(userService.transferMoneyTo(transaction.getToUser(),transaction.getFromUser(),transaction.getAmount())) {
            transaction.setStatus("Success");
        }else {
            transaction.setStatus("Failed");
        }
        transactionRepository.save(transaction);

        return "redirect:/demobank";
    }
}
