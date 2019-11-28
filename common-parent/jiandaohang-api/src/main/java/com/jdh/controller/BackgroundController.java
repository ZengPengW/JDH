package com.jdh.controller;

import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.service.BackgroundService;
import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    /**
     * 保存背景图片
     *
     */
    @PostMapping("/bgImg/upload")
    public JdhResult saveBackgroundImg(@RequestBody  BackgroundImgDo backgroundImgDo){

        try {
            Long id=backgroundService.addBackgroundImg(backgroundImgDo);
            return JdhResult.success("背景图片:上传成功",backgroundImgDo.getPid());
        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("背景图片:上传失败",-1);
        }

    }

    /**
     * 根据MD5查找图片
     * @param md5
     * @return
     */
    @GetMapping("/bgImg/md5/{md5}")
    public List<BackgroundImgDo>  getBackgroundImgByMd5(@PathVariable String md5){
        try {
            return  backgroundService.getBackgroundImgByMd5(md5);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据pid获取背景图片
     * @param pid
     * @return
     */
    @GetMapping("/bgImg/pid/{pid}")
    public BackgroundImgDo getUserBackgroundImgByPid(@PathVariable Long pid) {
        return backgroundService.getUserBackgroundImgByPid(pid);
    }



    /**
     * 根据uid获取用户背景信息
     * @param uid
     * @return
     */
    @GetMapping("/bg/{uid}")
    public Background getUserBackgroundById(@PathVariable Integer uid) {
        return backgroundService.getUserBackgroundById(uid);
    }

    /**
     * 设置当前用户背景信息
     */
    @PostMapping("/bg/add")
    public void saveBackground(@RequestBody  Background background){
        backgroundService.addBackground(background);
    }

    /**
     * 更新当前用户背景图片
     */
    @PostMapping("/bg/update")
    public void updateBackground(@RequestBody Background background){
        backgroundService.updateBackground(background);
    }



}
