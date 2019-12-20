package com.jdh.controller;

import com.jdh.feign.SearchBoxService;
import com.jdh.pojo.MyUser;
import com.jdh.pojo.SearchBoxDo;
import com.jdh.utils.JdhResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchBoxController {

    @Autowired
    private SearchBoxService searchBoxService;

    /**
     * 根据用户id 获取用户搜索框样式
     * @param uid
     * @return
     */
//    @GetMapping("/searchbox/uid/{uid}")
//    public SearchBoxDo getSearchBoxByUid(@PathVariable(name = "uid") Integer uid){
//        return searchBoxService.getSearchBoxByUid(uid);
//    }

    /**
     * 保存搜索框样式信息
     * @param sousuoIsChange 搜索框是否变色
     * @param sousuobgcolor 搜索框颜色
     * @param sousuofontcolor 字体颜色
     * @param sousuofontIsChange 字体是否变色
     * @return
     */
    @PostMapping("/searchbox/save")
    public JdhResult saveSearchBox(@RequestParam(name = "sousuoIsChange",required = false) Boolean sousuoIsChange, @RequestParam(name = "sousuobgcolor",required = false) String sousuobgcolor, @RequestParam(name = "sousuofontcolor",required = false)String sousuofontcolor, @RequestParam(name = "sousuofontIsChange",required = false)Boolean sousuofontIsChange){


        try {
            MyUser currUser = UserContext.getCurrUser();
            //获取搜索框样式
            SearchBoxDo searchBoxDo = searchBoxService.getSearchBoxByUid(currUser.getId());
            //如果不存在就保存
            if(searchBoxDo==null){
                searchBoxDo=new SearchBoxDo();
                searchBoxDo.setUid(currUser.getId());
                searchBoxDo.setBorderChangeColor(sousuoIsChange);
                searchBoxDo.setBorderColor(sousuobgcolor);
                searchBoxDo.setTextColor(sousuofontcolor);
                searchBoxDo.setTextChangeColor(sousuofontIsChange);
                searchBoxService.saveSearchBox(searchBoxDo);
                return JdhResult.success("边框样式保存成功！！！");
            }else {
                if(sousuoIsChange!=null){
                    searchBoxDo.setBorderChangeColor(sousuoIsChange);
                }

                if(sousuobgcolor!=null){
                    searchBoxDo.setBorderColor(sousuobgcolor);
                }

                if(sousuofontcolor!=null){
                    searchBoxDo.setTextColor(sousuofontcolor);
                }

                if (sousuofontIsChange!=null){
                    searchBoxDo.setTextChangeColor(sousuofontIsChange);
                }
                searchBoxService.updateSearchBox(searchBoxDo);

                return JdhResult.success("边框样式修改成功！！！");
            }




        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("服务器错误，边框样式修改失败！！！");
        }



    }


}