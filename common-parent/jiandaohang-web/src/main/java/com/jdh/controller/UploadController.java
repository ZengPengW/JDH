package com.jdh.controller;

import com.jdh.feign.BackgroundService;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String materialPath;//d:/zp/material/

    @Autowired
    public BackgroundService backgroundService;


    @PostMapping("/upload/bgImg")
    public JdhResult upload(@RequestParam("picFileUp") MultipartFile file) {

            MyUser currUser = UserContext.getCurrUser();

            String filePath=null;
            try {
                String fileName = file.getOriginalFilename();
                fileName = FileUtil.renameToUUID(fileName);//获取唯一名称
                //\img\bg 目录分级算法
                String dir=FileUtil.getDir(fileName);
                String savePath=materialPath+"img"+ File.separator+"bg"+dir;
                FileUtil.uploadFile(file.getBytes(),savePath , fileName);//保存到磁盘
                String pic=File.separator+"material"+File.separator+"img"+File.separator+"bg"+dir+fileName;//图片地址
                filePath=savePath+fileName;//文件路劲


                BackgroundImgDo backgroundImgDo=new BackgroundImgDo();
                backgroundImgDo.setAuthorId(currUser.getId());
                backgroundImgDo.setPic(pic);

                return  backgroundService.saveBackgroundImg(backgroundImgDo);

            } catch (Exception e) {
                e.printStackTrace();
                FileUtil.deleteFile(filePath);
                return JdhResult.fail("背景图片:系统错误操作失败！！！");
            }



    }
}
