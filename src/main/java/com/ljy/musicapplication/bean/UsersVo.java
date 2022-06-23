package com.ljy.musicapplication.bean;

/*
* 针对Users的补丁类
* 继承Users
* */
public class UsersVo extends Users{
    private String vcode;  // 验证码，数据库不需要，控制器需要，所以可以将数据库不需要，控制器需要的属性放在vo类中
    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @Override
    public String toString() {
        return "UsersVo{" +
                "vcode='" + vcode + '\'' +
                '}';
    }
}
