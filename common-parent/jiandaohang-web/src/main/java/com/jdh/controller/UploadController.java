package com.jdh.controller;

import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

@RestController
public class UploadController {

    @Value("${myparameter.uploadPath}")
    private String materialPath;//d:/zp/material/


    @ResponseBody
    @PostMapping("/upload/bg")
    public JdhResult upload(@RequestParam("file") MultipartFile file) {

        Map map;
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);//获取唯一名称
        //\img\bg 目录分级算法
        String savePath=materialPath+"img"+ File.separator+"bg"+FileUtil.getDir(fileName);
        try {
            FileUtil.uploadFile(file.getBytes(),savePath , fileName);


        } catch (Exception e) {
            return null;
        }


    }
}
