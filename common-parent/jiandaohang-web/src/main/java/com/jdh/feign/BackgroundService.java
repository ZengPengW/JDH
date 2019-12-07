package com.jdh.feign;

import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "JIANDAOHANG-API")
public interface BackgroundService {
    /**
     * 保存背景图片
     * @param backgroundImgDo
     * @return
     */
    @PostMapping(value = "/bgImg/upload",consumes = "appliction/json")
    public JdhResult saveBackgroundImg(@RequestBody BackgroundImgDo backgroundImgDo);

    /**
     * 根据MD5获取图片
     * @param md5
     * @return
     */
    @GetMapping("/bgImg/md5/{md5}")
    public List<BackgroundImgDo> getBackgroundImgByMd5(@PathVariable(name = "md5") String md5);

    /**
     * 获取背景信息 根据uid
     * @param uid
     * @return
     */

    @GetMapping("/bg/{uid}")
    public Background getUserBackgroundById(@PathVariable(name = "uid") Integer uid) ;

    /**
     * 设置当前用户背景信息
     */
    @PostMapping(value = "/bg/add",consumes = "appliction/json")
    public void saveBackground(@RequestBody  Background background);

    /**
     * 更新当前用户背景图片
     */
    @PostMapping(value = "/bg/update",consumes = "appliction/json")
    public void updateBackground(@RequestBody Background background);

    /**
     * 根据pid获取背景图片
     * @param pid
     * @return
     */
    @GetMapping("/bgImg/pid/{pid}")
    public BackgroundImgDo getUserBackgroundImgByPid(@PathVariable(name = "pid") Long pid);

    /**
     * 根据用户id获取上传的背景图片
     * @param author_id
     * @return
     */
    @GetMapping("/bgImg/authorId/{author_id}")
    public PageDataGridResult getUserBackgroundImgByUid(@PathVariable(name = "author_id") Integer author_id, @RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false )Integer size);


    /**
     * 根据pid背景图删除记录
     * @param pid
     * @return
     */
    @PostMapping("/bgImg/delete/{pid}")
    public Integer deleteBackgroundImgByPid(@PathVariable(name = "pid") Long pid);

    /**
     * 根据pid获取使用此背景图的用户信息
     * @param pid
     * @return
     */
    @GetMapping("/bg/pid/{pid}")
    public List<Background> getUserBackgroundByPid(@PathVariable(name = "pid") Long pid);
}
