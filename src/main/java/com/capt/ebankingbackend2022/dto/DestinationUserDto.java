package com.capt.ebankingbackend2022.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DestinationUserDto {
    private long id;

    private String firstName;

    private String lastName;

    private String avatar;

}
