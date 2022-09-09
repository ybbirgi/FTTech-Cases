package com.FTTech.FTTech.business.dtos.itemDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private int itemPrice;
    private LocalDate localDate;
}
