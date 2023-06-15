package com.zhs.system.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils  implements ApplicationContextAware {
    private static ApplicationContext application;

    public SpringUtils() {
    }

    public static void init(ApplicationContext context) {
        application = context;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        application = context;
    }

    private static ApplicationContext getApplicationContext() {
        if (application == null) {
            throw new RuntimeException("500-服务器错误");
        } else {
            return application;
        }
    }

    public static <T> T getBean(Class<T> prototype) {
        return getApplicationContext().getBean(prototype);
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
}
