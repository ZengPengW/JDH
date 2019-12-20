package com.jdh.mapper;

import com.jdh.pojo.SearchBoxDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchBoxMapper {
    /**
     * 根据用户id 获取用户搜索框样式
     * @param uid
     * @return
     */
    public SearchBoxDo getSearchBoxByUid(Integer uid);

    /**
     * 保存搜索框样式信息
     * @param searchBoxDo
     * @return
     */
    public Integer saveSearchBox(SearchBoxDo searchBoxDo);

    /**
     * 更新搜索框样式信息
     * @param searchBoxDo
     * @return
     */
    public Integer updateSearchBox(SearchBoxDo searchBoxDo);



}
