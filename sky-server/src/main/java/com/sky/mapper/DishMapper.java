package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.annotion.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    @AutoFill(value = OperationType.INSERT)
    void saveDish(Dish dish);

    Page<DishVO> page(DishPageQueryDTO dishPageQueryDTO);

    DishVO findById(Integer id);

    void update(Dish dish);

    void delDish(Integer[] ids);
}
