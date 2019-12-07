package com.jdh.controller;

import com.jdh.feign.BackgroundService;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import com.jdh.utils.UserContext;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    @Value("${myparameter.uploadPath}")
    public String materialPath;//d:/zp/material/
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

    /**
     * 根据md5删除背景图片
     * @param md5
     * @return
     */

    @PostMapping("/bgImg/del/md5/{md5}")
    public JdhResult deleteBackgroundImgByMd5(@PathVariable String md5){
        try {
            MyUser user=UserContext.getCurrUser();
            if(user==null||user.getId()==null)return JdhResult.fail("操作失败，用户此无权限！！！");
            List<BackgroundImgDo> imgByMd5 = backgroundService.getBackgroundImgByMd5(md5);
            //如果查无此图
            if (imgByMd5==null||imgByMd5.size()<1){
                return JdhResult.fail("此背景图不存在,删除失败!!!");
            }else {
                BackgroundImgDo imgDo = imgByMd5.get(0);
                //如果是此用户上传的就可以删除
                if (user.getId()==imgDo.getAuthorId()){
                    //获得使用此背景图的所有用户
                    List<Background> background = backgroundService.getUserBackgroundByPid(imgDo.getPid());
                    //如果无人使用此图就可以删除
                    if (background==null||background.size()<=0){
                        backgroundService.deleteBackgroundImgByPid(imgDo.getPid());
                        String pic = imgDo.getPic();//图片地址
                        String minpic=imgDo.getThumbnail();//缩略图
                        pic=materialPath+pic.substring(10);
                        minpic=materialPath+minpic.substring(10);
                      //  System.out.println(pic);
                       // System.out.println(minpic);
                        FileUtil.deleteFile(pic);
                        FileUtil.deleteFile(minpic);
                        return JdhResult.success("删除成功！！！");
                    }else {
                        return JdhResult.fail("此图有用户正在使用，暂时无法删除！！！");
                    }

                }else {
                    return JdhResult.fail("此背景图不是你上传的,删除失败!!!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("服务器错误,删除失败!!!");

        }

    }

}
