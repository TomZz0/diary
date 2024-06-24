package com.wzh.diary.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzh.diary.sys.entity.*;
import com.wzh.diary.sys.service.IRoleService;
import com.wzh.diary.sys.service.IUserRoleService;
import com.wzh.diary.sys.service.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wzh
 * @since 2023-09-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("/all")
    public R<List<User>> getAll() {
        List<User> list = userService.list();
        return R.success(list);
    }

    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> map = userService.login(user);
        if (map == null) return R.error("用户名或密码错误");
        return R.success(map);
    }

    @GetMapping("/info")
    public R<Map<String, Object>> getUserInfo(@RequestParam("token") String token) {
        //查询redis中保存的user
        Map<String, Object> map = userService.getUserInfo(token);
        if (map == null) return R.error("登录信息失效，请重新登陆");
        return R.success(map);
    }

    @PostMapping("/logout")
    public R<String> logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return R.success("退出成功");
    }

    @GetMapping("/list")
    public R<Map<String, Object>> list(@RequestParam(value = "username", required = false) String username,
                                       @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "pageNo") Long pageNo,
                                       @RequestParam(value = "pageSize") Long pageSize) {
        Map<String, Object> map = userService.getMap(username, email, pageNo, pageSize);
        return R.success(map);
    }

    @PostMapping()
    public R<String> add(@RequestBody UserDto userDto) {
        //添加时要设置role 并添加到user——role表中 不然不会搜出来
        userService.add(userDto);
        return R.successWithMsg("新增成功");
    }

    @PostMapping("/modify")
    public R<String> modify(@RequestBody UserDto userDto) {
        //添加时要设置role 并添加到user——role表中 不然不会搜出来
        userService.updateById(userDto);
        return R.successWithMsg("修改成功");
    }

    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam("id") Long id){
        userService.removeById(id);
        return R.successWithMsg("删除成功");
    }
}
