package com.xy.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Xieyong
 * @date 2019/12/2 - 9:59
 */
@RestController
@RequestMapping("/filetransController")
public class FiletransController {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return "failed";
        }
        // 获取文件存储路径（绝对路径）
        String path = req.getServletContext().getRealPath("/temp");
        System.out.println(path);
//        String path = "C:\\D\\temp";
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        //重命名文件（防止同名文件被覆盖）
        String newFileName = new Date().getTime() + fileName;

        // 创建文件实例
        File filePath = new File(path, newFileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
        return "index";
    }

    @RequestMapping("/springUpload")
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="C:/D/temp/"+file.getOriginalFilename();
                    System.out.println(path);
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("Spring方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/success";
    }


    @RequestMapping(value = "/toFile", method = RequestMethod.POST)
    public String toFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String path = "C:\\D\\temp";
        String path = "C:/D/temp";

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        //若item为普通表单项，一般用注解@RequestParam("file")就不用判断了，因为这里限制表单的name=file type为file就行了
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest  multipartRequest = commonsMultipartResolver.resolveMultipart(request);

            Iterator<String> uploadFilenames = multipartRequest.getFileNames();
            if(uploadFilenames.hasNext()) {
                String uploadFilename = uploadFilenames.next();
                List<MultipartFile> uploadFiles = multipartRequest.getFiles(uploadFilename);
                for (MultipartFile uploadFile : uploadFiles) {
                    //获取输入流，其中有上传文件的内容
                    InputStream is = uploadFile.getInputStream();

                    String fileName = uploadFile.getName();
                    File filePath = new File(path, fileName);
                    // 如果文件目录不存在，创建目录
                    if (!filePath.getParentFile().exists()) {
                        filePath.getParentFile().mkdirs();
                        System.out.println("创建目录" + filePath);
                    }

                    //创建文件输出流
                    OutputStream os = new FileOutputStream(filePath);
                    int len;
                    byte[] bytes = new byte[1024];
                    while ((len = is.read(bytes)) != -1) {
                        os.write(bytes, 0, len);
                    }
                    is.close();
                    os.close();
                }
            }

        }
        return "index";
    }



    @RequestMapping("/download01")
    public void download(HttpServletRequest req, HttpServletResponse resp, String filename) throws Exception {

        String realPath = req.getServletContext().getRealPath("/temp");//获取下载文件的路径
//        String realPath = "C:/D/temp";//获取下载文件的路径
        File file = new File(realPath, filename);//把下载文件构成一个文件处理   filename:前台传过来的文件名称
        System.out.println("---------->" + file);
        //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
        resp.setContentType("application/x-msdownload");
        //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型 如果文件名有中文的话，进行URL编码，让中文正常显示
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));

        //下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
        Files.copy(file.toPath(), resp.getOutputStream());//Files.copy(要下载的文件的路径,响应的输出流)
    }


    @RequestMapping("/download02")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String filename) throws IOException  {
//            String realPath = request.getServletContext().getRealPath("/temp");//获取下载文件的路径
            String realPath = "C:/D/temp";//获取下载文件的路径
            File file = new File(realPath, filename);//把下载文件构成一个文件处理   filename:前台传过来的文件名称

            HttpHeaders headers = new HttpHeaders();//设置头信息
            String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");//设置响应的文件名

            headers.setContentDispositionFormData("attachment", downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }
}
