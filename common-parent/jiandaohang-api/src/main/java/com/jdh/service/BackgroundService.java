package com.jdh.service;

import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.BackgroundInfo;
import com.jdh.utils.PageDataGridResult;

import java.util.List;

public interface BackgroundService {
    /**
     * 新增背景图片
     */
    public Long addBackgroundImg(BackgroundImgDo backgroundImgDo);

    /**
     * 根据MD5获取背景图片
     * @param md5
     * @return
     */
    public List<BackgroundImgDo> getBackgroundImgByMd5(String md5);

    /**
     * 根据id获取用户背景信息
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
    public PageDataGridResult getUserBackgroundImgByUid(Integer author_id, Integer page, Integer size);

    /**
     * 根据pid背景图删除记录
     * @param pid
     * @return
     */
    public Integer deleteBackgroundImgByPid(Long pid);


    /**
     * 根据pid获取使用此背景图的用户信息
     * @param pid
     * @return
     */
    public List<Background> getUserBackgroundByPid(Long pid);

    /**
     * 根据是否公开查询背景图片
     * @param ispublic 是否公开
     * @param page 第几页
     * @param size 每页几条
     * @param field  排序字段
     * @param order asc or desc
     * @return
     */
    public PageDataGridResult<BackgroundImgDo> getBackgroundImgByIsPublic(Boolean ispublic,Integer page,Integer size,String field,String order);

    /**
     * 根据pid增加背景图片使用次数
     * @param pid
     * @return
     */
    public Integer addBackgroundImgUseCountByPid(Long pid);
}
