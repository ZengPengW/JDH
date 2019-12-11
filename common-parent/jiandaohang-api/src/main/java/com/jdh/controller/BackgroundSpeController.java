package com.jdh.controller;

import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.service.BackgroundSpeService;
import com.jdh.utils.PageDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackgroundSpeController {

    @Autowired
    private BackgroundSpeService backgroundSpeService;

    /**
     * 获取所有特效
     * @param transparent 是否透明
     * @param page 第几页
     * @param size 每页几条
     * @param field 排序字段名
     * @param order asc/desc 排序规则
     * @return
     */
    @GetMapping("/bgSpe/transparent/{transparent}")
    public PageDataGridResult<BackgroundSpeDo> getBackgroundSpeByTransparent(@PathVariable Boolean transparent, @RequestParam(required = false)Integer page, @RequestParam(required = false)Integer size, @RequestParam(required = false)String field, @RequestParam(required = false)String order){
        if (size == null) size = 8;
        if (page == null || page == 0) page = 1;
        if (transparent == null) transparent = true;
        return backgroundSpeService.getBackgroundSpeByTransparent(transparent, page, size, field, order);
    }

}
