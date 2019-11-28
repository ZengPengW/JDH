package com.jdh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdh.feign.BackgroundService;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.APIService;
import com.jdh.utils.CheckImgServlet;
import com.jdh.utils.HttpResult;
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

    @RequestMapping(value = {"/index", "/"})
    public String openIndex(Model model, HttpServletRequest request, HttpServletResponse response)  {


        //获取当前用户
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { //存在当前用户
            MyUser userDetails = (MyUser) principal;
            String username = userDetails.getUsername();
            model.addAttribute("username", username);
//            System.out.println(username);
//            System.out.println(userDetails.getPassword());
//            System.out.println(userDetails.getEmail());
//            System.out.println(userDetails.getId());

            Background background = backgroundService.getUserBackgroundById(userDetails.getId());
            BackgroundImgDo bgImg = null;
            if(background!=null&&background.getPid()!=null)bgImg=backgroundService.getUserBackgroundImgByPid(background.getPid());

            //如果用户无背景图 就调用bing背景图
            if (bgImg==null){
            try {
 //  {"images":[{"startdate":"20191127","fullstartdate":"201911271600","enddate":"20191128","url":"/th?id=OHR.LasCatedralesBeach_ZH-CN5680206879_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","urlbase":"/th?id=OHR.LasCatedralesBeach_ZH-CN5680206879","copyright":"Las Catedrales海滩，西班牙加利西亚 (© Davide Seddio/Getty Images)","copyrightlink":"https://www.bing.com/search?q=Las+Catedrales%E6%B5%B7%E6%BB%A9&form=hpcapt&mkt=zh-cn","title":"","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20191127_LasCatedralesBeach%22&FORM=HPQUIZ","wp":true,"hsh":"5b99194834581de9765129ea59bcecc3","drk":1,"top":1,"bot":1,"hs":[]}],"tooltips":{"loading":"正在加载...","previous":"上一个图像","next":"下一个图像","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}}
                    HttpResult httpResult = APIService.doGet("http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
                    String body = httpResult.getBody();
                    JSONObject jsonObject=JSONObject.parseObject(body);
                    JSONArray jsonArray=jsonObject.getJSONArray("images");
                    jsonObject=jsonArray.getJSONObject(0);
                    String url = "https://cn.bing.com"+(jsonObject.get("url").toString());
                    model.addAttribute("bgImg",url);

            }catch (Exception e){
                e.printStackTrace();
            }
          }else {
                model.addAttribute("bgImg",bgImg.getPic());
          }


        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println("登录了");
        }else {
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
