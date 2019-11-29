package com.jdh.config;

import com.jdh.utils.BingBgIMG;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//ServletContext初始化监听器 赋值
@Component
@WebListener
public class SettingDataInitListener implements ServletContextListener {

    @Value("${myparameter.materialVersion}")
    private String materialVersion;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
      //  System.out.println(materialVersion+"sssss");
        servletContext.setAttribute("v", materialVersion);//设置静态资源版本号

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
