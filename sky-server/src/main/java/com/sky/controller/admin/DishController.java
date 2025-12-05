package com.sky.controller.admin;


import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping()
    public Result addDish(@RequestBody DishDTO dishDTO){
        dishService.save(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult page = dishService.page(dishPageQueryDTO);
        return Result.success(page);
    }
    @GetMapping("/{id}")
    public Result<DishVO> getDish(@PathVariable Integer id) {
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    @PutMapping()
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        dishService.update(dishDTO);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteDish(@PathVariable Long[] ids) {
        dishService.delDish(ids);
        return Result.success();
    }
}
