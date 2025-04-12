package com.sky.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品口味
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Api(tags = "口味")
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("口味ID 主键")
    private Long id;
    //菜品id
    @ApiModelProperty("菜品id")
    private Long dishId;

    //口味名称
    @ApiModelProperty("口味名称")
    private String name;

    //口味数据list
    @ApiModelProperty("口味值")
    private String value;

}
