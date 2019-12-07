package com.jdh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdh.feign.BackgroundService;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private CheckImgServlet checkImgServlet;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private BingBgImgBean bingBgImgBean;

    @RequestMapping(value = {"/index", "/"})
    public String openIndex(Model model, HttpServletRequest request, HttpServletResponse response) {


        //获取当前用户
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { //存在当前用户
            //获取用户
            MyUser userDetails = (MyUser) principal;
            String username = userDetails.getUsername();
            model.addAttribute("user", userDetails);

            //获取背景信息
            Background background = backgroundService.getUserBackgroundById(userDetails.getId());
            BackgroundImgDo bgImg = null;
            if (background != null && background.getPid() != null)//背景信息不为空 则获取背景 图片和特效
                bgImg = backgroundService.getUserBackgroundImgByPid(background.getPid());

            //如果用户无背景图 就调用bing背景图
            if (bgImg == null) {

                model.addAttribute("bgImg", bingBgImgBean.getImgPath());

            } else {
                model.addAttribute("bgImg", bgImg.getPic());
            }


        } else { //不存在用户 用bing图片
            MyUser userDetails=new MyUser();
            model.addAttribute("user", userDetails);
             model.addAttribute("bgImg", bingBgImgBean.getImgPath());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println("登录了");
        } else {
            System.out.println("未登录");
        }
        return "page/index";
    }


    @RequestMapping("/login")
    public String loginAjax() {
        return "page/loginAjax";
    }

    @RequestMapping("/imgCode")
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkImgServlet.getImg(request, response);
    }


}
