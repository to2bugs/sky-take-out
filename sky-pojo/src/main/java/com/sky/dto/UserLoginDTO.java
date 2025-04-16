package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
// @ApiModel
public class UserLoginDTO implements Serializable {

    // @ApiModelProperty(value = "微信授权码，只能使用一次")
    private String code;

}
