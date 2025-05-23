package com.sky.dto;

import com.sky.entity.DishFlavor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Api(tags = "菜品")
public class DishDTO implements Serializable {
    @ApiModelProperty("菜品id")
    private Long id;
    //菜品名称
    @ApiModelProperty("菜品名称")
    private String name;
    //菜品分类id
    @ApiModelProperty("菜品分类id")
    private Long categoryId;
    //菜品价格
    @ApiModelProperty("菜品价格")
    private BigDecimal price;
    //图片
    @ApiModelProperty("菜品图片路径")
    private String image;
    //描述信息
    @ApiModelProperty("描述信息")
    private String description;
    //0 停售 1 起售
    @ApiModelProperty("菜品状态 0 停售 1 起售")
    private Integer status;
    //口味
    @ApiModelProperty("菜品口味")
    private List<DishFlavor> flavors = new ArrayList<>();

}
