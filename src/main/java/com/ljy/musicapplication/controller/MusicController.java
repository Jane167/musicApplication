package com.ljy.musicapplication.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.musicapplication.bean.Music;
import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.bean.RtnInfo;
import com.ljy.musicapplication.mapper.MusicMapper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.multi.MultiDesktopIconUI;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicMapper musicMapper;


    /**
     * 添加音乐信息
     * 请求方法：POST
     * 请求URL：/musicApp/music/add/
     * 请求参数：表单
     * 响应结果：JSON字符串
     *
     * @param music
     * @return
     * @throws Exception
     */

    @PostMapping("add")
    public RtnInfo add(Music music) throws Exception {
        System.out.println("======================进入添加音乐页面====================" + music);
        // 创建一个rtn对象用来封装响应到前端的信息
        RtnInfo rtnInfo = new RtnInfo();
        // 非空校验
        if (StringUtils.isNullOrEmpty(music.getMusicName())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐名称不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getMusicPic())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("封面图片不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getSinger())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("歌手不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getMusicType().getMusictypeId() + "")) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐类别不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getRecordCompany())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("唱片公司不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getReleaseDate())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("发行时间不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getDepict())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐描述不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getPrice() + "")) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("单价不能为空！");
        } else {
            // 访问数据库
            if (musicMapper.addMusic(music) > 0) {
                rtnInfo.setCode(1);
                rtnInfo.setMsg("音乐添加成功！");
            } else {
                rtnInfo.setCode(0);
                rtnInfo.setMsg("音乐添加失败！");
            }
        }
        return rtnInfo;
    }

    /**
     * 获取音乐列表信息，支持关键字检索，直至分页
     * 请求方法：GET
     * 请求URL：findAll/{keywords/{currentPage}
     *
     * @return
     * @throws Exception
     */
    @GetMapping("findAll/{keywords}/{currentPage}")
    public RtnInfo finAll(@PathVariable(value = "keywords", required = true) String keywords,
                          @PathVariable(value = "currentPage", required = false) Integer currentPage) throws Exception {
        RtnInfo rtnInfo = new RtnInfo();
        System.out.println("===============进入音乐查询页面=========" + keywords + currentPage);
        //非空校验
        if (StringUtils.isNullOrEmpty(keywords) || keywords.equals("''")) {
            keywords = "";
        }
        if (StringUtils.isNullOrEmpty(currentPage + "")) {
            currentPage = 1;
        }
        // 设置分页
        PageHelper.startPage(currentPage, 5);   // 每页显示5条
        // 访问数据库
        List<Music> musics = musicMapper.findMusicAll(keywords);
        // 封装分页信息
        PageInfo<Music> lists = new PageInfo<>(musics, 5);  // 分页导航条页码个数
        // 封装信息到前端
        if (musics != null) {
            rtnInfo.setCode(1);
            rtnInfo.setMsg("音乐信息获取成功");
            rtnInfo.setResult(lists);
        }
        return rtnInfo;
    }
    // 编辑音乐的方法

    /**
     * 编辑音乐的方法
     * 请求方法：POST
     * 请求URL：musicApp/music/update
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public RtnInfo update(Music music) throws Exception {
        RtnInfo rtnInfo = new RtnInfo();
        // 非空校验
        if (StringUtils.isNullOrEmpty(music.getMusicName())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐名称不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getMusicPic())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("封面图片不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getSinger())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("歌手不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getMusicType().getMusictypeId() + "")) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐类别不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getRecordCompany())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("唱片公司不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getReleaseDate())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("发行时间不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getDepict())) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐描述不能为空！");
        } else if (StringUtils.isNullOrEmpty(music.getPrice() + "")) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("单价不能为空！");
        } else {
            // 访问数据库
            if (musicMapper.updateMusic(music) > 0) {
                rtnInfo.setCode(1);
                rtnInfo.setMsg("音乐修改成功！");
            } else {
                rtnInfo.setCode(0);
                rtnInfo.setMsg("音乐修改失败！");
            }
        }
        return rtnInfo;

    }

    /**
     * 修改状态：上架/下架
     * 请求URL：musicApp/music/changeStatus/{musicId}
     * 请求方法：GET
     * 响应结果：JSON字符串
     *
     * @param musicId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "changeStatus/{musicId}", method = RequestMethod.GET)
    public RtnInfo changeStatus(@PathVariable(value = "musicId", required = true) Integer musicId) throws Exception {
        System.out.println("======进入到修改音乐状态的方法：=====" + musicId);
        // 创建一个rtnInfo,封装返回给前端的信息
        RtnInfo rtnInfo = new RtnInfo();

        // 非空校验
        if (StringUtils.isNullOrEmpty(musicId + "")) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐编号不能为空！");
        } else {
            // 访问数据库
            if (musicMapper.changeStatus(musicId) > 0) {
                rtnInfo.setCode(1);
                rtnInfo.setMsg("状态修改成功！");
            } else {
                rtnInfo.setCode(0);
                rtnInfo.setMsg("状态修改失败！");
            }
        }
        return rtnInfo;
    }

    /**
     * 删除音乐
     * 请求方法：GET
     * 请求URL：musicApp/music/delete/{musicId}
     * 响应结果：JSON字符串
     *
     * @param musicId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete/{musicId}")
    public RtnInfo delete(@PathVariable(value = "musicId", required = true) Integer musicId) throws Exception {
        System.out.println("==========进入到了音乐类别删除方法==========" + musicId);
        // 创建rtnInfo，封装响应到前端的信息。
        RtnInfo rtnInfo = new RtnInfo();

        // 非空校验
        if (StringUtils.isNullOrEmpty(musicId + "")) {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐编号不能为空！");
        } else {
            // 访问数据库
            if (musicMapper.deleteMusic2(musicId) > 0) {
                rtnInfo.setCode(1);
                rtnInfo.setMsg("音乐类别删除成功！");
            } else {
                rtnInfo.setCode(0);
                rtnInfo.setMsg("音乐类别删除失败！");
            }
        }
        return rtnInfo;
    }
}
