package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value = "用户的标识")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("微信openid，用户的唯一标识")
    private String openid;
    @ApiModelProperty("用户jwt")
    private String token;

}
