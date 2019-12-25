package com.jdh.controller;

import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.service.MouseStyleService;
import com.jdh.utils.JdhResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MouseStyleController {

    @Autowired
    private MouseStyleService mouseStyleService;

    /**
     * 增加鼠标图标
     * @param mouseImgDo
     * @return
     */
    @PostMapping("/mouseImg/add")
    public JdhResult addMouseImg(@RequestBody MouseImgDo mouseImgDo){
        try {
             mouseStyleService.addMouseImg(mouseImgDo);
            if(mouseImgDo.getMid()!=null){
                return JdhResult.success(mouseImgDo.getMid());
            }else {
                return JdhResult.fail("错误");
            }
        }catch (Exception e){
            return JdhResult.fail("错误");
        }
    }

    /**
     * 保存用户鼠标样式映射
     * @param mouseStyleDo
     * @return
     */
    @PostMapping("/mouseStyle/save")
    public Integer saveMouseStyleDo(@RequestBody MouseStyleDo mouseStyleDo){
        return mouseStyleService.saveMouseStyleDo(mouseStyleDo);
    }


    /**
     * 更新用户鼠标样式
     * @param mouseStyleDo
     * @return
     */
    @PostMapping("/mouseStyle/update")
    public Integer updateMouseStyleDo(@RequestBody MouseStyleDo mouseStyleDo){
        return mouseStyleService.updateMouseStyleDo(mouseStyleDo);
    }

    /**
     * 根据MD5 查询图标
     * @param md5
     * @return
     */
    @GetMapping("/mouseImg/md5/{md5}")
    public MouseImgDo getMouseImgDoByMd5(@PathVariable(name = "md5") String md5){
        return mouseStyleService.getMouseImgDoByMd5(md5);
    }

    /**
     * 更新鼠标图标
     * @param mouseImgDo
     * @return
     */
    @PostMapping("/mouseImg/update")
    public Integer updateMouseImgDo(@RequestBody MouseImgDo mouseImgDo){
        return mouseStyleService.updateMouseImgDo(mouseImgDo);
    }

    /**
     * 根据uid 获取映射表
     * @param uid
     * @return
     */
    @GetMapping("/mouseImg/uid/{uid}")
    public MouseStyleDo getMouseStyleDoByUid(@PathVariable(name = "uid") Integer uid){
        return mouseStyleService.getMouseStyleDoByUid(uid);
    }

    /**
     * 根据mid 删除图标
     * @param mid
     * @return
     */
    @PostMapping("/mouseImg/del/{mid}")
    public Integer deleteMouseImgDoByMid(@PathVariable(name = "mid") Long mid){
        return mouseStyleService.deleteMouseImgDoByMid(mid);
    }
}
