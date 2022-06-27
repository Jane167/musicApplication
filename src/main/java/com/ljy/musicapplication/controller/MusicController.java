package com.ljy.musicapplication.controller;


import com.ljy.musicapplication.bean.Music;
import com.ljy.musicapplication.bean.RtnInfo;
import com.ljy.musicapplication.mapper.MusicMapper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicMapper musicMapper;


    /**
     * 添加音乐的方法
     * 请求方法：POST
     * 请求路径：musicApp/music/add/
     * 请求参数：表单
     * 响应结果：JSON字符串
     * @param music
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public RtnInfo add(Music music) throws Exception{
        System.out.println("======================进入添加音乐页面====================");
        // 创建一个rtn对象用来封装响应到前端的信息
        RtnInfo rtnInfo = new RtnInfo();
        // 非空校验
        if(StringUtils.isNullOrEmpty(music.getMusicName())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐名称不能为空！");
        }else if(StringUtils.isNullOrEmpty(music.getMusicPic())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("封面图片不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getSinger())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("歌手不能为空！");
        }else if(StringUtils.isNullOrEmpty(music.getRecordCompany())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("唱片公司不能为空！");
        }else if(StringUtils.isNullOrEmpty(music.getReleaseDate())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("发行时间不能为空！");
        } else if(StringUtils.isNullOrEmpty(music.getDepict())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐描述不能为空！");
        } else if(music.getPrice() <= 0){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("单价不能为空！");
        }else{
            // 访问数据库
           if(musicMapper.addMusic(music) > 1){
               rtnInfo.setCode(1);
               rtnInfo.setMsg("音乐添加成功！");
           }else{
               rtnInfo.setCode(0);
               rtnInfo.setMsg("音乐添加失败！");
           }
        }
        return rtnInfo;
    }
    // 查询音乐的方法

    // 编辑音乐的方法

    // 删除音乐的方法
}
