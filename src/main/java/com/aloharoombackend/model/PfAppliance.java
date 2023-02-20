package com.aloharoombackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PfAppliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "pfAppliance", fetch = FetchType.LAZY)
    private User user;

    @Column(name = "waterpurifier")
    private boolean waterpurifier;

    @Column(name = "airconditioner")
    private boolean airconditioner;

    @Column(name = "microwave")
    private boolean microwave;

    @Column(name = "washer")
    private boolean washer;

}
