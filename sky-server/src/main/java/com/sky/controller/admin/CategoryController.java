package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO  categoryPageQueryDTO) {
        PageResult page = categoryService.page(categoryPageQueryDTO);
        return Result.success(page);
    }
    @PostMapping()
    public Result save(@RequestBody CategoryDTO categoryDTO) {
        log.info("save-categoryDTO={}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }
    @PutMapping()
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        log.info("update-categoryDTO={}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }
    @DeleteMapping()
    public Result delete(@RequestParam Integer id) {
        log.info("delete-id=", id);
        categoryService.deleteCategory(id);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    public Result status(@PathVariable Integer status, @RequestParam Integer id) {
        log.info("status={}", status);
        categoryService.status(status, id);
        return Result.success();
    }
}
