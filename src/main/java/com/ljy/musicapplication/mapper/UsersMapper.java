package com.ljy.musicapplication.mapper;


import com.ljy.musicapplication.bean.Music;
import com.ljy.musicapplication.bean.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

// 对user表的dao操作
@Repository
public interface UsersMapper {

    /**
     * 登录
     *
     * @param users
     * @return
     */
    public Users login(Users users);

    /**
     * 修改个人信息（包括修改密码）
     *
     * @param users
     * @return
     */
    public int updateUsers(Users users);

}
