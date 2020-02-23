package com.jdh.controller;

import com.jdh.pojo.PlayerImgDo;
import com.jdh.pojo.PlayerMusicDo;
import com.jdh.pojo.PlayerStyleDo;
import com.jdh.service.PlayerService;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * 根据MD5 获取图片
     * @param md5
     * @return
     */
    @GetMapping("/player/img/md5/{md5}")
    public List<PlayerImgDo> getPlayerImgDoByMd5(@PathVariable(name = "md5") String md5){
        return playerService.getPlayerImgDoByMd5(md5);
    }

    /**
     * 增加播放器样式
     * @param playerImgDo
     * @return
     */
    @PostMapping("/player/img/save")
    public JdhResult addPlayerImgDo(@RequestBody PlayerImgDo playerImgDo){
        try {
            playerService.addPlayerImgDo(playerImgDo);
            if(playerImgDo.getPid()!=null){
                return JdhResult.success(playerImgDo.getPid());
            }else {
                return JdhResult.fail("错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("错误");
        }
    }

    /**
     * 修改播放器映射表
     * @param playerStyleDo
     * @return
     */
    @PostMapping("/player/style/change")
    public Integer changePlayerStyle(@RequestBody PlayerStyleDo playerStyleDo){
        return playerService.changePlayerStyle(playerStyleDo);
    }

    /**
     * 保存播放器映射表
     * @param playerStyleDo
     * @return
     */
    @PostMapping("player/style/save")
    public Integer addPlayerStyle(@RequestBody PlayerStyleDo playerStyleDo){
        return playerService.addPlayerStyle(playerStyleDo);
    }

    /**
     * 根据pid 获取播放器映射信息
     * @param uid
     * @return
     */
    @GetMapping("/player/style/uid/{uid}")
    public PlayerStyleDo getPlayerStyleDoByUid(@PathVariable(name = "uid") Integer uid){
        return playerService.getPlayerStyleDoByUid(uid);
    }

    /**
     * 更新图片
     * @param playerImgDo
     * @return
     */
    @PostMapping("/player/img/update")
    public Integer updatePlayerImgDo(@RequestBody PlayerImgDo playerImgDo){
        return playerService.updatePlayerImgDo(playerImgDo);
    }


    /**
     * 根据uid 获取图片信息
     * @param uid
     * @return
     */
    @GetMapping("/player/img/uid/{uid}")
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoByUid(@PathVariable(name = "uid") Integer uid, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer size){
        return playerService.getPlayerImgDoByUid(uid,page,size);
    }

    /**
     * 根据MD5删除图标
     * @param md5
     * @return
     */
    @PostMapping("/player/img/del/md5/{md5}")
    public JdhResult deletePlayerImgDoByMd5(@PathVariable(name = "md5") String md5){
        Integer integer = playerService.deletePlayerImgDoByMd5(md5);
        if (integer>0){
            return JdhResult.success("删除成功！！！");
        }else {
            return JdhResult.fail("删除失败");
        }
    }

    /**
     * 根据pid获取图片信息
     * @param pid
     * @return
     */
    @GetMapping("/player/style/pid/{pid}")
    public List<PlayerStyleDo> getPlayerStyleDoByPid(@PathVariable(name = "pid") Long pid){
        return playerService.getPlayerStyleDoByPid(pid);
    }

    /**
     * 获取所有播放器图标
     * map  存在：order asc/desc  ispublis true/false
     * @return
     */
    @GetMapping("/player/share/{share}")
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoAll(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "size",required = false)Integer size,@RequestParam(value = "field",required = false)String field,@RequestParam(value = "order",required = false) String order,@PathVariable(name = "share")Boolean ispublic){
        return playerService.getPlayerImgDoAll(page,size,field,order,ispublic);
    }

    /**
     * 根据pid获取图标
     * @param pid
     * @return
     */
    @GetMapping("/player/img/pid/{pid}")
    public List<PlayerImgDo> getPlayerImgDoByPid(@PathVariable(name = "pid") Long pid) {
        return playerService.getPlayerImgDoByPid(pid);
    }


    //--------------------背景音乐------------------------------


    /**
     * 根据uid查询用户背景音乐信息
     * @param uid
     * @return
     */

    @GetMapping("/player/music/uid/{uid}")
    public List<PlayerMusicDo> getPlayerMusicDoByUid(@PathVariable(name = "uid") Integer uid){
        return playerService.getPlayerMusicDoByUid(uid);
    }

    /**
     * 添加用户背景音乐信息
     * @param playerMusicDo
     * @return
     */
    @PostMapping("/player/music/add")
    public Integer addPlayerMusicDo(@RequestBody PlayerMusicDo playerMusicDo){
        return playerService.addPlayerMusicDo(playerMusicDo);
    }

    /**
     * 修改用户背景音乐信息
     * @param playerMusicDo
     * @return
     */
    @PostMapping("/player/music/change")
    public Integer changePlayerMusicDo(@RequestBody PlayerMusicDo playerMusicDo){
        return playerService.changePlayerMusicDo(playerMusicDo);
    }

    @PostMapping("/player/music/del/uid/{uid}")
    public Integer deletePlayerMusicDoByUid(@PathVariable(name = "uid") Integer uid){
        return playerService.deletePlayerMusicDoByUid(uid);
    }
}
