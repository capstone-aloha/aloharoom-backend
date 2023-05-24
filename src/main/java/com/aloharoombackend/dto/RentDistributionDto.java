package com.aloharoombackend.dto;

import com.aloharoombackend.model.Home;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RentDistributionDto {
    Integer s1e5 = 0;
    Integer s6e10 = 0;
    Integer s11e15 = 0;
    Integer s16e20 = 0;
    Integer s21e25 = 0;
    Integer s26e30 = 0;
    Integer s31e35 = 0;
    Integer s36e40 = 0;
    Integer s41e45 = 0;
    Integer s46e50 = 0;
    Integer s51e55 = 0;
    Integer s56e60 = 0;
    Integer s61e65 = 0;
    Integer s66e70 = 0;
    Integer s71e75 = 0;
    Integer s76e80 = 0;
    Integer s81e85 = 0;
    Integer s86e90 = 0;
    Integer s91e95 = 0;
    Integer s96e100 = 0;

    public RentDistributionDto(List<Home> homes) {
        for (Home home : homes) {
            Integer rent = home.getRent();
            if (1 <= rent && rent <= 5) s1e5 += 1;
            else if (6 <= rent && rent <= 10) s6e10 += 1;
            else if (11 <= rent && rent <= 15) s11e15 += 1;
            else if (16 <= rent && rent <= 20) s16e20 += 1;
            else if (21 <= rent && rent <= 25) s21e25 += 1;
            else if (26 <= rent && rent <= 30) s26e30 += 1;
            else if (31 <= rent && rent <= 35) s31e35 += 1;
            else if (36 <= rent && rent <= 40) s36e40 += 1;
            else if (41 <= rent && rent <= 45) s41e45 += 1;
            else if (46 <= rent && rent <= 50) s46e50 += 1;
            else if (51 <= rent && rent <= 55) s51e55 += 1;
            else if (56 <= rent && rent <= 60) s56e60 += 1;
            else if (61 <= rent && rent <= 65) s61e65 += 1;
            else if (66 <= rent && rent <= 70) s66e70 += 1;
            else if (71 <= rent && rent <= 75) s71e75 += 1;
            else if (76 <= rent && rent <= 80) s76e80 += 1;
            else if (81 <= rent && rent <= 85) s81e85 += 1;
            else if (86 <= rent && rent <= 90) s86e90 += 1;
            else if (91 <= rent && rent <= 95) s91e95 += 1;
            else if (96 <= rent) s96e100 += 1;
        }
    }
}
