package com.wzh.diary.sys.controller;

import com.wzh.diary.sys.entity.R;
import com.wzh.diary.sys.entity.User;
import com.wzh.diary.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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

    @GetMapping("/all")
    public R<List<User>> getAll() {
        List<User> list = userService.list();
        return R.success(list);
    }

    @PostMapping("/login")
    public R<Map<String,Object>> login(@RequestBody  User user){
        Map<String,Object> map = userService.login(user);
        if (map==null) return R.error("用户不存在");
        return R.success(map);
    }

    @GetMapping("/one")
    public R<User> getOne() {
        //查询redis中保存的user
        User user = userService.getViaRedis();
        if (user==null) return R.error("失败");
        return R.success(user);
    }
}
