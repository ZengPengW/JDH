package com.jdh.feign;

import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<MouseImgDo> getMouseImgDoByMd5(@PathVariable(name = "md5") String md5);

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

    /**
     * 查询我的上传图标
     * @param mouseType 0 指针 1 手指 or 2都可以
     * @param uid 用户id
     * @return
     */
    @GetMapping("/mouseImg/myUpload")
    public PageDataGridResult<MouseImgDo> getMouseImgDoByMyUpload(@RequestParam("mouseType") Integer mouseType, @RequestParam("uid")Integer uid, @RequestParam(required = false,name = "page") Integer page, @RequestParam(required = false,name = "size")Integer size);

    /**
     *
     * * 查询共享图标
     * @param mouseType 0 指针 1 手指 or 2都可以
     * @param share 是否共享 true false
     * @param page 第几页
     * @param size 多少条
     * @param field 数据库表字段名
     * @param order 排序规则 asc desc
     * @return
     */
    @GetMapping("/mouseImg/share/{share}")
    public PageDataGridResult<MouseImgDo> getMouseImgDoByPublic(@RequestParam("mouseType") Integer mouseType, @PathVariable(name = "share") Boolean share,@RequestParam(value = "page",required = false) Integer page,@RequestParam(value = "size",required = false) Integer size,@RequestParam(value = "field",required = false)String field, @RequestParam(value = "order",required = false)String order);

    /**
     * 根据mid 获取图标
     * @param mid
     * @return
     */
    @GetMapping("/mouseImg/mid/{mid}")
    public MouseImgDo getMouseImgByMid(@PathVariable(name = "mid") Long mid);

    /**
     * 根据mid 获取鼠标映射表
     * @param mid
     * @return
     */
    @GetMapping("/mouseStyle/mid/{mid}")
    public List<MouseStyleDo> getMouseStyleDoByMid(@PathVariable(name = "mid") Long mid);
}
