package com.aloharoombackend.model;

import com.aloharoombackend.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = "myProducts")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

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

    @OneToMany(mappedBy = "user")
    private List<MyProduct> myProducts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likeHashtag")
    private LikeHashtag likeHashtag;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myHashtag")
    private MyHashtag myHashtag;

    public User(SignUpDto signUpDto, LikeProduct likeProduct, List<MyProduct> myProducts, LikeHashtag likeHashtag, MyHashtag myHashtag) {
        this.username = signUpDto.getUsername();
        this.password = signUpDto.getPassword();
        this.nickname = signUpDto.getNickname();
//        this.name = signUpDto.getName;
        this.age = signUpDto.getAge();
        this.gender = signUpDto.getGender();
        this.role = signUpDto.getRole();
        this.tendency = signUpDto.getTendency();
        this.likeProduct = likeProduct;
        this.myProducts = myProducts;
        this.likeHashtag = likeHashtag;
        this.myHashtag = myHashtag;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String encPassword) {
        this.password = encPassword;
    }
}
