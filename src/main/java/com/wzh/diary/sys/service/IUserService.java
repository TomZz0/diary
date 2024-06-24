package com.wzh.diary.sys.service;

import com.wzh.diary.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzh.diary.sys.entity.UserDto;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2023-09-12
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String,Object> getUserInfo(String token);

    void logout(String token);

    Map<String, Object> getMap(String username, String email, Long pageNo, Long pageSize);

    void add(UserDto userDto);
}
