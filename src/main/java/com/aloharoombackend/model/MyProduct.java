package com.aloharoombackend.model;

import com.aloharoombackend.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "myProduct", fetch = FetchType.LAZY)
    private User user;

    @Column(name = "waterpurifier")
    private Boolean waterpurifier;

    @Column(name = "aircon")
    private Boolean aircon;

    @Column(name = "microwave")
    private Boolean microwave;

    @Column(name = "washer")
    private Boolean washer;

    public MyProduct(SignUpDto signUpDto) {
        this.waterpurifier = signUpDto.getMyWaterpurifier();
        this.aircon = signUpDto.getMyAircon();
        this.microwave = signUpDto.getMyMicrowave();
        this.washer = signUpDto.getMyWasher();
    }
}