package com.jdh.service;

import com.jdh.pojo.FloaterImgDo;
import com.jdh.pojo.FloaterStyleDo;
import com.jdh.utils.PageDataGridResult;

import java.util.List;

public interface FloaterService {
    /**
     * 获取所有漂浮物
     * @param page 页码
     * @param size 条数
     * @param field 字符段
     * @param order 排序 asc / desc
     * @return
     */
    public PageDataGridResult<FloaterImgDo> getFloaterImgDoAll(Integer page,Integer size,String field,String order);

    /**
     * 根据fid获取漂浮物
     * @param fid
     * @return
     */
    public FloaterImgDo getFloaterImgDoByFid(Long fid);

    /**
     * 根据uid获取用户漂浮物映射信息
     * @param uid
     * @return
     */
    public FloaterStyleDo getFloaterStyleDoByUid(Integer uid);

    /**
     * 增加用户漂浮物映射信息
     * @param floaterStyleDo
     * @return
     */
    public Integer addFloaterStyleDo(FloaterStyleDo floaterStyleDo);

    /**
     * 更新用户漂浮物映射信息
     * @param floaterStyleDo
     * @return
     */
    public Integer updateFloaterStyleDo(FloaterStyleDo floaterStyleDo);
}
