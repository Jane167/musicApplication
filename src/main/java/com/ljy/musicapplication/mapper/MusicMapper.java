package com.ljy.musicapplication.mapper;


import com.ljy.musicapplication.bean.Music;
import com.ljy.musicapplication.bean.MusicType;
import org.springframework.stereotype.Repository;

import java.util.List;

// 对music表的dao操作：增删改查
@Repository
public interface MusicMapper {
    /**
     * 添加音乐
     * @param music
     * @return
     */
    public int addMusic(Music music);
    /**
     * 查询音乐 （支持关键字 / 不支持关键字）
     * @param keywords
     * @return
     */
    public List<Music> findMusicAll(String keywords);

    /**
     * 修改音乐
     * @param music
     * @return
     */
    public int updateMusic(Music music);

    /**
     * 删除音乐 （真删除）
     * @param musicId
     * @return
     */
    public int deleteMusic1(Integer musicId);

    /**
     * 删除音乐 （假删除）
     * @param musicId
     * @return
     */
    public int deleteMusic2(Integer musicId);
}
