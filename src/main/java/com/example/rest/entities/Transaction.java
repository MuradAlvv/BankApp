package com.example.rest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {

    public Transaction(){}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;


    private double amount;

    private String status;


}
