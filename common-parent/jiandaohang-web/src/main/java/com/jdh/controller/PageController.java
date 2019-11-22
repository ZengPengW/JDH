package com.jdh.controller;

import com.jdh.utils.CheckImgServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageController {

    @Autowired
    private CheckImgServlet checkImgServlet;

    @RequestMapping(value = {"/index","/"})
    public String openIndex(){

        return "page/index";
    }


    @RequestMapping("/loginAjax")
    public String loginAjax() {
        return "page/loginAjax";
    }

    @RequestMapping("/imgCode")
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkImgServlet.getImg(request, response);
    }
}
