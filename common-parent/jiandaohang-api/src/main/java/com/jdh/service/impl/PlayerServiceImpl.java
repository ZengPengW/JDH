package com.jdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdh.mapper.PlayerMapper;
import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.PlayerImgDo;
import com.jdh.pojo.PlayerMusicDo;
import com.jdh.pojo.PlayerStyleDo;
import com.jdh.service.PlayerService;
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
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<PlayerImgDo> getPlayerImgDoByMd5(String md5) {
        return playerMapper.getPlayerImgDoByMd5(md5);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer addPlayerImgDo(PlayerImgDo playerImgDo) {
        return playerMapper.addPlayerImgDo(playerImgDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer changePlayerStyle(PlayerStyleDo playerStyleDo) {
        return playerMapper.changePlayerStyle(playerStyleDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer addPlayerStyle(PlayerStyleDo playerStyleDo) {
        return playerMapper.addPlayerStyle(playerStyleDo);
    }

    @Override
    public PlayerStyleDo getPlayerStyleDoByUid(Integer uid) {
        return playerMapper.getPlayerStyleDoByUid(uid);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer updatePlayerImgDo(PlayerImgDo playerImgDo) {
        return playerMapper.updatePlayerImgDo(playerImgDo);
    }

    @Override
    public PageDataGridResult<PlayerImgDo>  getPlayerImgDoByUid(Integer uid,Integer page, Integer size) {
        if (page==null)page=1;
        if (size==null)size=18;

        //分页
        PageHelper.startPage(page, size);
        List<PlayerImgDo> list = playerMapper.getPlayerImgDoByUid(uid);
        PageInfo<PlayerImgDo> pageInfo = new PageInfo<PlayerImgDo>(list);
        PageDataGridResult<PlayerImgDo> dataGridResult = new PageDataGridResult<PlayerImgDo>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer deletePlayerImgDoByMd5(String md5) {
        return playerMapper.deletePlayerImgDoByMd5(md5);
    }

    @Override
    public List<PlayerStyleDo> getPlayerStyleDoByPid(Long pid) {
        //分页
        PageHelper.startPage(1, 1);
        List<PlayerStyleDo> list = playerMapper.getPlayerStyleDoByPid(pid);

        return list;
    }

    @Override
    public PageDataGridResult<PlayerImgDo> getPlayerImgDoAll(Integer page, Integer size, String field, String order, Boolean ispublic) {
        if (page==null)page=1;
        if (size==null)size=18;
        Map mapParam=new HashMap();
        mapParam.put("share",ispublic);

        if(field!=null&&field.trim().length()>0&&order!=null&&order.trim().length()>0)
        mapParam.put(field,order);

        //分页
        PageHelper.startPage(page, size);
        List<PlayerImgDo> list = playerMapper.getPlayerImgDoAll(mapParam);
        PageInfo<PlayerImgDo> pageInfo = new PageInfo<PlayerImgDo>(list);
        PageDataGridResult<PlayerImgDo> dataGridResult = new PageDataGridResult<PlayerImgDo>();
        dataGridResult.setRows(list);
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setPage(Long.valueOf(page));
        dataGridResult.setHasNextPage(pageInfo.isHasNextPage());
        dataGridResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return dataGridResult;
    }

    @Override
    public List<PlayerImgDo> getPlayerImgDoByPid(Long pid) {
        return playerMapper.getPlayerImgDoByPid(pid);
    }



    @Override
    public List<PlayerMusicDo> getPlayerMusicDoByUid(Integer uid) {
        return playerMapper.getPlayerMusicDoByUid(uid);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer addPlayerMusicDo(PlayerMusicDo playerMusicDo) {
        return playerMapper.addPlayerMusicDo(playerMusicDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer changePlayerMusicDo(PlayerMusicDo playerMusicDo) {
        return playerMapper.changePlayerMusicDo(playerMusicDo);
    }

    /**
     * 删除用户的背景音乐
     * @param uid
     * @return
     */
    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer deletePlayerMusicDoByUid(Integer uid){
        return playerMapper.deletePlayerMusicDoByUid(uid);
    }
}
