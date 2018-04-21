package com.secondeal.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class FileUtil {


    /**
     * 单文件上传
     *
     * @param multiReq
     */
    public static String FileUpload(MultipartHttpServletRequest multiReq) throws IOException {
        // 获取上传文件的路径
        String uploadFilePath = multiReq.getFile("file").getOriginalFilename();
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(
                uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
        FileOutputStream fos = null;
        FileInputStream fis = null;

        fis = (FileInputStream) multiReq.getFile("file").getInputStream();
        // 将图片存储到static/images目录下
        fos = new FileOutputStream(new File("src/main/resources/static/images/" + StringUtil.getUUID()
                + ".")
                + uploadFileSuffix);
        String location = ("src/main/resources/static/images/" + StringUtil.getUUID() + ".") + uploadFileSuffix;
        byte[] temp = new byte[1024];
        int i = fis.read(temp);
        while (i != -1) {
            fos.write(temp, 0, temp.length);
            fos.flush();
            i = fis.read(temp);
        }
        System.out.println(location);
        return location;

    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

//    public static String handleFileUpload(List<MultipartFile> files) {
//        String buff = "";
//        for (int i = 0; i < files.size(); i++){
//            String contentType = files.get(i).getContentType();
//            String uploadFilePath = files.get(i).getOriginalFilename();
//            String uploadFileSuffix = uploadFilePath.substring(
//                    uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
//            String imgname = StringUtil.getUUID() + ".";
//            String filePathPer = "src/main/resources/static/images/";
////            String filePathPer = "e:/test/";
//            try {
//                FileUtil.uploadFile(files.get(i).getBytes(), filePathPer, imgname + uploadFileSuffix);
//                buff = buff + "\\" + ("images/" + imgname) + uploadFileSuffix;
//
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
//
//        //返回json
//        return buff;
//    }


    public static String handleFileUpload(List<MultipartFile> files) {
        String buff = "";
        Icon icon = null;
        for (int i = 0; i < files.size(); i++){
            String contentType = files.get(i).getContentType();
            String uploadFilePath = files.get(i).getOriginalFilename();
            String uploadFileSuffix = uploadFilePath.substring(
                    uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
            String imgname = StringUtil.getUUID() + ".";
            String filePathPer = "src/main/resources/static/images/";
//            String filePathPer = "e:/test/";
            try {
                FileUtil.uploadFile(files.get(i).getBytes(), filePathPer, imgname + uploadFileSuffix);
                buff = buff + "\\" + ("images/" + imgname) + uploadFileSuffix;
                icon = ImageUtil.getFixedIcon(filePathPer+imgname+uploadFileSuffix,426,590);

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        //返回json
        return buff;
    }
}
