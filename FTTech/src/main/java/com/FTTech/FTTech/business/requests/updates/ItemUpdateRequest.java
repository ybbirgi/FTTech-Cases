package com.FTTech.FTTech.business.requests.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemUpdateRequest {
    @NotNull
    @Positive
    private int itemId;

    @NotNull
    @NumberFormat
    @PositiveOrZero
    private int itemPrice;

    private LocalDate expirationDate;
}
