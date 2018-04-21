package com.secondeal.controller;

import com.secondeal.util.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {


    public PageData getPageData() {
        return new PageData(this.getParam());
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


    //get request headers
    protected String getHeadersInfo() {
        HttpServletRequest req = this.getRequest();
        String token = req.getHeader("Authorization");
        return token;
    }

    protected Map<String, Object> getParam() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String method = request.getMethod();
        Enumeration<?> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (key != null) {
                if (key instanceof String) {
                    String value = request.getParameter(key.toString());
                    if ("GET".equals(method)) {//前台encodeURIComponent('我们');转码后到后台还是ISO-8859-1，所以还需要转码
                        try {
                            value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    paramMap.put(key.toString(), value);
                }
            }
        }
        return paramMap;
    }
}
