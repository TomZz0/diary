package com.wzh.diary.sys.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzh
 * @date 2023年09月14日 19:18
 * Description:
 */
@Data
@NoArgsConstructor
public class UserDto extends User{
    private String roleName;

    public UserDto(String roleName) {
        this.roleName = roleName;
    }
}
