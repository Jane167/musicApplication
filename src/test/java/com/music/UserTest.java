package com.music;

import com.ljy.musicapplication.MusicApplication;
import com.ljy.musicapplication.bean.Users;
import com.ljy.musicapplication.mapper.UsersMapper;
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
}
