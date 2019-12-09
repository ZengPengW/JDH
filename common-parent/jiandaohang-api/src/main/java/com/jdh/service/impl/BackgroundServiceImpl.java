package com.jdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdh.mapper.BackgroundMapper;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.BackgroundInfo;
import com.jdh.service.BackgroundService;
import com.jdh.utils.PageDataGridResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundMapper backgroundMapper;


    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public Long addBackgroundImg(BackgroundImgDo backgroundImgDo) {
       return backgroundMapper.addBackgroundImg(backgroundImgDo);
    }

    @Override
    public List<BackgroundImgDo> getBackgroundImgByMd5(String md5) {
        return backgroundMapper.getBackgroundImgByMd5(md5);
    }

    @Override
    public Background getUserBackgroundById(Integer uid) {
        return backgroundMapper.getUserBackgroundById(uid);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public void addBackground(Background background) {
        backgroundMapper.addBackground(background);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public void updateBackground(Background background) {
        //pid不为空则背景图片个数加一
        if(background.getPid()!=null)backgroundMapper.addBackgroundImgUseCountByPid(background.getPid());
        backgroundMapper.updateBackground(background);
    }

    @Override
    public BackgroundImgDo getUserBackgroundImgByPid(Long pid) {
        return backgroundMapper.getUserBackgroundImgByPid(pid);
    }

    @Override
    public PageDataGridResult getUserBackgroundImgByUid(Integer author_id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<BackgroundImgDo> list = backgroundMapper.getUserBackgroundImgByUid(author_id);
        PageInfo<BackgroundImgDo> pageInfo = new PageInfo<BackgroundImgDo>(list);
        PageDataGridResult<BackgroundImgDo> dataGridResult = new PageDataGridResult<>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public Integer deleteBackgroundImgByPid(Long pid) {
        return backgroundMapper.deleteBackgroundImgByPid(pid);
    }

    @Override
    public List<Background> getUserBackgroundByPid(Long pid) {
        return backgroundMapper.getUserBackgroundByPid(pid);
    }

    @Override
    public PageDataGridResult getBackgroundImgByIsPublic(boolean ispublic,Integer page,Integer size,String field,String order) {
        //分页
        PageHelper.startPage(page, size);

        //条件
        Map map=new HashMap();
        map.put("ispublic",ispublic);
        if(field!=null&&field.trim().length()>0&&order!=null&&order.trim().length()>0)
            map.put(field,order);



        List<BackgroundImgDo> imgByIsPublic = backgroundMapper.getBackgroundImgByIsPublic(map);
        PageInfo<BackgroundImgDo> pageInfo = new PageInfo<BackgroundImgDo>(imgByIsPublic);
        PageDataGridResult<BackgroundImgDo> dataGridResult = new PageDataGridResult<>();
        dataGridResult.setRows(imgByIsPublic);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public Integer addBackgroundImgUseCountByPid(Long pid) {
        return backgroundMapper.addBackgroundImgUseCountByPid(pid);
    }


}
