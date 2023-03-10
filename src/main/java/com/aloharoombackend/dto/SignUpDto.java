package com.aloharoombackend.dto;

import com.aloharoombackend.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpDto {

    //user
    private Long signupId;
    private String username;
    private String password;
    private String nickname;
    //private String name;
    private Integer age;
    private String gender;
    private String role;
    private String tendency;

    //likeHash
    private List<String> likeHashtags;

    //likeProduct
    private List<String> likeProducts;

    //myHash
    private List<String> myHashtags;

    //myProduct
    private List<String> myProducts;

    public SignUpDto(User user) {
        this.signupId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.tendency = user.getTendency();
    }

    //test용
    public SignUpDto(String username, String password, Integer age, String gender) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }
}
