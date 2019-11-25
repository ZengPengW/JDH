package com.jdh.controller;

import com.jdh.pojo.MyUser;
import com.jdh.utils.CheckImgServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageController {

    @Autowired
    private CheckImgServlet checkImgServlet;

    @RequestMapping(value = {"/index", "/"})
    public String openIndex(Model model, HttpServletRequest request, HttpServletResponse response) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            MyUser userDetails = (MyUser) principal;
            String username = userDetails.getUsername();
            model.addAttribute("username", username);
//            System.out.println(username);
//            System.out.println(userDetails.getPassword());
//            System.out.println(userDetails.getEmail());
//            System.out.println(userDetails.getId());
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
