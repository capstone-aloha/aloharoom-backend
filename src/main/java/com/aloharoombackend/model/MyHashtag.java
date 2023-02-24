package com.aloharoombackend.model;

import com.aloharoombackend.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MyHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "myHashtag", fetch = FetchType.LAZY)
    private User user;

    //==해시태그 임의지정==//
    @Column(name = "b1") //층간소음 없는
    private Boolean b1;

    @Column(name = "b2") //조용한
    private Boolean b2;

    @Column(name = "b3") //역세권
    private Boolean b3;

    @Column(name = "b4") //비흡연자
    private Boolean b4;

    @Column(name = "b5") //편의점
    private Boolean b5;

    public MyHashtag(SignUpDto signUpDto) {
        this.b1 = signUpDto.getB1();
        this.b2 = signUpDto.getB2();
        this.b3 = signUpDto.getB3();
        this.b4 = signUpDto.getB4();
        this.b5 = signUpDto.getB5();
    }

}
