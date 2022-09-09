package com.FTTech.FTTech.business.concretes;

import com.FTTech.FTTech.business.abstracts.ItemCommentService;
import com.FTTech.FTTech.business.abstracts.ItemService;
import com.FTTech.FTTech.business.abstracts.UserService;
import com.FTTech.FTTech.business.constants.messages.BusinessMessages;
import com.FTTech.FTTech.business.dtos.itemCommentDtos.ItemCommentDto;
import com.FTTech.FTTech.business.dtos.itemCommentDtos.ItemCommentListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCommentCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemCommentDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemCommentUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.mapping.ModelMapperService;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;
import com.FTTech.FTTech.core.utilities.results.SuccessDataResult;
import com.FTTech.FTTech.core.utilities.results.SuccessResult;
import com.FTTech.FTTech.dataAccess.abstracts.ItemCommentDao;
import com.FTTech.FTTech.entities.concretes.ItemComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCommentManager implements ItemCommentService {
    private ModelMapperService modelMapperService;
    private ItemCommentDao itemCommentDao;
    private UserService userService;
    private ItemService itemService;

    @Autowired
    public ItemCommentManager(ItemCommentDao itemCommentDao, ModelMapperService modelMapperService,UserService userService,
                              ItemService itemService) {
        this.itemCommentDao = itemCommentDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    public DataResult<List<ItemCommentListDto>> getAll() {
        List<ItemComment> result = this.itemCommentDao.findAll();

        List<ItemCommentListDto> response = result.stream().map(itemComment ->
                this.modelMapperService.forDto().map(itemComment, ItemCommentListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemCommentListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(ItemCommentCreateRequest itemCommentCreateRequest) throws BusinessException {
        checkIfUserExists(itemCommentCreateRequest.getUserId());
        checkIfItemExists(itemCommentCreateRequest.getItemId());

        ItemComment itemComment = this.modelMapperService.forRequest().map(itemCommentCreateRequest, ItemComment.class);

        this.itemCommentDao.save(itemComment);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<ItemCommentDto> getById(int id) throws BusinessException {

        ItemComment itemComment = this.itemCommentDao.getReferenceById(id);

        ItemCommentDto itemCommentDto = this.modelMapperService.forDto().map(itemComment, ItemCommentDto.class);

        return new SuccessDataResult<ItemCommentDto>(itemCommentDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result update(ItemCommentUpdateRequest itemCommentUpdateRequest) throws BusinessException {
        checkIfUserExists(itemCommentUpdateRequest.getUserId());
        checkIfItemExists(itemCommentUpdateRequest.getItemId());

        ItemComment itemComment = this.modelMapperService.forRequest().map(itemCommentUpdateRequest, ItemComment.class);

        this.itemCommentDao.save(itemComment);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(ItemCommentDeleteRequest itemCommentDeleteRequest) throws BusinessException {

        ItemComment itemComment = this.modelMapperService.forRequest().map(itemCommentDeleteRequest, ItemComment.class);

        this.itemCommentDao.delete(itemComment);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<ItemCommentListDto>> getAllByItemId(int id) throws BusinessException {
        checkIfItemExists(id);

        List<ItemComment> itemCommentList = this.itemCommentDao.getAllByItem_ItemId(id);

        List<ItemCommentListDto> response = itemCommentList.stream().map(itemComment ->
                this.modelMapperService.forDto().map(itemComment, ItemCommentListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemCommentListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<ItemCommentListDto>> getAllBetweenDates(LocalDate firstDate,LocalDate secondDate) throws BusinessException{
        checkIfDatesAreValid(firstDate,secondDate);

        List<ItemComment> itemCommentList = this.itemCommentDao.findByCommentDateBetween(firstDate, secondDate);

        List<ItemCommentListDto> response = itemCommentList.stream().map(itemComment ->
                this.modelMapperService.forDto().map(itemComment, ItemCommentListDto.class)).collect(Collectors.toList());

        /*for(int i=0;i<response.size();i++){
            response.get(i).setItemId(itemCommentList.get(i).getItem().getItemId());
            response.get(i).setUserId(itemCommentList.get(i).getUser().getUserId());
        }*/

        return new SuccessDataResult<List<ItemCommentListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<ItemCommentListDto>> getAllByUserId(int id) throws BusinessException{
        checkIfUserExists(id);

        List<ItemComment> itemCommentList = this.itemCommentDao.getAllByUser_UserId(id);

        List<ItemCommentListDto> response = itemCommentList.stream().map(itemComment ->
                this.modelMapperService.forDto().map(itemComment, ItemCommentListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemCommentListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<ItemCommentListDto>> getAllByUserIdAndBetweenDates(LocalDate firstDate,LocalDate lastDate,int id) throws BusinessException{
        checkIfDatesAreValid(firstDate,lastDate);

        List<ItemComment> itemCommentList = this.itemCommentDao.findByCommentDateBetween(firstDate, lastDate);

        List<ItemCommentListDto> response = itemCommentList.stream().map(itemComment ->
                this.modelMapperService.forDto().map(itemComment, ItemCommentListDto.class)).collect(Collectors.toList());

        List<ItemCommentListDto> filteredResponse = response.stream().filter(itemComment ->
                itemComment.getUserId() == id).collect(Collectors.toList());

        return new SuccessDataResult<List<ItemCommentListDto>>(filteredResponse, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }


    public boolean checkIfUserExists(int userId) throws BusinessException{
        if(this.userService.checkIfUserExistsById(userId)){
            return true;
        }
        throw new BusinessException(BusinessMessages.UserMesseges.USER_NOT_FOUND);
    }

    public boolean checkIfItemExists(int itemId) throws BusinessException{
        if(this.itemService.checkIfItemExistsByItemId(itemId)){
            return true;
        }
        throw new BusinessException(BusinessMessages.ItemMessages.ITEM_NOT_FOUND);
    }

    public void checkIfDatesAreValid(LocalDate first,LocalDate second) throws BusinessException{
        if(second.isBefore(first)){
            throw new BusinessException(BusinessMessages.ItemCommentMessages.DATES_ARE_NOT_VALID);
        }
    }
}
