package com.wzh.diary.sys.util;

import com.wzh.diary.sys.entity.User;
import com.wzh.diary.sys.entity.UserDto;

import java.util.Objects;

/**
 * @author wzh
 * @date 2024年06月26日 14:50
 * Description:
 */
public class BaseContext {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
