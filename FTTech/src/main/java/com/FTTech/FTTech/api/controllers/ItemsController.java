package com.FTTech.FTTech.api.controllers;

import com.FTTech.FTTech.business.abstracts.ItemService;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemDto;
import com.FTTech.FTTech.business.dtos.itemDtos.ItemListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsController {
    private ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/getAll")
    public DataResult<List<ItemListDto>> getAll(){
        return this.itemService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<ItemDto> getById(@RequestParam int id) throws BusinessException {return this.itemService.getById(id);}

    @GetMapping("/getAllExpiredItems")
    public DataResult<List<ItemListDto>> getAllExpiredItems() {return this.itemService.findAllExpiredItems();}

    @GetMapping("/getAllNonExpiredItems")
    public DataResult<List<ItemListDto>> getAllNonExpiredItems() {return this.itemService.findAllNonExpiredItems();}

    @PostMapping("/add")
    public Result add(@RequestBody ItemCreateRequest itemCreateRequest) throws BusinessException {
        return this.itemService.add(itemCreateRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody ItemUpdateRequest itemUpdateRequest) throws BusinessException{
        return this.itemService.update(itemUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody ItemDeleteRequest itemDeleteRequest) throws BusinessException{
        return this.itemService.delete(itemDeleteRequest);
    }

}
