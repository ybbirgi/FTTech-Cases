package com.FTTech.FTTech.business.requests.creates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCommentCreateRequest {
    @NotNull
    @Max(500)
    private String comment;

    private LocalDate localDate;

    @NotNull
    @PositiveOrZero
    private int itemId;

    @NotNull
    @PositiveOrZero
    private int userId;

}
