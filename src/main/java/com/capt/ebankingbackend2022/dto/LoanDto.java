package com.capt.ebankingbackend2022.dto;

import com.capt.ebankingbackend2022.entity.InterestEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class LoanDto {
    private Long id;

    private Date startTime;

    private Date endTime;

    private String status;

    private InterestDto interest;
}
