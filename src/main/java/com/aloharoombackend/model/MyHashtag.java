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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String hash;

    public MyHashtag(String hash, User user) {
        this.hash = hash;
        this.user = user;
    }

    //test용
    public MyHashtag(User user, String hash) {
        this.hash = hash;
        this.user = user;
    }
}
