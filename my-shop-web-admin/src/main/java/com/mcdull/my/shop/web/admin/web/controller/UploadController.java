package com.mcdull.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传控制器
 */
@Controller
public class UploadController {

    private final static String UPLOAD_PATH = "/static/upload/";

    /**
     * 文件上传
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, HttpServletRequest httpServletRequest){
        Map<String,Object> result=new HashMap<>();

        //获取文件后缀
        String fileName=dropFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        //文件存放路径
        String filePath = httpServletRequest.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //判断路径是否存在，不存在则创建文件夹
        File file =new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //将文件写入目标
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            dropFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.put("fileName", UPLOAD_PATH+file.getName());
        return result;
    }
}
