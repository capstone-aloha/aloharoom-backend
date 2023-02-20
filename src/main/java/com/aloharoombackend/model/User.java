package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "home_id")
    private Home home;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    @Column(name = "tendency")
    private String tendency;

    @OneToOne
    @JoinColumn(name = "pfapp_id")
    private PfAppliance pfAppliance;

    @OneToOne
    @JoinColumn(name = "hash_id")
    private Hashtag hashtag;


}
