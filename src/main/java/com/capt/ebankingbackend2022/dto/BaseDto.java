package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
public class BaseDto {

    protected Long id;

    protected Date createdAt;

    protected Date updatedAt;
}
