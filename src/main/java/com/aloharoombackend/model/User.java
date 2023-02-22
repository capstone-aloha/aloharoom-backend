package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
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
    @JoinColumn(name = "likeProduct")
    private LikeProduct likeProduct;

    @OneToOne
    @JoinColumn(name = "myProduct")
    private MyProduct myProduct;

    @OneToOne
    @JoinColumn(name = "likeHashtag")
    private LikeHashtag likeHashtag;

    @OneToOne
    @JoinColumn(name = "myHashtag")
    private MyHashtag myHashtag;


}
