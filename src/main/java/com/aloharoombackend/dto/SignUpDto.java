package com.aloharoombackend.dto;

import com.aloharoombackend.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {

    //user
    private Long signupId;
    private String username;
    private String password;
    //private String nickname;
    //private String name;
    private Integer age;
    private String gender;
    private String role;
    private String tendency;

    //likeHash
    private Boolean a1; //층간소음 없는
    private Boolean a2; //조용한
    private Boolean a3; //역세권
    private Boolean a4; //비흡연자
    private Boolean a5; //편의점

    //likeProduct
    private Boolean likeWaterpurifier;
    private Boolean likeAircon;
    private Boolean likeMicrowave;
    private Boolean likeWasher;

    //myHash
    private Boolean b1; //층간소음 없는
    private Boolean b2; //조용한
    private Boolean b3; //역세권
    private Boolean b4; //비흡연자
    private Boolean b5; //편의점

    //myProduct
    private Boolean myWaterpurifier;
    private Boolean myAircon;
    private Boolean myMicrowave;
    private Boolean myWasher;

    public SignUpDto(User user) {
        this.signupId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.tendency = user.getTendency();
        this.a1 = user.getLikeHashtag().getA1();
        this.a2 = user.getLikeHashtag().getA2();
        this.a3 = user.getLikeHashtag().getA3();
        this.a4 = user.getLikeHashtag().getA4();
        this.a5 = user.getLikeHashtag().getA5();
        this.likeWaterpurifier = user.getLikeProduct().getWaterpurifier();
        this.likeAircon = user.getLikeProduct().getAircon();
        this.likeMicrowave = user.getLikeProduct().getMicrowave();
        this.likeWasher = user.getLikeProduct().getWasher();
        this.b1 = user.getMyHashtag().getB1();
        this.b2 = user.getMyHashtag().getB2();
        this.b3 = user.getMyHashtag().getB3();
        this.b4 = user.getMyHashtag().getB4();
        this.b5 = user.getMyHashtag().getB5();
        this.myWaterpurifier = user.getMyProduct().getWaterpurifier();
        this.myAircon = user.getMyProduct().getAircon();
        this.myMicrowave = user.getMyProduct().getMicrowave();
        this.myWasher = user.getMyProduct().getWasher();
    }
}
