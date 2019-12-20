package com.jdh.service.impl;

import com.jdh.mapper.SearchBoxMapper;
import com.jdh.pojo.SearchBoxDo;
import com.jdh.service.SearchBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchBoxServiceImpl implements SearchBoxService {

    @Autowired
    private SearchBoxMapper searchBoxMapper;

    @Override
    public SearchBoxDo getSearchBoxByUid(Integer uid) {
        return searchBoxMapper.getSearchBoxByUid(uid);
    }


    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer saveSearchBox(SearchBoxDo searchBoxDo) {
        return searchBoxMapper.saveSearchBox(searchBoxDo);
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Integer updateSearchBox(SearchBoxDo searchBoxDo) {
        return searchBoxMapper.updateSearchBox(searchBoxDo);
    }
}
