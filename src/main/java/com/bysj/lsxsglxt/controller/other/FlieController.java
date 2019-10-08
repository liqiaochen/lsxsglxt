package com.bysj.lsxsglxt.controller.other;

import com.bysj.lsxsglxt.utils.ServerResponse;
import com.bysj.lsxsglxt.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：liqiaochen
 * @date ：Created in 2019/4/12 15:52
 * @description：文件上传
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("/file")
public class FlieController {

    @PostMapping("/upload")
    @ResponseBody
    public ServerResponse<String> fileUpload(@RequestParam("file") MultipartFile srcFile) {
        //前端没有选择文件，srcFile为空
        if (srcFile.isEmpty()) {
            return ServerResponse.createByError("请选择一个文件");
        }
        System.out.println(srcFile);
        String relpath = null;
        //选择了文件，开始上传操作
        try {
            //构建上传目标路径，找到了项目的target的classes目录
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!destFile.exists()) {
                destFile = new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:" + destFile.getAbsolutePath());
            //拼接子路径，以时间日期创建文件夹
            Date date = new Date();
            String dateString = new SimpleDateFormat("yyyyMMdd/").format(date);
            File upload = new File(destFile.getAbsolutePath(), "static/upload/"+dateString);
            //若目标文件夹不存在，则创建
            if (!upload.exists()) {
                upload.mkdirs();
            }
            //使用uuid重命名文件
            String fileName = srcFile.getOriginalFilename();
            String    fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            System.out.println("完整的上传路径：" + upload.getAbsolutePath() + "/" + fileExtension);
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //通过项目路径，拼接上传路径
            String uppath=UUIDUtil.getUUID32() + fileExtension;
            //组装相对路径
            relpath= dateString+uppath;
            System.out.println("relpath:==="+relpath);
            Path path = Paths.get(upload.getAbsolutePath() + "/" + uppath);
            System.out.println(path);
            //** 开始将源文件写入目标地址
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.createBySuccess("文件上传成功",relpath);
    }
}

