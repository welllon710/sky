package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    void save(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    Category getById(Integer id);

    void deleteCategory(Integer id);

    void status(Integer status, long id);

    List<Category> list(Integer type);
}
