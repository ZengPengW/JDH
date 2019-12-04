package com.jdh.controller;

import com.jdh.feign.BackgroundService;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import com.jdh.utils.UserContext;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    /**
     * 我的背景图上传分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/bgImg/myUpLoad")
    public PageDataGridResult getUserBackgroundImgByUid( @RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false )Integer size){
        MyUser currUser = UserContext.getCurrUser();
        return backgroundService.getUserBackgroundImgByUid(currUser.getId(),page,size);
    }

    /**
     * 根据图片MD5更改用户背景图
     * @param md5
     * @return
     */
    @PostMapping ("/bgImg/change/md5/{md5}")
    public JdhResult changeUserBackgroundImgByMd5(@PathVariable(name = "md5",required = true) String md5){

        try {
            List<BackgroundImgDo> imgByMd5 = backgroundService.getBackgroundImgByMd5(md5);
            //如果有此图 就设置
            if (imgByMd5!=null&&imgByMd5.size()>0){
                MyUser user=UserContext.getCurrUser();
                BackgroundImgDo backgroundImgDo = imgByMd5.get(0);
                Background background = backgroundService.getUserBackgroundById(user.getId());
                //用户无背景 就添加
                if(background==null){
                    background=new Background();
                    background.setPid(backgroundImgDo.getPid());
                    background.setUid(user.getId());
                    backgroundService.saveBackground(background);
                } else { //用户有背景 就更新
                    background.setPid(backgroundImgDo.getPid());
                    backgroundService.updateBackground(background);
                }
              return JdhResult.success("背景图片:修改成功!");
            }else {
              return  JdhResult.fail("无此背景图: 背景图片设置失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  JdhResult.fail("服务器错误: 背景图片设置失败！");
        }


    }

}
