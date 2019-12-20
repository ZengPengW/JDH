package com.jdh.controller;

import com.jdh.pojo.SearchBoxDo;
import com.jdh.service.SearchBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索框
 */
@RestController
public class SearchBoxController {

    @Autowired
    private SearchBoxService searchBoxService;

    /**
     * 根据用户id 获取用户搜索框样式
     * @param uid
     * @return
     */
    @GetMapping("/searchbox/uid/{uid}")
    public SearchBoxDo getSearchBoxByUid(@PathVariable(name = "uid") Integer uid){
        return searchBoxService.getSearchBoxByUid(uid);
    }

    /**
     * 保存搜索框样式信息
     * @param searchBoxDo
     * @return
     */
    @PostMapping("/searchbox/add")
    public Integer saveSearchBox(@RequestBody SearchBoxDo searchBoxDo){
        return searchBoxService.saveSearchBox(searchBoxDo);
    }

    /**
     * 更新搜索框样式信息
     * @param searchBoxDo
     * @return
     */
    @PostMapping("/searchbox/update")
    public Integer updateSearchBox(@RequestBody SearchBoxDo searchBoxDo){
        return searchBoxService.updateSearchBox(searchBoxDo);
    }
}
