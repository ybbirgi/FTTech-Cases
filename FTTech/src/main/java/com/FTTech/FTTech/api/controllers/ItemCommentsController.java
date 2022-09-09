package com.FTTech.FTTech.api.controllers;

import com.FTTech.FTTech.business.abstracts.ItemCommentService;
import com.FTTech.FTTech.business.dtos.itemCommentDtos.ItemCommentDto;
import com.FTTech.FTTech.business.dtos.itemCommentDtos.ItemCommentListDto;
import com.FTTech.FTTech.business.requests.creates.ItemCommentCreateRequest;
import com.FTTech.FTTech.business.requests.deletes.ItemCommentDeleteRequest;
import com.FTTech.FTTech.business.requests.updates.ItemCommentUpdateRequest;
import com.FTTech.FTTech.core.utilities.exceptions.BusinessException;
import com.FTTech.FTTech.core.utilities.results.DataResult;
import com.FTTech.FTTech.core.utilities.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/itemComments")
public class ItemCommentsController {
    private ItemCommentService itemCommentService;

    public ItemCommentsController(ItemCommentService itemCommentService) {
        this.itemCommentService = itemCommentService;
    }

    @GetMapping("/getAll")
    public DataResult<List<ItemCommentListDto>> getAll() {
        return this.itemCommentService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<ItemCommentDto> getById(@RequestParam int id) throws BusinessException {
        return this.itemCommentService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ItemCommentCreateRequest itemCommentCreateRequest) throws BusinessException {
        return this.itemCommentService.add(itemCommentCreateRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody ItemCommentUpdateRequest itemCommentUpdateRequest) throws BusinessException {
        return this.itemCommentService.update(itemCommentUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody ItemCommentDeleteRequest itemCommentDeleteRequest) throws BusinessException {
        return this.itemCommentService.delete(itemCommentDeleteRequest);
    }

    @GetMapping("/getAllByItemId")
    public DataResult<List<ItemCommentListDto>> getAllByItemId(@RequestParam int id) throws BusinessException {
        return this.itemCommentService.getAllByItemId(id);
    }

    @GetMapping("/getCommentsBetweenDates/{first_date}/{second_date}")
    DataResult<List<ItemCommentListDto>> getCommentsBetweenDates(@PathVariable(value = "first_date")
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDate,
                                                                 @PathVariable(value = "second_date")
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate secondDate) throws BusinessException {
        return this.itemCommentService.getAllBetweenDates(firstDate, secondDate);
    }

    @GetMapping("/getAllByUserId")
    public DataResult<List<ItemCommentListDto>> getAllByUserId(@RequestParam int id) throws BusinessException {
        return this.itemCommentService.getAllByUserId(id);
    }

    @GetMapping("/getCommentsOfUserBetweenDates/{first_date}/{second_date}/{user_id}")
    DataResult<List<ItemCommentListDto>> getAllByUserIdAndBetweenDates(@PathVariable(value = "first_date")
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDate,
                                                                 @PathVariable(value = "second_date")
                                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate secondDate,
                                                                       @PathVariable(value = "user_id") int userId) throws BusinessException {
        return this.itemCommentService.getAllByUserIdAndBetweenDates(firstDate, secondDate, userId);
    }

}
