package com.ljy.musicapplication.controller;


import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.bean.RtnInfo;
import com.ljy.musicapplication.bean.Users;
import com.ljy.musicapplication.bean.UsersVo;
import com.ljy.musicapplication.mapper.UsersMapper;
import com.ljy.musicapplication.util.CpachaUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * 生成验证码图片
     * 请求方式:GET
     * 请求url: /musicapp/users/verifyCode
     * 返回结果: JSON字符串
     * @param tokenStr
     * @param request
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value="verifyCode" ,method = RequestMethod.GET)
    public void verifyCode(String tokenStr, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        CpachaUtil cpachaUtil = new CpachaUtil(4,100,30);//指定验证码长度、图片宽度、高度
        //生成随机的验证码(四个字符的字符串)
        String generatorVCode = cpachaUtil.generatorVCode();
        //将验证码保存到session
        HttpSession session = request.getSession();
        //设置超时
        //session.setMaxInactiveInterval(60);//以秒为单位
        //将验证码存放到session，主要是在登录的servlet中实现跟前台的验证码进行匹配
        session.setAttribute("vCode",generatorVCode);
        System.out.println("-----------------保存session:"+session.getId()+" "+generatorVCode);
        //生成一个验证码图片，响应到前台
        BufferedImage g = cpachaUtil.generatorRotateVCodeImage(generatorVCode,true);
        //回写
        ImageIO.write(g,"gif",resp.getOutputStream());
    }
    /*
    * 登录
    * 请求方式：POST
    * 请求url：/musicapp/user/login
    * 返回结果：JSON字符串
    *
    * */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public RtnInfo login(HttpServletRequest request, Users users) throws Exception{
        String vcode = request.getParameter("vcode");
        System.out.println(vcode);
        System.out.println(users);
        RtnInfo rtnInfo = new RtnInfo();
        // 对传过来的数据进行非空校验
        if(StringUtils.isNullOrEmpty(users.getPhone())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("手机号不能为空");
        }else if(StringUtils.isNullOrEmpty(users.getPassword())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("密码不能为空");
        }else if(StringUtils.isNullOrEmpty(vcode)){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("验证码不能为空");
        }else{
            // 获取session中的存储的验证码，校验验证码事都正确
            HttpSession session = request.getSession();
            String vcodetrue = (String) session.getAttribute("vCode");
            if(vcodetrue == null || !vcodetrue.equalsIgnoreCase(vcode)){
                rtnInfo.setCode(-1);
                rtnInfo.setMsg("验证码错误!");
            } else{
                // 验证码正确，通过数据库校验phone和password
                Users query = usersMapper.login(users);
                if(query != null){
                    rtnInfo.setCode(1);
                    rtnInfo.setMsg("用户登录成功！");
                    rtnInfo.setResult(query);
                }else{
                    rtnInfo.setCode(0);
                    rtnInfo.setMsg("用户登录失败！");
                }
            }
        }


        return rtnInfo;  // springboot会自动转化为json字符串响应到前端
    }


    /**
     * 根据id修改音乐类别
     * 请求方式：POST
     * 请求URL：/musicApp/user/updateUsers
     * 请求参数：表单
     * 响应结果：JSON字符串
     * @param users
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateUsers", method = RequestMethod.POST)
    public RtnInfo updateUsers(Users users) throws Exception{
        System.out.println("======进入到修改个人信息的方法：=====" +users);
        // 创建一个rtnInfo,封装返回给前端的信息
        RtnInfo rtnInfo = new RtnInfo();

        // 非空校验
        if(StringUtils.isNullOrEmpty(users.getUserId() + "")){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("用户编号不能为空！");
        }else if(StringUtils.isNullOrEmpty(users.getPhone())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("手机号码不能为空！");
        }else if(StringUtils.isNullOrEmpty(users.getNickname())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("用户昵称不能为空！");
        }else if(StringUtils.isNullOrEmpty(users.getSex())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("性别不能为空！");
        }else if(StringUtils.isNullOrEmpty(users.getAddress())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("地址不能为空！");
        }else{
            // 访问数据库
            if(usersMapper.updateUsers(users) > 0){
                rtnInfo.setCode(1);
                rtnInfo.setMsg("个人信息修改成功！");
            }else {
                rtnInfo.setCode(0);
                rtnInfo.setMsg("个人信息修改失败！");
            }
        }
        return rtnInfo;
    }

}
