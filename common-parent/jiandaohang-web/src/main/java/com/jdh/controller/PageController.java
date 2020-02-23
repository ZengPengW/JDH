package com.jdh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jdh.feign.*;
import com.jdh.pojo.*;
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
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private CheckImgServlet checkImgServlet;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private BackgroundSpeService backgroundSpeService;

    @Autowired
    private SearchBoxService searchBoxService;

    @Autowired
    private BingBgImgBean bingBgImgBean;

    @Autowired
    private MouseStyleService mouseStyleService;

    @Autowired
    private FloaterService floaterService;


    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = {"/index", "/"})
    public String openIndex(Model model, HttpServletRequest request, HttpServletResponse response) {


        //获取当前用户
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { //存在当前用户
            //获取用户
            MyUser userDetails = (MyUser) principal;
           // String username = userDetails.getUsername();
            model.addAttribute("user", userDetails);

            //获取背景信息
            Background background = backgroundService.getUserBackgroundById(userDetails.getId());

            //****************************背景图片******************************************************
            BackgroundImgDo bgImg = null;
            if (background != null && background.getPid() != null)//背景信息不为空 则获取背景 图片和特效
                bgImg = backgroundService.getUserBackgroundImgByPid(background.getPid());

            //如果用户无背景图 就调用bing背景图
            if (bgImg == null||bgImg.getPic()==null||bgImg.getPic().trim().length()==0) {

                bgImg=new BackgroundImgDo();
                bgImg.setPic(bingBgImgBean.getImgPath());
                model.addAttribute("bgImg", bgImg);

            } else {
                model.addAttribute("bgImg", bgImg);
            }

            //****************************背景图片******************************************************

            //****************************背景特效******************************************************
            BackgroundSpeDo bgSpe=null;
            if (background != null && background.getSid() != null)//背景特效不为空 则获取背景 图片和特效
                bgSpe = backgroundSpeService.getBackgroundSpeBySid(background.getSid());

            //如果有特效 就用 无就不用
            if (bgSpe != null) {
                model.addAttribute("bgSpe", bgSpe);
            }else {
                bgSpe=new BackgroundSpeDo();//空参数的背景特效
                model.addAttribute("bgSpe", bgSpe);
            }
//****************************背景特效******************************************************

//****************************搜索框样式******************************************************
            SearchBoxDo searchBox = searchBoxService.getSearchBoxByUid(userDetails.getId());
            if(searchBox!=null){
                model.addAttribute("searchBox", searchBox);
            }else {
                searchBox=new SearchBoxDo();
                model.addAttribute("searchBox", searchBox);
            }

//****************************搜索框样式******************************************************

//****************************鼠标图标******************************************************
            MouseStyleDo mouseStyleDo = mouseStyleService.getMouseStyleDoByUid(userDetails.getId());
            MouseImgDo  mouseImgP=null;//指针
            MouseImgDo  mouseImgH=null;//图标

            if (mouseStyleDo!=null){
              if(mouseStyleDo.getPmid()!=null)  mouseImgP = mouseStyleService.getMouseImgByMid(mouseStyleDo.getPmid());
              if(mouseStyleDo.getHmid()!=null)   mouseImgH= mouseStyleService.getMouseImgByMid(mouseStyleDo.getHmid());
                if (mouseImgP!=null){
                    model.addAttribute("mouseImgP",mouseImgP);
                }else {
                    model.addAttribute("mouseImgP",new MouseImgDo());
                }

                if (mouseImgH!=null){
                    model.addAttribute("mouseImgH",mouseImgH);
                }else {
                    model.addAttribute("mouseImgH",new MouseImgDo());
                }

            }else {
                model.addAttribute("mouseImgP",new MouseImgDo());
                model.addAttribute("mouseImgH",new MouseImgDo());
            }


//****************************鼠标图标******************************************************
//****************************漂浮物******************************************************
            FloaterStyleDo floaterStyleDo = floaterService.getFloaterStyleDoByUid(userDetails.getId());
            if (floaterStyleDo!=null){
                FloaterImgDo floaterImgDo = floaterService.getFloaterImgDoByFid(floaterStyleDo.getFid());
                model.addAttribute("floaterImg",floaterImgDo);
            }else {
                FloaterImgDo floaterImgDo = new FloaterImgDo();
                model.addAttribute("floaterImg",floaterImgDo);
            }
//****************************漂浮物******************************************************

//****************************播放器样式&歌曲******************************************************
            PlayerStyleDo playerStyle = playerService.getPlayerStyleDoByUid(userDetails.getId());
            if (playerStyle!=null){
                List<PlayerImgDo> playerImgList = playerService.getPlayerImgDoByPid(playerStyle.getPid());
                if (playerImgList!=null&&playerImgList.size()>0){
                    model.addAttribute("playerImg",playerImgList.get(0));
                }else {
                    model.addAttribute("playerImg",new PlayerImgDo());
                }

            }else {
                model.addAttribute("playerImg",new PlayerImgDo());
            }

            List<PlayerMusicDo> playerMusicDoByUid = playerService.getPlayerMusicDoByUid(userDetails.getId());
            if (playerMusicDoByUid!=null&&playerMusicDoByUid.size()>0){
                PlayerMusicDo playerMusicDo = playerMusicDoByUid.get(0);
                model.addAttribute("playerMusic",playerMusicDo);
            }else {
                model.addAttribute("playerMusic",new PlayerMusicDo());

            }
//****************************漂播放器样式&歌曲******************************************************


        } else { //不存在用户
            // 用bing图片
            MyUser userDetails=new MyUser();
            BackgroundSpeDo   bgSpe=new BackgroundSpeDo();//空参数的背景特效
            model.addAttribute("bgSpe", bgSpe);
            //用户
            model.addAttribute("user", userDetails);
            //背景图
            BackgroundImgDo bgImg=new BackgroundImgDo();
             bgImg.setPic(bingBgImgBean.getImgPath());
            model.addAttribute("bgImg", bgImg);

            //搜索框
            SearchBoxDo searchBox=new SearchBoxDo();
            model.addAttribute("searchBox", searchBox);

            //鼠标
            model.addAttribute("mouseImgP",new MouseImgDo());
            model.addAttribute("mouseImgH",new MouseImgDo());

            //漂浮物
            FloaterImgDo floaterImgDo = new FloaterImgDo();
            model.addAttribute("floaterImg",floaterImgDo);

            //播放器图标
            model.addAttribute("playerImg",new PlayerImgDo());
            //背景音乐
            model.addAttribute("playerMusic",new PlayerMusicDo());

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

    @RequestMapping("/getmusicstudy")
    public String getmusicstudy(){
        return "page/getmusicstudy";
    }



}
