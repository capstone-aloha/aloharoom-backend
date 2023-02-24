package com.aloharoombackend.model;

import com.aloharoombackend.dto.SignUpDto;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    @OneToOne(fetch = FetchType.LAZY)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likeProduct")
    private LikeProduct likeProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myProduct")
    private MyProduct myProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likeHashtag")
    private LikeHashtag likeHashtag;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myHashtag")
    private MyHashtag myHashtag;

    public User(SignUpDto signUpDto, LikeProduct likeProduct, MyProduct myProduct, LikeHashtag likeHashtag, MyHashtag myHashtag) {
        this.username = signUpDto.getUsername();
        this.password = signUpDto.getPassword();
//        this.nickname = signUpDto.getnickname;
//        this.name = signUpDto.getName;
        this.age = signUpDto.getAge();
        this.gender = signUpDto.getGender();
        this.role = signUpDto.getRole();
        this.tendency = signUpDto.getTendency();
        this.likeProduct = likeProduct;
        this.myProduct = myProduct;
        this.likeHashtag = likeHashtag;
        this.myHashtag = myHashtag;
    }

}
