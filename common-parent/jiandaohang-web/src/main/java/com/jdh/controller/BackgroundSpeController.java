package com.jdh.controller;

import com.jdh.feign.BackgroundService;
import com.jdh.feign.BackgroundSpeService;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 背景特效 controller
 */
@RestController
public class BackgroundSpeController {

    @Autowired
    private BackgroundSpeService backgroundSpeService;

    @Autowired
    private BackgroundService backgroundService;

    /**
     * 是否为空
     * @param str
     * @return 是true 否 false
     */
    public boolean isEmpty(String str){
        if(str==null)return true;
        return str.trim().length() <= 0;
    }



    /**
     * 获取所有背景特效
     * @param transparent 是否透明 必选
     * @param page 第几页 可选
     * @param size 每页几条 可选
     * @param field 排序字段名 可选
     * @param order asc/desc 排序规则 可选
     * @return
     */
    @GetMapping("/bgSpe/transparent/{transparent}")
    public PageDataGridResult<BackgroundSpeDo> getBackgroundSpeByTransparent(@PathVariable(name = "transparent") Boolean transparent, @RequestParam(required = false,name = "page") Integer page, @RequestParam(required = false,name = "size")Integer size, @RequestParam(required = false,name = "field")String field, @RequestParam(required = false,name = "order")String order){
        //两个值必须同时存在 否则不排序
        if(isEmpty(field)||isEmpty(order)){
            field=null;
            order=null;
        }else {
            field=field.trim();
            order=order.trim();

            if((!field.equals("1")&&!field.equals("2"))||(!order.equals("1")&&!order.equals("2"))){
                field=null;
                order=null;
            }else {
                //1.根据上传时间排序 2.根据热度排序
                if(field.equals("1"))field="up_date";
                else field="use_count";

                //1.asc 2.desc
                if(order.equals("1"))order="asc";
                else order="desc";
            }


        }

        return  backgroundSpeService.getBackgroundSpeByTransparent(transparent,page,size,field,order);

    }

//    /**
//     * 根据sid查询背景特效
//     * @param sid
//     * @return
//     */
//    @GetMapping("/bgSpe/sid/{sid}")
//    public BackgroundSpeDo getBackgroundSpeBySid(@PathVariable Long sid){
//        return backgroundSpeService.getBackgroundSpeBySid(sid);
//    }

    /**
     * 根据sid改变背景特效
     * @param sid
     * @return
     */
    @PostMapping ("/bgSpe/change/{sid}")
    public JdhResult changeBackgroundSpeBySid(@PathVariable Long sid){
         try {
             //获取当前用户
             MyUser currUser = UserContext.getCurrUser();

             if(sid==null||currUser==null)throw  new RuntimeException("参数错误,保存失败!");
             BackgroundSpeDo backgroundSpe = backgroundSpeService.getBackgroundSpeBySid(sid);
             if(backgroundSpe!=null){
                 //获取当前用户背景
                 Background background = backgroundService.getUserBackgroundById(currUser.getId());
                 //用户无背景 就添加
                 if(background==null){
                     background=new Background();
                     background.setSid(backgroundSpe.getSid());//设置此用户对应的 sid
                     background.setUid(currUser.getId());
                     backgroundService.saveBackground(background);
                 } else { //用户有背景 就更新
                     background.setSid(backgroundSpe.getSid());
                     backgroundService.updateBackground(background);
                 }
                 return JdhResult.success("背景特效，修改成功！！！");
             }else {
                 return JdhResult.fail("不存在此特效,特效保存失败!");
             }


         }catch (Exception e){
           return JdhResult.fail("参数错误,保存失败!");
        }



    }

}
