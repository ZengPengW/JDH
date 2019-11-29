package com.jdh.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BingBgIMG {

    public static String getBingImg() throws Exception {
        //  {"images":[{"startdate":"20191127","fullstartdate":"201911271600","enddate":"20191128","url":"/th?id=OHR.LasCatedralesBeach_ZH-CN5680206879_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","urlbase":"/th?id=OHR.LasCatedralesBeach_ZH-CN5680206879","copyright":"Las Catedrales海滩，西班牙加利西亚 (© Davide Seddio/Getty Images)","copyrightlink":"https://www.bing.com/search?q=Las+Catedrales%E6%B5%B7%E6%BB%A9&form=hpcapt&mkt=zh-cn","title":"","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20191127_LasCatedralesBeach%22&FORM=HPQUIZ","wp":true,"hsh":"5b99194834581de9765129ea59bcecc3","drk":1,"top":1,"bot":1,"hs":[]}],"tooltips":{"loading":"正在加载...","previous":"上一个图像","next":"下一个图像","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}}
        HttpResult httpResult = APIService.doGet("http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
        String body = httpResult.getBody();
        JSONObject jsonObject=JSONObject.parseObject(body);
        JSONArray jsonArray=jsonObject.getJSONArray("images");
        jsonObject=jsonArray.getJSONObject(0);
        String url = "https://cn.bing.com"+(jsonObject.get("url").toString());
        return url;
    }
}
