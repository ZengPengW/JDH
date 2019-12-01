package com.jdh.controller;

import com.jdh.feign.BackgroundService;
import com.jdh.pojo.MyUser;
import com.jdh.utils.PageDataGridResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    @GetMapping("/bgImg/myUpLoad")
    public PageDataGridResult getUserBackgroundImgByUid( @RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false )Integer size){
        MyUser currUser = UserContext.getCurrUser();
        return backgroundService.getUserBackgroundImgByUid(currUser.getId(),page,size);
    }

}
