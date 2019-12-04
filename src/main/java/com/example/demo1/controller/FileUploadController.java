package com.example.demo1.controller;

import com.example.demo1.entity.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FileUploadController {


    @RequestMapping("/upload")
    public Response say(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response res = new Response();
        UUID uuid = UUID.randomUUID();

        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        fileName = uuid.toString() + suffixName;
        String path = "/home/y-minzh/demo/";

        File dir = new File(path,fileName);
        File filepath = new File(path);

        if (!filepath.exists()){
            filepath.mkdir();
        }
        System.out.println(file);
        file.transferTo(dir);

        String url = "http://127.0.0.1:3001/"+fileName;

        res.setData(url);
        return res;
    }
}
