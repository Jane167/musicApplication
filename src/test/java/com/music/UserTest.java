package com.music;

import com.ljy.musicapplication.MusicApplication;
import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.bean.Users;
import com.ljy.musicapplication.mapper.UsersMapper;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {MusicApplication.class})
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 测试用户登录
     */
    @Test
    public void login() {
        Users u = new Users();
        u.setPhone("17612937572");
        u.setPassword("123456");
        Users users = usersMapper.login(u);
        if (users != null) {
            System.out.println("管理员登录成功");
            System.out.println(users);
        } else {
            System.out.println("管理员登录失败");
        }
    }

    /**
     * 测试修改个人信息
     */
    @Test
    public void testUpdateUsers(){
        Users users = new Users();
        users.setUserId(1);
        users.setAddress("陕西省渭南市临渭区");
        users.setPhone("17612937572");
        users.setSex("女");
        users.setNickname("卑微女大学生！");
        if(usersMapper.updateUsers(users) > 0){
            System.out.println("个人信息修改成功！");
        }else{
            System.out.println("个人信息修改失败！");
        }
    }
}
