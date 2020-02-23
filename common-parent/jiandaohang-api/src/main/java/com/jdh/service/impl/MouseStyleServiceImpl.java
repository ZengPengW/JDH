package com.jdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdh.mapper.MouseStyleMapper;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.service.MouseStyleService;
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
public class MouseStyleServiceImpl implements MouseStyleService {

    @Autowired
    private MouseStyleMapper mouseStyleMapper;

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer addMouseImg(MouseImgDo mouseImgDo) {
        return mouseStyleMapper.addMouseImg(mouseImgDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer saveMouseStyleDo(MouseStyleDo mouseStyleDo) {
        return mouseStyleMapper.saveMouseStyleDo(mouseStyleDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer updateMouseStyleDo(MouseStyleDo mouseStyleDo) {
        return mouseStyleMapper.updateMouseStyleDo(mouseStyleDo);
    }

    @Override
    public List<MouseImgDo> getMouseImgDoByMd5(String md5) {
        return mouseStyleMapper.getMouseImgDoByMd5(md5);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer updateMouseImgDo(MouseImgDo mouseImgDo) {
        return mouseStyleMapper.updateMouseImgDo(mouseImgDo);
    }

    @Override
    public MouseStyleDo getMouseStyleDoByUid(Integer uid) {
        return mouseStyleMapper.getMouseStyleDoByUid(uid);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer deleteMouseImgDoByMid(Long mid) {
        return mouseStyleMapper.deleteMouseImgDoByMid(mid);
    }

    @Override
    public PageDataGridResult<MouseImgDo> getMouseImgDoByMyUpload(Integer mouseType, Integer uid, Integer page, Integer size) {
        if (page==null)page=1;
        if (size==null)size=18;

        //分页
        PageHelper.startPage(page, size);
        List<MouseImgDo> list = mouseStyleMapper.getMouseImgDoByMyUpload(mouseType, uid);
        PageInfo<MouseImgDo> pageInfo = new PageInfo<MouseImgDo>(list);
        PageDataGridResult<MouseImgDo> dataGridResult = new PageDataGridResult<MouseImgDo>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;

    }

    @Override
    public PageDataGridResult<MouseImgDo> getMouseImgDoByPublic(Integer mouseType, Boolean share, Integer page, Integer size,String field, String order) {
        if (page==null)page=1;
        if (size==null)size=18;
        Map mapParam=new HashMap();
        mapParam.put("mouseType",mouseType);
        mapParam.put("share",share);

        if(field!=null&&field.trim().length()>0&&order!=null&&order.trim().length()>0)
        mapParam.put(field,order);

        //分页
        PageHelper.startPage(page, size);
        List<MouseImgDo> list = mouseStyleMapper.getMouseImgDoByPublic(mapParam);
        PageInfo<MouseImgDo> pageInfo = new PageInfo<MouseImgDo>(list);
        PageDataGridResult<MouseImgDo> dataGridResult = new PageDataGridResult<MouseImgDo>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Override
    public MouseImgDo getMouseImgByMid(Long mid) {
        return mouseStyleMapper.getMouseImgByMid(mid);
    }

    @Override
    public List<MouseStyleDo> getMouseStyleDoByMid(Long mid) {
        return mouseStyleMapper.getMouseStyleDoByMid(mid);
    }


}
