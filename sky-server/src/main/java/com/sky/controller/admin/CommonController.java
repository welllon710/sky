package com.sky.controller.admin;


import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/admin/common")
@RestController
@Slf4j
public class CommonController {
    private static final String URL_PREFIX = "http://localhost:8080/images/";
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        log.info("file{} ", file);
        String path = "/Users/ww/Documents/uploadFile/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        // 保存文件到本地
        file.transferTo(dest);
        return Result.success(URL_PREFIX + fileName);
    }
}
