package com.jdh.service;

import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.utils.PageDataGridResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MouseStyleService {
    /**
     * 增加鼠标图标
     * @param mouseImgDo
     * @return
     */
    public Integer addMouseImg(MouseImgDo mouseImgDo);

    /**
     * 保存用户鼠标样式映射
     * @param mouseStyleDo
     * @return
     */
    public Integer saveMouseStyleDo(MouseStyleDo mouseStyleDo);

    /**
     * 更新用户鼠标样式
     * @param mouseStyleDo
     * @return
     */
    public Integer updateMouseStyleDo(MouseStyleDo mouseStyleDo);

    /**
     * 根据MD5 查询图标
     * @param md5
     * @return
     */
    public List<MouseImgDo> getMouseImgDoByMd5(String md5);

    /**
     * 更新鼠标图标
     * @param mouseImgDo
     * @return
     */
    public Integer updateMouseImgDo(MouseImgDo mouseImgDo);

    /**
     * 根据uid 获取映射表
     * @param uid
     * @return
     */
    public MouseStyleDo getMouseStyleDoByUid(Integer uid);

    /**
     * 根据mid 删除图标
     * @param mid
     * @return
     */
    public Integer deleteMouseImgDoByMid(Long mid);

    /**
     * 查询我的上传图标
     * @param mouseType 0 指针 1 手指 or 2都可以
     * @param uid 用户id
     * @return
     */
    public PageDataGridResult<MouseImgDo> getMouseImgDoByMyUpload(Integer mouseType, Integer uid, Integer page, Integer size);

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
    public PageDataGridResult<MouseImgDo> getMouseImgDoByPublic(Integer mouseType, Boolean share, Integer page, Integer size,String field, String order);

    /**
     * 根据mid 获取图标
     * @param mid
     * @return
     */
    public MouseImgDo getMouseImgByMid(Long mid);

    /**
     * 根据mid 获取鼠标映射表
     * @param mid
     * @return
     */
    public List<MouseStyleDo> getMouseStyleDoByMid(Long mid);
}
