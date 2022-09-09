package com.FTTech.FTTech.business.requests.creates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateRequest {
    @NotNull
    @NumberFormat
    @PositiveOrZero
    private int itemPrice;

    private LocalDate expirationDate;
}
