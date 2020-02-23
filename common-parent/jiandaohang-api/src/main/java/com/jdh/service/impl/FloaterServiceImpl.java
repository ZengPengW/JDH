package com.jdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdh.mapper.FloaterMapper;
import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.pojo.FloaterImgDo;
import com.jdh.pojo.FloaterStyleDo;
import com.jdh.service.FloaterService;
import com.jdh.utils.PageDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FloaterServiceImpl implements FloaterService {

    @Autowired
    private FloaterMapper floaterMapper;


    @Override
    public PageDataGridResult<FloaterImgDo> getFloaterImgDoAll(Integer page,Integer size,String field,String order) {
        //分页
        PageHelper.startPage(page, size);

        //条件
        Map map=new HashMap();
        if(field!=null&&field.trim().length()>0&&order!=null&&order.trim().length()>0)
            map.put(field,order);

        List<FloaterImgDo> floatlist = floaterMapper.getFloaterImgDoAll(map);
        PageInfo<FloaterImgDo> pageInfo = new PageInfo<FloaterImgDo>(floatlist);
        PageDataGridResult<FloaterImgDo> dataGridResult = new PageDataGridResult<>();
        dataGridResult.setRows(floatlist);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Override
    public FloaterImgDo getFloaterImgDoByFid(Long fid) {
        return floaterMapper.getFloaterImgDoByFid(fid);
    }

    @Override
    public FloaterStyleDo getFloaterStyleDoByUid(Integer uid) {
        return floaterMapper.getFloaterStyleDoByUid(uid);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer addFloaterStyleDo(FloaterStyleDo floaterStyleDo) {
        return floaterMapper.addFloaterStyleDo(floaterStyleDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer updateFloaterStyleDo(FloaterStyleDo floaterStyleDo) {
        return floaterMapper.updateFloaterStyleDo(floaterStyleDo);
    }
}
