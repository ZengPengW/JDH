package com.jdh.controller;

import com.jdh.feign.FloaterService;
import com.jdh.pojo.FloaterImgDo;
import com.jdh.pojo.FloaterStyleDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FloaterController {

    @Autowired
    private FloaterService floaterService;

    @GetMapping("/floater/list")
    public PageDataGridResult<FloaterImgDo> getFloaterImgDoAll(@RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false) Integer size, @RequestParam(name = "field",required = false) String field,@RequestParam(name = "order",required = false) String order){        //两个值必须同时存在 否则不排序
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

        return floaterService.getFloaterImgDoAll(page,size,field,order);
    }


    @PostMapping("/floaterStyle/save/fid/{fid}")
    public JdhResult saveFloaterStyle(@PathVariable(name = "fid") Long fid){
        try {
            FloaterImgDo floaterImgDoByFid = floaterService.getFloaterImgDoByFid(fid);
            if (floaterImgDoByFid==null)return JdhResult.fail("此漂浮物不存在,保存失败！");
            MyUser currUser = UserContext.getCurrUser();

            //获取用户映射信息
            FloaterStyleDo floaterStyleDo = floaterService.getFloaterStyleDoByUid(currUser.getId());

            //没有就保存  有就更新
            if (floaterStyleDo!=null){
                floaterStyleDo.setFid(fid);
                floaterService.updateFloaterStyleDo(floaterStyleDo);
            }else {
                floaterStyleDo=new FloaterStyleDo();
                floaterStyleDo.setFid(fid);
                floaterStyleDo.setUid(currUser.getId());
                floaterService.addFloaterStyleDo(floaterStyleDo);
            }
            return JdhResult.success("漂浮物保存成功！");

        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("服务器错误保存失败！");
        }
    }


    /**
     * 是否为空
     * @param str
     * @return 是true 否 false
     */
    private boolean isEmpty(String str){
        if(str==null)return true;
        return str.trim().length() <= 0;
    }
}
