package com.jdh.mapper;

import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.BackgroundInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackgroundMapper {

    /**
     *返回id
     */
    public Long addBackgroundImg(BackgroundImgDo backgroundImgDo);


    /**
     * 根据MD5获取背景图片
     * @param md5
     * @return
     */
    public List<BackgroundImgDo> getBackgroundImgByMd5(String md5);


    /**
     * 根据uid获取用户背景信息
     * @param uid
     * @return
     */
    public Background getUserBackgroundById(Integer uid);


    /**
     * 设置当前用户背景信息
     */
    public void addBackground(Background background);

    /**
     * 更新当前用户背景图片
     */
    public void updateBackground(Background background);

    /**
     * 根据pid获取图片
     * @param pid
     * @return
     */
    public BackgroundImgDo getUserBackgroundImgByPid(Long pid);


    /**
     * 根据用户id获取上传的背景图片
     * @param author_id
     * @return
     */
    public List<BackgroundImgDo> getUserBackgroundImgByUid(Integer author_id);
}
