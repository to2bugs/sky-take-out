package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 新增菜品，以及对应的口味数据
     */
    public void saveWithFlavor(DishDTO dishDTO);


    /**
     * 菜品分页查询
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);


    /**
     * 批量删除菜品
     */
    void deleteBatch(List<Long> ids);


    /**
     * 根据id查询菜品
     */
    DishVO getByIdWithFlavor(Long id);


    /**
     * 修改菜品
     */
    void updateWidthFlavor(DishDTO dishDTO);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
