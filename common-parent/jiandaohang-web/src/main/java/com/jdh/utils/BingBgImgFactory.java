package com.jdh.utils;

public class BingBgImgFactory{
    private static final BingBgImgBean bingBgImgBean=new BingBgImgBean();

    static {
        String bingImg = null;
        try {
            bingImg = BingBgIMG.getBingImg();
            bingBgImgBean.setImgPath(bingImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BingBgImgBean getBingBgImgBean(){
        return bingBgImgBean;
    }
}