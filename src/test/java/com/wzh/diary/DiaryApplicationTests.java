package com.wzh.diary;

import com.wzh.diary.sys.entity.User;
import com.wzh.diary.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class DiaryApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("jerry");
        user.setPassword("jerry");
        user.setEmail("1210374096@qq.com");
        user.setAvatarUrl("https://c-ssl.duitang.com/uploads/item/201907/18/20190718092857_bygtl.jpg");

        user.setRegistrationDate(new Date());
        userMapper.insert(user);
    }

}
