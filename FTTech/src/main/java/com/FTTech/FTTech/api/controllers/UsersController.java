package com.FTTech.FTTech.api.controllers;

import com.FTTech.FTTech.business.abstracts.UserService;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemDto;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemListDto;
import com.FTTech.FTTech.business.dtos.userDtos.UserDto;
import com.FTTech.FTTech.business.dtos.userDtos.UserListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCreateRequest;
import com.FTTech.FTTech.business.requests.creates.UserCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemDeleteRequest;
import com.FTTech.FTTech.business.requests.deletes.UserDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemUpdateRequest;
import com.FTTech.FTTech.business.requests.updates.UserUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public DataResult<List<UserListDto>> getAll(){
        return this.userService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<UserDto> getById(@RequestParam int id) throws BusinessException {return this.userService.getById(id);}

    @PostMapping("/add")
    public Result add(@RequestBody UserCreateRequest userCreateRequest) throws BusinessException {
        return this.userService.add(userCreateRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody UserUpdateRequest userUpdateRequest) throws BusinessException{
        return this.userService.update(userUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody UserDeleteRequest userDeleteRequest) throws BusinessException{
        return this.userService.delete(userDeleteRequest);
    }

}
