package com.jdh.config;

import com.jdh.utils.BingBgImgBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * springboot 定时器 定时执行任务
 */
@Component
public class MyTimer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BingBgImgBean bingBgImgBean;

   // @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次
    @Scheduled(cron = "0 0 1 * * ?")
    public void statusCheck()  {
        logger.info("每天一点执行bing背景图片获取");

        try {
            bingBgImgBean.updateImgPath();
        } catch (Exception e) {
           // e.printStackTrace();
            logger.info("bing背景图片获取。失败。");
        }
        logger.info("背景图片获取。结束。");
    }

//    @Scheduled(fixedRate=20000)
//    public void testTasks() {
//        logger.info("每20秒执行一次。开始……");
//        //statusTask.healthCheck();
//        logger.info("每20秒执行一次。结束。");
//    }

}
