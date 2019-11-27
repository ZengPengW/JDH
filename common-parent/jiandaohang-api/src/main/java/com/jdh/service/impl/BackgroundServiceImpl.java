package com.jdh.service.impl;

import com.jdh.mapper.BackgroundMapper;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundMapper backgroundMapper;


    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public void addBackgroundImg(BackgroundImgDo backgroundImgDo) {
        backgroundMapper.addBackgroundImg(backgroundImgDo);
    }
}
