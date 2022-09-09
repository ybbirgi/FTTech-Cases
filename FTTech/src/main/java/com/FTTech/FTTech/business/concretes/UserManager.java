package com.FTTech.FTTech.business.concretes;

import com.FTTech.FTTech.business.abstracts.UserService;
import com.FTTech.FTTech.business.constants.messages.BusinessMessages;
import com.FTTech.FTTech.business.dtos.userDtos.UserDto;
import com.FTTech.FTTech.business.dtos.userDtos.UserListDto;
import com.FTTech.FTTech.business.requests.creates.UserCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.UserDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.UserUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.mapping.ModelMapperService;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;
import com.FTTech.FTTech.core.utilities.results.SuccessDataResult;
import com.FTTech.FTTech.core.utilities.results.SuccessResult;
import com.FTTech.FTTech.dataAccess.abstracts.UserDao;
import com.FTTech.FTTech.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {
    private ModelMapperService modelMapperService;
    private UserDao userDao;

    @Autowired
    public UserManager(ModelMapperService modelMapperService, UserDao userDao) {
        this.modelMapperService = modelMapperService;
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<UserListDto>> getAll() {
        List<User> result = this.userDao.findAll();

        List<UserListDto> response = result.stream().map(user ->
                this.modelMapperService.forDto().map(user, UserListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<UserListDto>>(response, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(UserCreateRequest userCreateRequest) throws BusinessException {

        checkIfUserAlreadyExits(userCreateRequest.getEmail());

        User user = this.modelMapperService.forRequest().map(userCreateRequest, User.class);

        this.userDao.save(user);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<UserDto> getById(int id) throws BusinessException {
        checkIfUserExistsById(id);

        User user = this.userDao.getReferenceById(id);

        UserDto userDto = this.modelMapperService.forDto().map(user, UserDto.class);

        return new SuccessDataResult<UserDto>(userDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result update(UserUpdateRequest userUpdateRequest) throws BusinessException {

        checkIfUserExistsById(userUpdateRequest.getUserId());

        User user = this.modelMapperService.forRequest().map(userUpdateRequest, User.class);

        this.userDao.save(user);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(UserDeleteRequest userDeleteRequest) throws BusinessException {
        checkIfUserExistsById(userDeleteRequest.getUserId());

        User user = this.modelMapperService.forRequest().map(userDeleteRequest, User.class);

        this.userDao.delete(user);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
    }

    void checkIfUserAlreadyExits(String email) throws BusinessException{
        if(this.userDao.existsByUserEmail(email)){
            throw new BusinessException(BusinessMessages.UserMesseges.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public boolean checkIfUserExistsById(Integer id) throws BusinessException{
        if(this.userDao.existsById(id)){
            return true;
        }
        throw new BusinessException(BusinessMessages.UserMesseges.USER_NOT_FOUND);
    }
}
