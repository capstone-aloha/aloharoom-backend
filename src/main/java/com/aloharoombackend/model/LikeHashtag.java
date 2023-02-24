package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LikeHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "likeHashtag", fetch = FetchType.LAZY)
    private User user;

    //==해시태그 임의지정==//
    @Column(name = "a1") //층간소음 없는
    private Boolean a1;

    @Column(name = "a2") //조용한
    private Boolean a2;

    @Column(name = "a3") //역세권
    private Boolean a3;

    @Column(name = "a4") //비흡연자
    private Boolean a4;

    @Column(name = "a5") //편의점
    private Boolean a5;
}
