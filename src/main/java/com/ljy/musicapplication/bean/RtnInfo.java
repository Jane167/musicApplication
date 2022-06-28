package com.ljy.musicapplication.bean;


/*
 * 响应结果类
 * controller 统一返回类型
 *
 * */


public class RtnInfo {
    private Integer code;       // 响应码，-1非空校验，0失败状态码，1成功状态码
    private String msg;     // 响应提示消息，只要是给前端弹窗显示
    private Object result;      // 响应到前端的数据，（只有查询操作有 ）

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RtnInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

}
