package com.FTTech.FTTech.business.concretes;

import com.FTTech.FTTech.business.abstracts.ItemService;
import com.FTTech.FTTech.business.constants.messages.BusinessMessages;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemDto;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.mapping.ModelMapperService;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;
import com.FTTech.FTTech.core.utilities.results.SuccessDataResult;
import com.FTTech.FTTech.core.utilities.results.SuccessResult;
import com.FTTech.FTTech.dataAccess.abstracts.ItemDao;
import com.FTTech.FTTech.entities.concretes.Item;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemManager implements ItemService {
    private ModelMapperService modelMapperService;
    private ItemDao itemDao;

    public ItemManager(ModelMapperService modelMapperService, ItemDao itemDao) {
        this.modelMapperService = modelMapperService;
        this.itemDao = itemDao;
    }

    @Override
    public DataResult<List<ItemListDto>> getAll() {
        List<Item> result = this.itemDao.findAll();

        List<ItemListDto> response = result.stream().map(item ->
                this.modelMapperService.forDto().map(item, ItemListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(ItemCreateRequest itemCreateRequest) throws BusinessException {
        Item item = this.modelMapperService.forRequest().map(itemCreateRequest, Item.class);

        this.itemDao.save(item);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<ItemDto> getById(int id) throws BusinessException {
        checkIfItemExistsByItemId(id);
        Item item = this.itemDao.getReferenceById(id);

        ItemDto itemDto = this.modelMapperService.forDto().map(item, ItemDto.class);

        return new SuccessDataResult<ItemDto>(itemDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result update(ItemUpdateRequest itemUpdateRequest) throws BusinessException {
        checkIfItemExistsByItemId(itemUpdateRequest.getItemId());
        Item item = this.modelMapperService.forRequest().map(itemUpdateRequest, Item.class);

        this.itemDao.save(item);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(ItemDeleteRequest itemDeleteRequest) throws BusinessException {
        checkIfItemExistsByItemId(itemDeleteRequest.getItemId());
        Item item = this.modelMapperService.forRequest().map(itemDeleteRequest, Item.class);

        this.itemDao.delete(item);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<ItemListDto>> findAllExpiredItems() {
        List<Item> result = this.itemDao.findByExpirationDateBefore(LocalDate.now());

        List<ItemListDto> response = result.stream().map(item ->
                this.modelMapperService.forDto().map(item, ItemListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<ItemListDto>> findAllNonExpiredItems(){
        List<Item> allItems = this.itemDao.findAll();
        List<Item> expiredItems = this.itemDao.findByExpirationDateBefore(LocalDate.now());

        allItems.removeAll(expiredItems);

        List<ItemListDto> response = allItems.stream().map(item ->
                this.modelMapperService.forDto().map(item, ItemListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public boolean checkIfItemExistsByItemId(Integer id) throws BusinessException{
        if(this.itemDao.existsById(id)){
            return true;
        }
        throw new BusinessException(BusinessMessages.ItemMessages.ITEM_NOT_FOUND);
    }
}
