package com.music;


import com.ljy.musicapplication.MusicApplication;
import com.ljy.musicapplication.bean.Music;
import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.mapper.MusicMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 针对Music的单元测试
 */
@SpringBootTest(classes = {MusicApplication.class})
@RunWith(SpringRunner.class)
public class MusicTest {
    @Autowired
    private MusicMapper musicMapper;
    /**
     * 测试添加音乐
     */
    @Test
    public void testAddMusic(){
        // 封装测试信息
        MusicType musicType = new MusicType();
        musicType.setMusictypeId(4);
        Music music = new Music();
        music.setMusicName("孤勇者");
        music.setMusicType(musicType);
        music.setMusicPic(null);
        music.setSinger("陈奕迅");
        music.setRecordCompany("陈奕迅音乐工作室");
        music.setDepict("这是英雄联盟主题曲");
        music.setReleaseDate("2020-09-18");
        music.setPrice(20.1);
        // 访问数据库
        if (musicMapper.addMusic(music) > 0){
            System.out.println("音乐添加成功！");
        }else{
            System.out.println("音乐添加失败！");
        }
    }

    /**
     * 测试查询音乐
     */
    @Test
    public void testFindMusicAll(){
        List<Music> list = musicMapper.findMusicAll("");
        System.out.println(list);

    }
    /**
     * 测试修改音乐
     */
    @Test
    public void testUpdateMusic(){
        MusicType musicType = new MusicType();
        musicType.setMusictypeId(4);
        Music music = new Music();
        music.setMusicId(1);
        music.setRecordCompany("音乐工作室");
        music.setPrice(9.9);
        if(musicMapper.updateMusic(music) > 0){
            System.out.println("音乐类别修改成功！");
        }else{
            System.out.println("音乐类别修改失败！");
        }
    }

    /**
     * 测试删除音乐
     * 真删除
     */
    @Test
    public void testDeleteMusic1(){
        if(musicMapper.deleteMusic1(1)>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }

    /**
     * 测试删除音乐
     * 假删除
     */
    @Test
    public void testDeleteMusic2(){
        if(musicMapper.deleteMusic2(2)>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }
}
