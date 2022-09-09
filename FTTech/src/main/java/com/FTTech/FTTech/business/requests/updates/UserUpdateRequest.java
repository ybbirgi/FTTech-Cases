package com.FTTech.FTTech.business.requests.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotNull
    @Positive
    private int userId;

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
