package com.wzh.diary.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wzh.diary.sys.entity.User;
import com.wzh.diary.sys.mapper.UserMapper;
import com.wzh.diary.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzh
 * @since 2023-09-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Map<String, Object> login(User user) {
        //根据用户名密码查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件
        queryWrapper.eq(User::getUsername, user.getUsername());
        queryWrapper.eq(User::getPassword, user.getPassword());
        //执行查询
        User one = getOne(queryWrapper);
        //空值判断
        if (one == null) return null;
        //不为空 生成token并存入redis
        String key = "user:" + UUID.randomUUID();
        //存入 redis
        Map<String, Object> userMap = BeanUtil.beanToMap(one, new HashMap<>(),
                CopyOptions.create().
                        setIgnoreNullValue(true).
                        setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        stringRedisTemplate.opsForHash().putAll(key,userMap);
        //d 设置有效期
        stringRedisTemplate.expire(key, 30L, TimeUnit.MINUTES);
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", key);
        return map;
    }

    @Override
    public User getViaRedis() {
        //通过redis查询存入的user
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:bfd1dac2-7a3c-4e11-a8ce-50ce5048c333");
        User user = BeanUtil.mapToBean(entries, User.class,true);
        System.out.println(user);
        return user;
    }
}
