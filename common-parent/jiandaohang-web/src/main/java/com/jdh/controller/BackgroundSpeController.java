package com.jdh.controller;

import com.jdh.feign.BackgroundSpeService;
import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.utils.PageDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 背景特效 controller
 */
@RestController
public class BackgroundSpeController {

    @Autowired
    private BackgroundSpeService backgroundSpeService;


    /**
     * 是否为空
     * @param str
     * @return 是true 否 false
     */
    public boolean isEmpty(String str){
        if(str==null)return true;
        if(str.trim().length()<=0)return true;
        return false;
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

}
