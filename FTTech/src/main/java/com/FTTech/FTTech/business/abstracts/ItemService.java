package com.FTTech.FTTech.business.abstracts;

import com.FTTech.FTTech.business.dtos.itemDtos.ItemDto;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;

import java.util.List;

public interface ItemService {
    DataResult<List<ItemListDto>> getAll();
    Result add(ItemCreateRequest itemCreateRequest) throws BusinessException;
    DataResult<ItemDto> getById(int id) throws BusinessException;
    Result update(ItemUpdateRequest itemUpdateRequest) throws BusinessException;
    Result delete(ItemDeleteRequest itemDeleteRequest) throws BusinessException;
    DataResult<List<ItemListDto>> findAllExpiredItems();
    DataResult<List<ItemListDto>> findAllNonExpiredItems();
    boolean checkIfItemExistsByItemId(Integer id) throws BusinessException;
}
