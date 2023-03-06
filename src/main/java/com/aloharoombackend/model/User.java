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

    @OneToMany(mappedBy = "user")
    private List<LikeProduct> likeProducts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<MyProduct> myProducts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<LikeHashtag> likeHashtags = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<MyHashtag> myHashtags = new ArrayList<>();

    public User(SignUpDto signUpDto, List<LikeProduct> likeProducts, List<MyProduct> myProducts, List<LikeHashtag> likeHashtags, List<MyHashtag> myHashtags) {
        this.username = signUpDto.getUsername();
        this.password = signUpDto.getPassword();
        this.nickname = signUpDto.getNickname();
//        this.name = signUpDto.getName;
        this.age = signUpDto.getAge();
        this.gender = signUpDto.getGender();
        this.role = signUpDto.getRole();
        this.tendency = signUpDto.getTendency();
        this.likeProducts = likeProducts;
        this.myProducts = myProducts;
        this.likeHashtags = likeHashtags;
        this.myHashtags = myHashtags;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String encPassword) {
        this.password = encPassword;
    }
}
