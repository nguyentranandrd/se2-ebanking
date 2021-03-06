package com.capt.ebankingbackend2022.dto;

import com.capt.ebankingbackend2022.entity.InterestEntity;
import com.capt.ebankingbackend2022.entity.TransactionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class SavingDto extends BaseDto {

    private Date startTime;

    private Date endTime;

    private boolean hasMaturity;

    private boolean maturityWithProfit;

    private String status;

    private InterestDto interest;
}
