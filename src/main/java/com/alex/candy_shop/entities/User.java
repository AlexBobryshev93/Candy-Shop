package com.alex.candy_shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String tel;
/*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders; */
}
