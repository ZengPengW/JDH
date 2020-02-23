package com.jdh.controller;

import com.jdh.pojo.FloaterImgDo;
import com.jdh.pojo.FloaterStyleDo;
import com.jdh.service.FloaterService;
import com.jdh.utils.PageDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FloaterController  {

    @Autowired
    private FloaterService floaterService;

    /**
     * 分页查询 加 排序
     * @param page
     * @param size
     * @param field
     * @param order
     * @return
     */
    @GetMapping("/floater/list")
    public PageDataGridResult<FloaterImgDo> getFloaterImgDoAll(@RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false) Integer size, @RequestParam(name = "field",required = false) String field,@RequestParam(name = "order",required = false) String order){
        if (page==null)page=1;
        if (size==null)size=8;
        return floaterService.getFloaterImgDoAll(page,size,field,order);
    }

    /**
     * 根据uid获取用户漂浮物映射信息
     * @param uid
     * @return
     */
    @GetMapping("/floaterStyle/uid/{uid}")
    public FloaterStyleDo getFloaterStyleDoByUid(@PathVariable(name = "uid") Integer uid){
        return floaterService.getFloaterStyleDoByUid(uid);
    }

    /**
     * 增加用户漂浮物映射信息
     * @param floaterStyleDo
     * @return
     */
    @PostMapping("/floaterStyle/add")
    public Integer addFloaterStyleDo(@RequestBody FloaterStyleDo floaterStyleDo){
        return floaterService.addFloaterStyleDo(floaterStyleDo);
    }

    /**
     * 更新用户漂浮物映射信息
     * @param floaterStyleDo
     * @return
     */
    @PostMapping("/floaterStyle/update")
    public Integer updateFloaterStyleDo(@RequestBody  FloaterStyleDo floaterStyleDo){
        return floaterService.updateFloaterStyleDo(floaterStyleDo);
    }

    /**
     * 根据fid获取漂浮物
     * @param fid
     * @return
     */
    @GetMapping("/floater/fid/{fid}")
    public FloaterImgDo getFloaterImgDoByFid(@PathVariable(name = "fid") Long fid){
        if (fid==null)return null;
        return  floaterService.getFloaterImgDoByFid(fid);
    }
}
