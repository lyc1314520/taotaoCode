package com.taotao.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:192017/8/25
 * @Modified By:
 */
@RestController
public class PictrueController {

    /** 注入FastDFS服务器的地址 */
    @Value("${fastdfsServerUrl}")
    private String fastdfsServerUrl;
    /**
     * 文件上传 (application/json --> text/html)
     */
    @PostMapping(path="/pic/upload", produces=MediaType.TEXT_HTML_VALUE)
    public String upload(@RequestParam("pic")MultipartFile multipartFile)throws Exception{
        /** 定义响应数据  {"error" : 0, "url" : ""} */
        Map<String, Object> data = new HashMap<>();
        data.put("error", 500);
        try{
            /** 加载配置文件fastdfs-client.conf */
            String conf_filename= this.getClass()
                    .getResource("/fastdfs-client.conf").getPath();
            /** 初始化客户端全局信息 */
            ClientGlobal.init(conf_filename);
            /** 创建存储客户端对象 */
            StorageClient storageClient = new StorageClient();
            /** 上传文件到FastDFS文件服务器 */
            String[] arr = storageClient.upload_file(multipartFile.getBytes(),
                    FilenameUtils.getExtension(multipartFile.getOriginalFilename()),
                    null);
            // http://192.168.12.128 /group1/M00/00/00/xxx.jpg
            StringBuilder url = new StringBuilder(fastdfsServerUrl);
            for (String str : arr){
                url.append("/" + str);
            }
            data.put("error", 0);
            data.put("url", url);
        }catch(Exception ex){
            data.put("error", 500);
        }
        return new ObjectMapper().writeValueAsString(data);
    }
}
