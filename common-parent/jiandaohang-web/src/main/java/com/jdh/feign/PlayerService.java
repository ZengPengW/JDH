package com.jdh.feign;

import com.jdh.pojo.PlayerImgDo;
import com.jdh.pojo.PlayerMusicDo;
import com.jdh.pojo.PlayerStyleDo;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "JIANDAOHANG-API")
public interface PlayerService {
    /**
     * 根据MD5 获取图片
     * @param md5
     * @return
     */
    @GetMapping("/player/img/md5/{md5}")
    public List<PlayerImgDo> getPlayerImgDoByMd5(@PathVariable(name = "md5") String md5);

    /**
     * 增加播放器样式
     * @param playerImgDo
     * @return
     */
    @PostMapping(value = "/player/img/save",consumes = "appliction/json")
    public JdhResult addPlayerImgDo(@RequestBody PlayerImgDo playerImgDo);

    /**
     * 修改播放器映射表
     * @param playerStyleDo
     * @return
     */
    @PostMapping(value = "/player/style/change",consumes = "appliction/json")
    public Integer changePlayerStyle(@RequestBody PlayerStyleDo playerStyleDo);

    /**
     * 保存播放器映射表
     * @param playerStyleDo
     * @return
     */
    @PostMapping(value = "player/style/save",consumes = "appliction/json")
    public Integer addPlayerStyle(@RequestBody PlayerStyleDo playerStyleDo);

    /**
     * 根据pid 获取播放器映射信息
     * @param uid
     * @return
     */
    @GetMapping("/player/style/uid/{uid}")
    public PlayerStyleDo getPlayerStyleDoByUid(@PathVariable(name = "uid") Integer uid);

    /**
     * 更新图片
     * @param playerImgDo
     * @return
     */
    @PostMapping(value = "/player/img/update",consumes = "appliction/json")
    public Integer updatePlayerImgDo(@RequestBody PlayerImgDo playerImgDo);

    /**
     * 根据uid 获取图片信息
     * @param uid
     * @return
     */
    @GetMapping("/player/img/uid/{uid}")
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoByUid(@PathVariable(name = "uid") Integer uid, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer size);

    /**
     * 根据MD5删除图标
     * @param md5
     * @return
     */
    @PostMapping("/player/img/del/md5/{md5}")
    public JdhResult deletePlayerImgDoByMd5(@PathVariable(name = "md5") String md5);

    /**
     * 根据pid获取图片信息
     * @param pid
     * @return
     */
    @GetMapping("/player/style/pid/{pid}")
    public List<PlayerStyleDo> getPlayerStyleDoByPid(@PathVariable(name = "pid") Long pid);

    /**
     * 获取所有播放器图标
     * map  存在：order asc/desc  ispublis true/false
     * @return
     */
    @GetMapping("/player/share/{share}")
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoAll(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "size",required = false)Integer size,@RequestParam(value = "field",required = false)String field,@RequestParam(value = "order",required = false) String order,@PathVariable(name = "share")Boolean ispublic);

    /**
     * 根据pid获取图标
     * @param pid
     * @return
     */
    @GetMapping("/player/img/pid/{pid}")
    public List<PlayerImgDo> getPlayerImgDoByPid(@PathVariable(name = "pid") Long pid);


    //--------------------背景音乐------------------------------


    /**
     * 根据uid查询用户背景音乐信息
     * @param uid
     * @return
     */

    @GetMapping("/player/music/uid/{uid}")
    public List<PlayerMusicDo> getPlayerMusicDoByUid(@PathVariable(name = "uid") Integer uid);

    /**
     * 添加用户背景音乐信息
     * @param playerMusicDo
     * @return
     */
    @PostMapping(value = "/player/music/add",consumes = "appliction/json")
    public Integer addPlayerMusicDo(@RequestBody PlayerMusicDo playerMusicDo);

    /**
     * 修改用户背景音乐信息
     * @param playerMusicDo
     * @return
     */
    @PostMapping(value = "/player/music/change",consumes = "appliction/json")
    public Integer changePlayerMusicDo(@RequestBody PlayerMusicDo playerMusicDo);

    /**
     * 删除一用户背景音乐
     * @param uid
     * @return
     */
    @PostMapping("/player/music/del/uid/{uid}")
    public Integer deletePlayerMusicDoByUid(@PathVariable(name = "uid") Integer uid);
}
