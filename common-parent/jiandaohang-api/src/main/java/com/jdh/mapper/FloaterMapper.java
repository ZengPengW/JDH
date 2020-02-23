package com.jdh.mapper;

import com.jdh.pojo.FloaterImgDo;
import com.jdh.pojo.FloaterStyleDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FloaterMapper {
    /**
     * 获取所有漂浮物
     * @return
     */
    public List<FloaterImgDo> getFloaterImgDoAll(Map map);

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
