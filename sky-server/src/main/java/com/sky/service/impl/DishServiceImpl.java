package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.saveDish(dish);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dish.getId());
            }
            dishFlavorMapper.saveFlavors(flavors);
        }
    }

    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.page(dishPageQueryDTO);
        log.info("page:{}",page);
        List<DishVO> result = page.getResult();
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public DishVO getById(Integer id) {
       return dishMapper.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        // 更新基本信息
        dishMapper.update(dish);
        // 删除口味
        List<Long> ids = Collections.singletonList(dish.getId());
        dishFlavorMapper.delFlavors(ids);
        // 更新口味
        if (flavors != null && flavors.size() > 0) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dish.getId());
            }
        }
        dishFlavorMapper.saveFlavors(flavors);
    }

    @Override
    public void delDish(Long[] ids) {
        // 删除口味信息
        List<Long> list = Arrays.asList(ids);
        dishFlavorMapper.delFlavors(list);

    }
}
