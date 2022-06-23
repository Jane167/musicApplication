package com.ljy.musicapplication.mapper;


import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.bean.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

// 对musictype表的dao操作：增删改查
@Repository
public interface MusicTypeMapper {
    /**
     * 添加音乐类别
     * @param musicType
     * @return
     */
    public int addMusicType(MusicType musicType);

    /**
     * 查询音乐类别 （支持关键字 / 不支持关键字）
     * @param keywords
     * @return
     */
    public List<MusicType> findMusicTypeAll(String keywords);

    /**
     * 修改音乐类别
     * @param musicType
     * @return
     */
    public int updateMusicType(MusicType musicType);

    /**
     * 删除音乐类别 （真删除）
     * @param musictypeId
     * @return
     */
    public int deleteMusicType1(Integer musictypeId);

    /**
     * 删除音乐类别 （假删除）
     * @param musictypeId
     * @return
     */
    public int deleteMusicType2(Integer musictypeId);
}
