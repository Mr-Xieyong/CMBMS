package com.xy.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.*;

/**
 * @author Xieyong
 * @date 2019/12/2 - 15:28
 */
@RestController
@RequestMapping("/trans")
public class TransfileServlet extends HttpServlet {

//    private String path = "E:/test";

    //    @Override
    @RequestMapping("/file")
    protected void doPost(@RequestParam("file") MultipartFile file) throws ServletException, IOException {

        OutputStream outputStream = null;
        InputStream inputStream = null;
        String fileName = null;

        try {
            inputStream = file.getInputStream();
            fileName = file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = "C:\\D\\temp";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len = -1;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            //重命名文件，防止相同文件名文件被覆盖
            fileName = System.currentTimeMillis() + fileName;

            outputStream = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//        //1.判断是否为mulitpart请求
//        if (!ServletFileUpload.isMultipartContent(request)) {
//            System.out.println("1");
//            throw new RuntimeException("这不是一个multipart请求......");
//        }
//
//
//        //2.创建一个FileItem工厂
//        System.out.println("2");
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        //设置临时文件的边界值 大于改值，上传文件会保存于临时文件中；小于等于该值，上传文件直接写入到内存。单位：字节
//        factory.setSizeThreshold(1024 * 1024 * 1);//1M
//
////            //临时文件 上传还没有完成时文件数据暂存的文件  在当前项目下建立一个临时目录 上传完成之后临时文件应该删除=>item.delete();
////            String tempPath = this.getServletContext().getRealPath("C:\\E\\");
////            System.out.println("tempPath:"  + tempPath);
////            File temp = new File(tempPath);
////            factory.setRepository(temp);
//
//        //3.创建文件上传核心组件
//        System.out.println("3");
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        //设置每一个item的头部字符编码，可以解决文件名的中文乱码问题
////            upload.setHeaderEncoding("UTF-8");
//
//
//        //设置单个文件上传的最大值 2M
//        upload.setFileSizeMax(1024 * 1024 * 2);
//
//        //设置一次上传所有文件的最大值 5M (对于上传多个文件时起作用)
//        upload.setSizeMax(1024 * 1024 * 5);
//
//        try {
//            //4.解析请求,获取所有的item
//            System.out.println("4");
////            List<FileItem> items = upload.parseRequest(request);
//            List<FileItem> items = upload.parseRequest(request);
//            //5.遍历items
//            for (FileItem item : items) {
//                System.out.println("5");
//                //若item为普通表单项
//                if (item.isFormField()) {
//                    String filedName = item.getName();//获取表单项名称
//                    String filedValue = item.getString();//获取表单项的值
//                    //String filedValue = item.getString("utf-8");//获取表单项的值,并设置字符集为utf-8
//
//                    System.out.println(filedName + "=" + filedValue);
//                } else {
//                    //若item为文件表单项
//                    String fileName = item.getName();//获取上传文件的原始名称
//
//                    //通过加上当前系统时间，可以确保每个文件的文件名不一样，避免相同的文件名导致文件被覆盖（替换）
//                    fileName = System.currentTimeMillis() + fileName;
//
//
//                    //获取输入流，其中有上传文件的内容
//                    InputStream is = item.getInputStream();
//                    //获取文件保存在服务器的路径
//                    String path = this.getServletContext().getRealPath("doc");
//
//                    //获取当前格式化的时间
//                    Date date = new Date();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    String nowDate = sdf.format(date);
//
////                    Calendar now = Calendar.getInstance();
////                    //获取年、月、日
////                    int year = now.get(Calendar.YEAR);
////                    int month = now.get(Calendar.MONTH) + 1;
////                    int day = now.get(Calendar.DAY_OF_MONTH);
////                    int day = now.get(Calendar.DATE);
//
//                    path = path + "/" + nowDate;
//
//                    //为避免文件过多导致一个文件夹装不下，所以可以通过时间的不同（如每天创建一个 or 每年一个=> 每月一个 => 每天一个）
//                    //   path = path + "/" + year +"/" + month + "/" +day;
//                    //   下面就是 dirPath.mkdirs();    创建多级目录
//                    // 来创建文件夹的形式来解决
//                    //若该目录不存在，则创建一个文件夹
//                    File dirPath = new File(path);
//                    if (!dirPath.exists()) {
//                        dirPath.mkdir();
//                    }
//
//                    //创建目标文件，将用来保存上传文件
//                    File descFile = new File(path, fileName);
//                    //创建文件输出流
//                    OutputStream os = new FileOutputStream(descFile);
//                    //将输入流中的数据写入到输出流中
//                    int len = -1;
//                    byte[] bt = new byte[1024];
//                    while ((is.read(bt)) != -1) {
//                        System.out.println("6");
//                        os.write(bt, 0, len);
//                    }
//
//                    //关闭流
//                    os.close();
//                    is.close();
////                    item.delete();//删除临时文件
//                    System.out.println("7");
//                }
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }


        //        //以输入流的形式获取multipart请求的请求体内容
//        ServletInputStream is = req.getInputStream();
//
//        //将输入流的数据写入到标准输出流中
//        PrintWriter pw = resp.getWriter();
//        byte[] bt = new byte[1024];
//        int len = -1;
//        while((len = is.read(bt)) != -1) {
//            String str = new String(bt,0,len);
//            pw.write(str);
//        }
//        //关闭输入流
//        is.close();

}
