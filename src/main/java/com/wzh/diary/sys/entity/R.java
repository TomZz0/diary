package com.wzh.diary.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回结果类，服务端响应的数据都会封装成此对象
 * @param <T>
 */
@Data
public class R<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Integer userType;

    private Map map = new HashMap(); //动态数据
    public static <T> R<T> success(T object,Integer userType) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 20000;
        r.userType = userType;
        return r;
    }
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 20000;
        return r;
    }
    public static <T> R<T> successWithMsg(String msg) {
        R<T> r = new R<T>();
        r.code = 20000;
        r.msg = msg;
        return r;
    }
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
