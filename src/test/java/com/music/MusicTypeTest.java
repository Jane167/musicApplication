package com.music;

import com.ljy.musicapplication.MusicApplication;
import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.bean.Users;
import com.ljy.musicapplication.mapper.MusicTypeMapper;
import com.ljy.musicapplication.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {MusicApplication.class})
@RunWith(SpringRunner.class)
public class MusicTypeTest {
    @Autowired
    private MusicTypeMapper musicTypeMapper;

    /**
     * 用于新增类别
     * 单元测试
     */
    @Test
    public void testAddMusicType() {
        MusicType m = new MusicType();
        m.setMusictypeName("第一个类别");
        m.setMusictypeDepict("这个类别用来单元测试");
        if(musicTypeMapper.addMusicType(m) > 0){
            System.out.println("音乐类别添加成功");
        }else{
            System.out.println("音乐类别添加失败");
        }
    }

    /**
     * 测试查询所有图书类别
     */
    @Test
    public void testFindMusicTypeAll() {
        List<MusicType> list = musicTypeMapper.findMusicTypeAll("");
        System.out.println(list);
    }
    /**
     * 测试修改类别
     */
    @Test
    public void testUpdateMusicType(){
        MusicType musicType = new MusicType();
        musicType.setMusictypeId(2);
        musicType.setMusictypeName("欧美");
//        musicType.setMusictypeDepict("民谣歌曲");
        if(musicTypeMapper.updateMusicType(musicType) > 0){
            System.out.println("音乐类别修改成功！");
        }else{
            System.out.println("音乐类别修改失败！");
        }
    }

    /**
     * 测试删除音乐类别
     * 真删除
     */
    @Test
    public void testDeleteMusicType1(){
        if(musicTypeMapper.deleteMusicType1(1)>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }

    /**
     * 测试删除音乐类别
     * 假删除
     */
    @Test
    public void testDeleteMusicType2(){
        if(musicTypeMapper.deleteMusicType2(1)>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }
}
