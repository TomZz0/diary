package com.wzh.diary;

import com.wzh.diary.sys.entity.Role;
import com.wzh.diary.sys.service.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzh
 * @date 2023年09月13日 12:41
 * Description:
 */
@SpringBootTest
public class ServiceTest {
    @Resource
    private IRoleService iRoleService;

    @Test
    public void test(){
        List<Role> r = iRoleService.list();
        System.out.println(r);
    }
}
