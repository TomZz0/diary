package com.wzh.diary.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzh.diary.sys.entity.Role;
import com.wzh.diary.sys.entity.User;
import com.wzh.diary.sys.entity.UserDto;
import com.wzh.diary.sys.entity.UserRole;
import com.wzh.diary.sys.mapper.UserMapper;
import com.wzh.diary.sys.service.IRoleService;
import com.wzh.diary.sys.service.IUserRoleService;
import com.wzh.diary.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
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


    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

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
    public Map<String,Object> getUserInfo(String token) {
        //通过redis查询存入的user
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(token);
        User user = BeanUtil.mapToBean(entries, User.class,true);
        System.out.println(user);
        //创建返回map
        Map<String,Object> map = new HashMap<>();
        map.put("name",user.getUsername());
        map.put("avatar",user.getAvatarUrl());
        //获取角色
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(UserRole::getUserId,user.getId());
        UserRole one = userRoleService.getOne(userRoleLambdaQueryWrapper);
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.eq(Role::getRoleId,one.getRoleId());
        Role one1 = roleService.getOne(roleLambdaQueryWrapper);

        List<String> roleNameByUserId = new ArrayList<>();
        roleNameByUserId.add(one1.getRoleName());
        map.put("roles",roleNameByUserId);
        return map;
    }

    @Override
    public void logout(String token) {
        //实现注销功能 需要将redis中存储的user删除
        stringRedisTemplate.delete(token);
    }

    public Map<String, Object> getMap(String username, String email, Long pageNo, Long pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(username), User::getUsername, username);
        queryWrapper.eq(StringUtils.hasLength(email), User::getEmail, email);
        //创建page
        Page<User> userPage = new Page<>(pageNo, pageSize);
        this.page(userPage, queryWrapper);
        //创建dto 包括用户的角色
        //单独处理records
        List<User> records = userPage.getRecords();
        List<UserDto> result = new LinkedList<>();
        for (User user : records) {
            //获取用户id
            Long id = user.getId();
            //查询用户id对应的角色id
            LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userRoleLambdaQueryWrapper.eq(UserRole::getUserId, id);
            UserRole one = userRoleService.getOne(userRoleLambdaQueryWrapper);
            if (one == null) continue;
            Long roleId = one.getRoleId();
            System.out.println("roleId="+roleId);
            //通过角色id查询角色
            LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            roleLambdaQueryWrapper.eq(Role::getRoleId,roleId);
            Role role = roleService.getOne(roleLambdaQueryWrapper);
            //创建dto对象返回
            UserDto userDto = new UserDto();
            userDto.setRoleName(role.getRoleName());
            BeanUtils.copyProperties(user, userDto);
            result.add(userDto);
        }

        //创建返回集
        Map<String, Object> map = new HashMap<>();
        map.put("total", userPage.getTotal());
        // map.put("data",userPage.getRecords());
        map.put("data", result);
        return map;
    }

    @Override
    @Transactional
    public void add(UserDto userDto) {
        //写入用户表
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        user.setRegistrationDate(new Date());
        user.setAvatarUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMFFVQMHUSE9HDX0UJfDkFju5LIrxS45PiHbsyk8hWzg&s");
        this.save(user);
        //查询role id
        String role = userDto.getRoleName();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(role),Role::getRoleName,role);
        Role one = roleService.getOne(queryWrapper);
        Long roleId = one.getRoleId();
        //创建userrole对象 写入表
        UserRole userRole = new UserRole();
        userRole.setUserId( user.getId());
        userRole.setRoleId(roleId);
        userRoleService.save(userRole);
    }
}
