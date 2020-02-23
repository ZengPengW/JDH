package com.jdh.feign;

import com.jdh.pojo.FloaterImgDo;
import com.jdh.pojo.FloaterStyleDo;
import com.jdh.utils.PageDataGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "JIANDAOHANG-API")
public interface FloaterService {

    @GetMapping("/floater/list")
    public PageDataGridResult<FloaterImgDo> getFloaterImgDoAll(@RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false) Integer size, @RequestParam(name = "field",required = false) String field,@RequestParam(name = "order",required = false) String order);

    /**
     * 根据uid获取用户漂浮物映射信息
     * @param uid
     * @return
     */
    @GetMapping("/floaterStyle/uid/{uid}")
    public FloaterStyleDo getFloaterStyleDoByUid(@PathVariable(name = "uid") Integer uid);

    /**
     * 增加用户漂浮物映射信息
     * @param floaterStyleDo
     * @return
     */
    @PostMapping(value = "/floaterStyle/add",consumes = "appliction/json")
    public Integer addFloaterStyleDo(@RequestBody FloaterStyleDo floaterStyleDo);

    /**
     * 更新用户漂浮物映射信息
     * @param floaterStyleDo
     * @return
     */
    @PostMapping(value = "/floaterStyle/update",consumes = "appliction/json")
    public Integer updateFloaterStyleDo(@RequestBody  FloaterStyleDo floaterStyleDo);

    /**
     * 根据fid获取漂浮物
     * @param fid
     * @return
     */
    @GetMapping("/floater/fid/{fid}")
    public FloaterImgDo getFloaterImgDoByFid(@PathVariable(name = "fid") Long fid);
}
