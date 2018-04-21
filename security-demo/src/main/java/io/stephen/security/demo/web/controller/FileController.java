package io.stephen.security.demo.web.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 10447
 * @since 2017/11/4
 */
@RestController
@RequestMapping("/file")
public class FileController {


    private String filePath = "C:\\Users\\10447\\Documents";


    /**
     * 上传文件
     * @return
     * @throws IOException
     */
    @PostMapping()
    @ApiOperation("上传文件")
    public Map<String, Object> upload(MultipartFile file) throws IOException {

        System.out.println("名字："+file.getName());
        System.out.println(file.getSize());

        File locaFile = new File(filePath, file.getName()+".txt");

        file.transferTo(locaFile);

        Map<String, Object> map = new HashMap<>();
        map.put("path", filePath + file.getName());
        return map;
    }


    /**
     * 下载
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping()
    @ApiOperation(value = "下载文件")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (InputStream inputStream = new FileInputStream(new File(filePath+"\\user.sql"));
             OutputStream outputStream= response.getOutputStream();
        ) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=user.sql");
            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();
        }
    }


}
