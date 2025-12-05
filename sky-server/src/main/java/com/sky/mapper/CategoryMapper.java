package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotion.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    Page<Category> page(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void save(Category category);

    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    @Delete("delete from wuwei_sky_take_out.category where id = #{id}")
    void delete(Integer id);

    @Select("select * from wuwei_sky_take_out.category where type = #{type}")
    List<Category> list(Integer type);
}
