package com.jdh.mapper;

import com.jdh.pojo.PlayerImgDo;
import com.jdh.pojo.PlayerMusicDo;
import com.jdh.pojo.PlayerStyleDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlayerMapper {

    /**
     * 根据MD5 获取图片
     * @param md5
     * @return
     */
    public List<PlayerImgDo> getPlayerImgDoByMd5(String md5);

    /**
     * 增加播放器样式
     * @param playerImgDo
     * @return
     */
    public Integer addPlayerImgDo(PlayerImgDo playerImgDo);

    /**
     * 修改播放器映射表
     * @param playerStyleDo
     * @return
     */
    public Integer changePlayerStyle(PlayerStyleDo playerStyleDo);

    /**
     * 保存播放器映射表
     * @param playerStyleDo
     * @return
     */
    public Integer addPlayerStyle(PlayerStyleDo playerStyleDo);

    /**
     * 根据uid 获取播放器映射信息
     * @param uid
     * @return
     */
    public PlayerStyleDo getPlayerStyleDoByUid(Integer uid);

    /**
     * 更新图片
     * @param playerImgDo
     * @return
     */
    public Integer updatePlayerImgDo(PlayerImgDo playerImgDo);

    /**
     * 根据uid 获取图片信息
     * @param uid
     * @return
     */
    public List<PlayerImgDo> getPlayerImgDoByUid(Integer uid);

    /**
     * 根据MD5删除图标
     * @param md5
     * @return
     */
    public Integer deletePlayerImgDoByMd5(String md5);

    /**
     * 根据pid获取图片信息
     * @param pid
     * @return
     */
    public List<PlayerStyleDo> getPlayerStyleDoByPid(Long pid);


    /**
     * 获取所有播放器图标
     * map  存在：order asc/desc  ispublis true/false
     * @return
     */
    public List<PlayerImgDo> getPlayerImgDoAll(Map map);

    /**
     * 根据pid获取图标
     * @param pid
     * @return
     */
    public List<PlayerImgDo> getPlayerImgDoByPid(Long pid);



    //--------------------背景音乐------------------------------


    /**
     * 根据uid查询用户背景音乐信息
     * @param uid
     * @return
     */
    public List<PlayerMusicDo> getPlayerMusicDoByUid(Integer uid);

    /**
     * 添加用户背景音乐信息
     * @param playerMusicDo
     * @return
     */
    public Integer addPlayerMusicDo(PlayerMusicDo playerMusicDo);

    /**
     * 修改用户背景音乐信息
     * @param playerMusicDo
     * @return
     */
    public Integer changePlayerMusicDo(PlayerMusicDo playerMusicDo);

    /**
     * 删除用户的背景音乐
     * @param uid
     * @return
     */
    public Integer deletePlayerMusicDoByUid(Integer uid);

}
