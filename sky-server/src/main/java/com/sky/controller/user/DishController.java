package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        // 1, 查询Redis中是否存在菜品数据
        //    redis中的key的规则：dish_ + 分类id
        String key = "dish_" + categoryId;
        List<DishVO> list = (List<DishVO>)redisTemplate.opsForValue().get(key); // 缓存到redis中是什么数据，去到的就是什么数据
        // 2, 如果存在，直接返回，无需查询数据库
        if (list != null && !list.isEmpty()) {
            log.info("菜品数据从Redis缓存中获取");
            return Result.success(list);
        }

        // 3, 如果不存在，查询数据库，并将查询到的菜品数据要缓存到Redis中
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品

        list = dishService.listWithFlavor(dish);
        redisTemplate.opsForValue().set(key,list); // 将查询到的菜品数据缓存到Redis中

        return Result.success(list);
    }

}
