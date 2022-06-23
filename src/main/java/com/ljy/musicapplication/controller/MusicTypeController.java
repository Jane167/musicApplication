package com.ljy.musicapplication.controller;


import com.ljy.musicapplication.bean.MusicType;
import com.ljy.musicapplication.bean.RtnInfo;
import com.ljy.musicapplication.mapper.MusicTypeMapper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 完成MusicType对应的comtroller
 */

@RestController
@RequestMapping("/musictype")
public class MusicTypeController {
    @Autowired
    private MusicTypeMapper musicTypeMapper;
    //

    /**
     * 添加音乐类别的方法
     * 请求方法：POST
     * 请求URL：musicApp/musictype/add/
     * 请求参数：表单
     * 响应结果：JSON字符串
     * @param musicType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RtnInfo add(MusicType musicType) throws Exception{
        System.out.println("-----进入到了音乐类别管理-----"+ musicType);
        // 创建一个rtn对象用来封装响应到前端的信息
        RtnInfo rtnInfo = new RtnInfo();
        // 非空校验
        if(StringUtils.isNullOrEmpty(musicType.getMusictypeName())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("类别名称不能为空");
        }else if(StringUtils.isNullOrEmpty(musicType.getMusictypeDepict())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("类别描述不能为空");
        }else{
            // 访问数据库

            if(musicTypeMapper.addMusicType(musicType)>0){
                rtnInfo.setCode(1);
                rtnInfo.setMsg("音乐类别添加成功！");
            }else{
                rtnInfo.setCode(0);
                rtnInfo.setMsg("音乐类别添加失败！");
            }

        }
        return rtnInfo;
    }

    /**
     * 查询所有音乐类别的方法（支持关键字，不支持分页）
     * 请求方法：GET
     * 请求URL：/musicApp/musictype/findAll
     * 响应结果：JSON字符串
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findAll/{musictypeName}", method = RequestMethod.GET)
    public RtnInfo findAll(@PathVariable(value = "musictypeName", required = true)String musictypeName) throws Exception{
        System.out.println("-----进入到了音乐类别列表查询-----"+ musictypeName);

        // 创建rtnInfo，封装响应到前端的信息
        RtnInfo rtnInfo = new RtnInfo();
        // 对关键字进行非空校验
        // 如果要查询所有，就传一个单引号占位
        if(StringUtils.isNullOrEmpty(musictypeName) || musictypeName.equals("''")){
            musictypeName = "";
        }
        // 访问数据库
        List<MusicType> list = musicTypeMapper.findMusicTypeAll(musictypeName);
        if(list != null){
            // 封装信息到前端
            rtnInfo.setCode(1);
            rtnInfo.setMsg("音乐类别列表信息获取成功！");
            rtnInfo.setResult(list);
        }else{
            // 封装信息到前端
            rtnInfo.setCode(0);
            rtnInfo.setMsg("音乐类别列表信息获取失败！");
        }


        return rtnInfo;
    }


    /**
     * 根据id修改音乐类别
     * 请求方式：POST
     * 请求URL：/musicApp/musictype/update
     * 请求参数：表单
     * 响应结果：JSON字符串
     * @param musicType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public RtnInfo update(MusicType musicType) throws Exception{
        System.out.println("======进入到修改音乐类别的方法：=====" + musicType);
        // 创建一个rtnInfo,封装返回给前端的信息
        RtnInfo rtnInfo = new RtnInfo();

        // 非空校验
        if(StringUtils.isNullOrEmpty(musicType.getMusictypeId() + "")){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐类别编号不能为空！");
        }else if(StringUtils.isNullOrEmpty(musicType.getMusictypeName())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐类别名称不能为空！");
        }else if(StringUtils.isNullOrEmpty(musicType.getMusictypeDepict())){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐类别描述不能为空！");
        }else{
            // 访问数据库
            if(musicTypeMapper.updateMusicType(musicType) > 0){
                rtnInfo.setCode(1);
                rtnInfo.setMsg("音乐类别修改成功！");
            }else {
                rtnInfo.setCode(0);
                rtnInfo.setMsg("音乐类别修改失败！");
            }
        }
        return rtnInfo;
    }


    /**
     * 请求方式：GET
     * 请求URL：/musicApp/musictype/delete/{musictypeId}
     * 响应结果：JSON字符串
     * @param musictypeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete/{musictypeId}")
    public RtnInfo delete(@PathVariable(value = "musictypeId", required = true) Integer musictypeId) throws Exception{
        System.out.println("==========进入到了音乐类别删除方法==========" + musictypeId);
        // 创建rtnInfo，封装响应到前端的信息。
        RtnInfo rtnInfo  = new RtnInfo();

        // 非空校验
        if(StringUtils.isNullOrEmpty(musictypeId + "")){
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("音乐类别编号不能为空！");
        }else{
            // 访问数据库
            if(musicTypeMapper.deleteMusicType2(musictypeId) > 0){
                rtnInfo.setCode(1);
                rtnInfo.setMsg("音乐类别删除成功！");
            }else{
                rtnInfo.setCode(0);
                rtnInfo.setMsg("音乐类别删除失败！");
            }
        }
        return rtnInfo;
    }
}
