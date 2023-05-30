package com.zhs.system.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.HashMap;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String DATA = "data";

    public R(Integer code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    public static R ok() {
        return parse("200-success");
    }

    public static R ok(String msg) {
        R r = ok();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object obj) {
        return parse("200-success").add(obj);
    }

    public static R error() {
        return parse("500-error");
    }

    public static R error(String msg) {
        R r = error();
        r.put("msg", msg);
        return r;
    }

    public R add(Object obj) {
        this.put("d", obj);
        return this;
    }

    public static R parse(String message) {
        if (message == null || message.equals("")) {
            return parse("500-error");
        } else {
            String[] msg = message.split("-");
            return msg.length == 2 ? new R(Integer.parseInt(msg[0]), msg[1]) : parse("500-".concat(message));
        }
    }
}
