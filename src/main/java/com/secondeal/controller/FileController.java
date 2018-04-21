package com.secondeal.controller;

import com.secondeal.service.org.GoodsI;
import com.secondeal.util.CodeMsg;
import com.secondeal.util.FileUtil;
import com.secondeal.util.Result;
import com.secondeal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    GoodsI goodsI;


    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String location = FileUtil.handleFileUpload(files);
        return location;
    }
}
