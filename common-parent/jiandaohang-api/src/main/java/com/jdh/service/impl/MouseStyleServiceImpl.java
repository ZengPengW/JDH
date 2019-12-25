package com.jdh.service.impl;

import com.jdh.mapper.MouseStyleMapper;
import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.service.MouseStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public MouseImgDo getMouseImgDoByMd5(String md5) {
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
    public Integer deleteMouseImgDoByMid(Long mid) {
        return mouseStyleMapper.deleteMouseImgDoByMid(mid);
    }
}
