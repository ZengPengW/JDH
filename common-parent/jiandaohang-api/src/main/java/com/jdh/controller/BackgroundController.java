package com.jdh.controller;

import com.jdh.pojo.BackgroundImgDo;
import com.jdh.service.BackgroundService;
import com.jdh.utils.JdhResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    /**
     * 保存背景图片
     *
     */
    @PostMapping("/upload/bgImg")
    public JdhResult saveBackgroundImg(@RequestBody  BackgroundImgDo backgroundImgDo){

        try {
            backgroundService.addBackgroundImg(backgroundImgDo);
            return JdhResult.success("背景图片:上传成功");
        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("背景图片:上传失败");
        }

    }
}
