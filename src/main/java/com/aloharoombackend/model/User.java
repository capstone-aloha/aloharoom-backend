package com.aloharoombackend.model;

import com.aloharoombackend.dto.MyPageEditDto;
import com.aloharoombackend.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"likeHomeHashtags", "myHomeHashtags", "likeHashtags", "myHashtags", "board"})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "home_id")
//    private Home home;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    Board board;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private String profileUrl;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name", length = 1000)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeHomeHashtag> likeHomeHashtags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MyHomeHashtag> myHomeHashtags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeHashtag> likeHashtags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MyHashtag> myHashtags = new ArrayList<>();

    public User(SignUpDto signUpDto) {
        this.username = signUpDto.getUsername();
        this.password = signUpDto.getPassword();
        this.nickname = signUpDto.getNickname();
        this.age = signUpDto.getAge();
        this.gender = signUpDto.getGender();
    }

    public User edit(MyPageEditDto myPageEditDto, String profileUrl) {
        this.password = myPageEditDto.getPassword();
        this.nickname = myPageEditDto.getNickname();
        this.age = myPageEditDto.getAge();
        this.gender = myPageEditDto.getGender();
        this.profileUrl = "https://test-aloha1.s3.ap-northeast-2.amazonaws.com/" + profileUrl;
        return this;
    }

    //초기 데이터용
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    //Test용
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String encPassword) {
        this.password = encPassword;
    }
}
