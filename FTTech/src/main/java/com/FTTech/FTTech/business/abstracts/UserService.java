package com.FTTech.FTTech.business.abstracts;

import com.FTTech.FTTech.business.dtos.userDtos.UserDto;
import com.FTTech.FTTech.business.dtos.userDtos.UserListDto;
import com.FTTech.FTTech.business.requests.creates.UserCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.UserDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.UserUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;

import java.util.List;

public interface UserService {
    DataResult<List<UserListDto>> getAll();
    Result add(UserCreateRequest userCreateRequest) throws BusinessException;
    DataResult<UserDto> getById(int id) throws BusinessException;
    Result update(UserUpdateRequest userUpdateRequest) throws BusinessException;
    Result delete(UserDeleteRequest userDeleteRequest) throws BusinessException;
    boolean checkIfUserExistsById(Integer id) throws BusinessException;
}
