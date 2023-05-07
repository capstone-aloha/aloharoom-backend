package com.aloharoombackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeDto {
    Double southWestLatitude;
    Double southWestLongitude;
    Double northEastLatitude;
    Double northEastLongitude;
}
