package com.chomoncik.karol.camp_menu.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail")
    private String mail;

    @OneToOne(mappedBy = "author")
    private Meal meal;
}
