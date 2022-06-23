package com.ljy.musicapplication.mapper;


import com.ljy.musicapplication.bean.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

// 对user表的dao操作
@Repository
public interface UsersMapper {


    public Users login(Users users);
}
