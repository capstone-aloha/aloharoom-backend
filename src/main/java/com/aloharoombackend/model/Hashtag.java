package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "hashtag", fetch = FetchType.LAZY)
    private User user;

    //==해시태그 임의지정==//
    @Column(name = "a1") //층간소음 없는
    private boolean a1;
    @Column(name = "b1")
    private boolean b1;

    @Column(name = "a2") //조용한
    private boolean a2;
    @Column(name = "b2")
    private boolean b2;

    @Column(name = "a3") //역세권
    private boolean a3;
    @Column(name = "b3")
    private boolean b3;

    @Column(name = "a4") //비흡연자
    private boolean a4;
    @Column(name = "b4")
    private boolean b4;

    @Column(name = "a5") //편의점
    private boolean a5;
    @Column(name = "b5")
    private boolean b5;
}
