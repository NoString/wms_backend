package com.zhs.system.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.entity.OperateLog;
import com.zhs.system.entity.Users;
import com.zhs.system.entity.UserLogin;
import com.zhs.system.service.LoginService;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.Constant;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }

        //有ManagerAuth注解的才检查token有效
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(ManagerAuth.class)){
            ManagerAuth annotation = method.getAnnotation(ManagerAuth.class);
            if (annotation.value().equals(ManagerAuth.Auth.CHECK)){
                return check(request, response, annotation.memo());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }



    private boolean check(HttpServletRequest request, HttpServletResponse response, String memo) {
        try {
            String token = request.getHeader("token");
            //先判断这个token是否存在
            UserLogin userLogin = loginService.getOne(new QueryWrapper<UserLogin>().eq("token", token));
            if (null == userLogin){
                    response(response, Constant.DENIED);
                return false;
            }

            Users users = usersService.getById(userLogin.getUserId());
            //检查用户是否被禁用
            if (!users.isStatus()){
                response(response, Constant.BLOCK);
                return false;
            }


            // 检查token是否还有效,15分钟
            if (System.currentTimeMillis() - userLogin.getCreateTime().getTime() > 900000){
                response(response, Constant.DENIED);
                return false;
            }

            request.setAttribute("userId", users.getId());
            // 不是通过更新token来刷新有效期,而是通过更新create_time
            userLogin.setCreateTime(new Date());
            loginService.updateById(userLogin);
            // 操作日志
            if (!memo.equals("")) {
                // 记录操作日志
                OperateLog operateLog = new OperateLog();
                operateLog.setAction(request.getRequestURI());
                operateLog.setIp(request.getRemoteAddr());
                operateLog.setUserId(users.getId());
                operateLog.setRequest(new JSONObject(request.getParameterMap()).toString());
                request.setAttribute("operateLog", operateLog);
            }
            return true;
        } catch (Exception e){
            response(response, Constant.DENIED);
            return false;
        }

    }

    public static void response(HttpServletResponse response, String baseRes){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            R r = R.parse(baseRes);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total", "0");
            jsonObject.put("record", "");
            r.add(jsonObject);
            out.print(new JSONObject(r));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
