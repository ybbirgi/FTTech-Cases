package com.FTTech.FTTech.business.requests.creates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotNull
    @Max(50)
    private String userName;

    @NotNull
    @Max(50)
    private String userLastName;

    @NotNull
    @Max(50)
    @Email
    private String email;

    @NotNull
    @NumberFormat
    @PositiveOrZero
    @Max(15)
    private int userPhoneNumber;

}
