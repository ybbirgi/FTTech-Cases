package com.FTTech.FTTech.business.dtos.itemCommentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCommentListDto {
    private String comment;

    private LocalDate commentDate;

    private int itemId;

    private int userId;

}
