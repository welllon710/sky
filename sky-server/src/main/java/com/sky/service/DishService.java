package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

public interface DishService {
    void save(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    DishVO getById(Integer id);

    void update(DishDTO  dishDTO);

    void delDish(Long[] ids);
}
