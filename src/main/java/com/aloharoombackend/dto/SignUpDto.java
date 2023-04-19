package com.aloharoombackend.dto;

import com.aloharoombackend.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpDto {

    private String username;
    private String password;
    private String nickname;
    //private String name;
    private Integer age;
    private String gender;

    private List<String> likeHashtags;

    private List<String> likeProducts;

    private List<String> myHashtags;

    private List<String> myProducts;

    public SignUpDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
    }

    //test용
    public SignUpDto(String username, String password, Integer age, String gender) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }
}
