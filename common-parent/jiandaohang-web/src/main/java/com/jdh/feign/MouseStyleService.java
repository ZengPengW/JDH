package com.jdh.feign;

import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.utils.JdhResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "JIANDAOHANG-API")
public interface MouseStyleService {

    /**
     * 增加鼠标图标
     * @param mouseImgDo
     * @return
     */
    @PostMapping(value = "/mouseImg/add",consumes = "appliction/json")
    public JdhResult addMouseImg(@RequestBody MouseImgDo mouseImgDo);

    /**
     * 保存用户鼠标样式映射
     * @param mouseStyleDo
     * @return
     */
    @PostMapping(value = "/mouseStyle/save",consumes = "appliction/json")
    public Integer saveMouseStyleDo(@RequestBody MouseStyleDo mouseStyleDo);


    /**
     * 更新用户鼠标样式
     * @param mouseStyleDo
     * @return
     */
    @PostMapping(value = "/mouseStyle/update",consumes = "appliction/json")
    public Integer updateMouseStyleDo(@RequestBody MouseStyleDo mouseStyleDo);

    /**
     * 根据MD5 查询图标
     * @param md5
     * @return
     */
    @GetMapping("/mouseImg/md5/{md5}")
    public MouseImgDo getMouseImgDoByMd5(@PathVariable(name = "md5") String md5);

    /**
     * 更新鼠标图标
     * @param mouseImgDo
     * @return
     */
    @PostMapping(value = "/mouseImg/update",consumes = "appliction/json")
    public Integer updateMouseImgDo(@RequestBody MouseImgDo mouseImgDo);

    /**
     * 根据uid 获取映射表
     * @param uid
     * @return
     */
    @GetMapping("/mouseImg/uid/{uid}")
    public MouseStyleDo getMouseStyleDoByUid(@PathVariable(name = "uid") Integer uid);

    /**
     * 根据mid 删除图标
     * @param mid
     * @return
     */
    @PostMapping("/mouseImg/del/{mid}")
    public Integer deleteMouseImgDoByMid(@PathVariable(name = "mid") Long mid);
}
