package com.aloharoombackend.model;

import com.aloharoombackend.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LikeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "waterpurifier")
    private Boolean waterpurifier;

    @Column(name = "aircon")
    private Boolean aircon;

    @Column(name = "microwave")
    private Boolean microwave;

    @Column(name = "washer")
    private Boolean washer;

    public LikeProduct(SignUpDto signUpDto) {
        this.waterpurifier = signUpDto.getLikeWaterpurifier();
        this.aircon = signUpDto.getLikeAircon();
        this.microwave = signUpDto.getLikeMicrowave();
        this.washer = signUpDto.getLikeWasher();
    }
}
