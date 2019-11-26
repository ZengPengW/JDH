package com.jdh.utils;

public class JdhResult {

    // 响应业务状态
    private Integer status; //1 成功 2错误失败

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public JdhResult(){}
    public  JdhResult(Integer status,String msg,Object data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    //成功success
    public static JdhResult success(String msg,Object data){
        return  new JdhResult(1,msg,data);
    }
    public static JdhResult success(String msg){
        return  new JdhResult(1,msg,null);
    }
    public static JdhResult success(Object data){
        return  new JdhResult(1,null,data);
    }
    //失败fail
    public static JdhResult fail(String msg,Object data){
        return  new JdhResult(2,msg,data);
    }
    public static JdhResult fail(String msg){
        return  new JdhResult(2,msg,null);
    }
    public static JdhResult fail(Object data){
        return  new JdhResult(2,null,data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
