package com.jdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdh.mapper.BackgroundSpeMapper;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.service.BackgroundSpeService;
import com.jdh.utils.PageDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackgroundSpeServiceImpl implements BackgroundSpeService {

    @Autowired
    private BackgroundSpeMapper backgroundSpeMapper;

    @Override
    public PageDataGridResult<BackgroundSpeDo> getBackgroundSpeByTransparent(Boolean transparent, Integer page, Integer size, String field, String order) {
        //分页
        PageHelper.startPage(page, size);

        //条件
        Map map=new HashMap();
        map.put("transparent",transparent);
        if(field!=null&&field.trim().length()>0&&order!=null&&order.trim().length()>0)
            map.put(field,order);



        List<BackgroundSpeDo> spelist = backgroundSpeMapper.getBackgroundSpeByTransparent(map);
        PageInfo<BackgroundSpeDo> pageInfo = new PageInfo<BackgroundSpeDo>(spelist);
        PageDataGridResult<BackgroundSpeDo> dataGridResult = new PageDataGridResult<>();
        dataGridResult.setRows(spelist);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Override
    public BackgroundSpeDo getBackgroundSpeBySid(Long sid) {
        return backgroundSpeMapper.getBackgroundSpeBySid(sid);
    }
}
