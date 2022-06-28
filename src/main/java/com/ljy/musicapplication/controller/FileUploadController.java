package com.ljy.musicapplication.controller;

import com.ljy.musicapplication.bean.RtnInfo;
import com.ljy.musicapplication.util.AttFileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传服务器
 */
@RestController
public class FileUploadController {
    /**
     * 上传图书封面
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public RtnInfo modStuPic(HttpServletRequest request) throws IOException {
        RtnInfo rtnInfo = new RtnInfo();
        //判断是否上传了头像
        //转换为多部分请求对象
        MultipartRequest mReq = (MultipartRequest) request;
        //获取头像文件对象
        MultipartFile mf = mReq.getFile("picFile");
        //生成文件名
        String fileName = AttFileUtil.createNewFileId();
        System.out.println("上传后的文件名：" + fileName);
        //判断是否上传了文件
        if (mf != null && mf.getSize() > 0) {
            //生成上传文件
            File upFile = new File(request.getServletContext().getRealPath("imgs/musicPic"), fileName);
            //保存文件
            FileUtils.copyInputStreamToFile(mf.getInputStream(), upFile);
        } else {
            rtnInfo.setCode(-1);
            rtnInfo.setMsg("没有上传头像文件！");
            return rtnInfo;
        }
        rtnInfo.setCode(1);
        rtnInfo.setMsg("修改成功");
        rtnInfo.setResult(fileName);
        System.out.println("图书封面路径：" + fileName);
        return rtnInfo;

    }

}
