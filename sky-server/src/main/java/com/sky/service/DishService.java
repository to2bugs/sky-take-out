package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {

    /**
     * 新增菜品，以及对应的口味数据
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
