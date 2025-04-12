package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口之文件上传
 *      https://otto-take-out.oss-cn-shanghai.aliyuncs.com/6db9cb31-c9c3-4633-9083-88e4b8a5cf6f.png
 *      https://otto-take-out.https://oss-cn-shanghai.aliyuncs.com/6db9cb31-c9c3-4633-9083-88e4b8a5cf6f.png
 *
 *      https://otto-take-out.oss-cn-shanghai.aliyuncs.com/34f9be08-217a-47fb-9b00-7a161969afe5.25.37.png
 *      https://otto-take-out.oss-cn-shanghai.aliyuncs.com/34f9be08-217a-47fb-9b00-7a161969afe5.25.37.png
 */
@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(@RequestBody MultipartFile file){
        log.info("文件上传");


        try {
            int index = file.getOriginalFilename().indexOf(".");
            String suffix = file.getOriginalFilename().substring(index);
            String fileName = UUID.randomUUID().toString() + suffix;
            log.info("上传文件名：{}", fileName);

            String filePath = aliOssUtil.upload(file.getBytes(), fileName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
