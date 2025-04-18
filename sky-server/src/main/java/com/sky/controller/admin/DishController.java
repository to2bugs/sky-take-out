package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜品管理
 */
@Api(tags = "菜品管理相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @ApiOperation("新增菜品")
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        // 清除缓存数据
        redisTemplate.delete("dish_" + dishDTO.getCategoryId());
        return Result.success();
    }


    /**
     * 菜品分页查询
     */
    @ApiOperation("菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 批量删除菜品
     */
    @ApiOperation("菜品的批量删除")
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        log.info("菜品的批量删除: {}", ids);
        dishService.deleteBatch(ids);
        // 将所有的菜品缓存数据清楚掉，即清除掉key以dish_开头的所有数据
        redisTemplate.delete(redisTemplate.keys("dish_*"));
        return Result.success();
    }


    /**
     * 根据id查询菜品
     */
    @ApiOperation("根据id查询菜品")
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品: {}", id);
        DishVO dishVo = dishService.getByIdWithFlavor(id);
        return Result.success(dishVo);
    }


    /**
     * 修改菜品
     */
    @ApiOperation("修改菜品")
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品: {}", dishDTO);
        dishService.updateWidthFlavor(dishDTO);
        // 将所有的菜品缓存数据清楚掉，即清除掉key以dish_开头的所有数据
        redisTemplate.delete(redisTemplate.keys("dish_*"));
        return Result.success();
    }

    /**
     * 清理Redis缓存数据
     * @param partten
     */
    private void cleanRedisCache(String partten) {
        Set keys = redisTemplate.keys(partten);
        redisTemplate.delete(keys);
    }
}
