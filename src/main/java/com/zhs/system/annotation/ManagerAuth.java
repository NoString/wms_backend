package com.zhs.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ManagerAuth {

    String value() default "";

    Auth isCheck() default ManagerAuth.Auth.CHECK;


    public static enum Auth {
        CHECK,
        NONE;

        private Auth() {
        }
    }
}
