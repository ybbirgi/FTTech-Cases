package com.FTTech.FTTech.business.requests.deletes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequest {
    @NotNull
    @Positive
    private int userId;

}
