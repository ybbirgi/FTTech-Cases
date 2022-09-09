package com.FTTech.FTTech.business.dtos.itemCommentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCommentDto {
    private String comment;
    private LocalDate commentDate;
    private int itemId;
}
