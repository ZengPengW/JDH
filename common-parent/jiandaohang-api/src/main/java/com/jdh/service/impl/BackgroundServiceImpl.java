package com.jdh.service.impl;

import com.jdh.mapper.BackgroundMapper;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.BackgroundInfo;
import com.jdh.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        backgroundMapper.updateBackground(background);
    }

    @Override
    public BackgroundImgDo getUserBackgroundImgByPid(Long pid) {
        return backgroundMapper.getUserBackgroundImgByPid(pid);
    }


}
