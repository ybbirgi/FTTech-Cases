package com.FTTech.FTTech.business.abstracts;

import com.FTTech.FTTech.business.dtos.itemCommentDtos.ItemCommentDto;
import com.FTTech.FTTech.business.dtos.itemCommentDtos.ItemCommentListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCommentCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemCommentDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemCommentUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;

import java.time.LocalDate;
import java.util.List;

public interface ItemCommentService {
    DataResult<List<ItemCommentListDto>> getAll();
    Result add(ItemCommentCreateRequest itemCommentCreateRequest) throws BusinessException;
    DataResult<ItemCommentDto> getById(int id) throws BusinessException;
    Result update(ItemCommentUpdateRequest itemCommentUpdateRequest) throws BusinessException;
    Result delete(ItemCommentDeleteRequest itemCommentDeleteRequest) throws BusinessException;
    DataResult<List<ItemCommentListDto>> getAllByItemId(int id) throws BusinessException;
    public DataResult<List<ItemCommentListDto>> getAllBetweenDates(LocalDate firstDate, LocalDate secondDate) throws BusinessException;
    public DataResult<List<ItemCommentListDto>> getAllByUserId(int id) throws BusinessException;
    public DataResult<List<ItemCommentListDto>> getAllByUserIdAndBetweenDates(LocalDate firstDate,LocalDate lastDate,int id) throws BusinessException;
    }
