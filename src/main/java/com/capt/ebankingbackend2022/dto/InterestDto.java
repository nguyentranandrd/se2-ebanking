package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class InterestDto extends BaseDto {
    private double rate;
    private double instantRate;
    private long duration;
    private String type; //(saving/loan)
}
