package com.capt.ebankingbackend2022.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@Table(name = "fr_interest")
public class InterestEntity extends BaseEntity {
    @Column(name = "rate")
    private double rate;
    @Column(name = "instantRate")
    private double instantRate;
    @Column(name = "duration")
    private long duration;
    @Column
    private String type; //(saving/loan)
}
