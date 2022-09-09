package com.FTTech.FTTech.business.dtos.userDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    private String userName;
    private String userLastName;
    private String userEmail;
    private int userPhoneNumber;
}
