package com.zhs.system.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.AbstractController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;


public class BaseController  {


    public String getIp(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }


    public <T> void parseParamToWrapper(Map<String, Object> params, QueryWrapper<T> wrapper, Class<T> cls) {
        //将时间段字符串分割,并拼接成sql
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (key.contains("_date")) {
                String combineDate = (String) params.get(key);
                String[] split = combineDate.split(",");
                String col = key.split("_date")[0];
                String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(split[1]));
                String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(split[3]));
                wrapper.between(col, startDate.toString(), endDate.toString());
            } else {
                if ("true".equalsIgnoreCase(String.valueOf(value))
                        || "false".equalsIgnoreCase(String.valueOf(value))) {
                    value = Boolean.valueOf(String.valueOf(value));
                    wrapper.eq(key, value);
                } else {
                    wrapper.like(key, value);
                }
            }
        }
    }

    public <T> void allLike(QueryWrapper<T> wrapper, String condition, ArrayList<String> unclearColumns, Map<String, Object> unclearParams) {
        ArrayList<String> realColumns = new ArrayList<>();
        unclearParams.remove("all");
        unclearParams.remove("columns");
        unclearColumns.remove("action");
        for (String column : unclearColumns) {
            if (unclearParams.containsKey(column)){
                continue;
            }
            realColumns.add(column);
        }
        wrapper.nested(item -> {
            for (String column : realColumns) {
                item.like(column, condition);
                item.or();
            }
        });


    }

}
