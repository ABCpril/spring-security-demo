package com.sspath.passwordencoderupgrade.mapper;

import com.sspath.passwordencoderupgrade.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName: UserMapper.java
 * @Description: Mapper类
 * @Author: ABCpril
 * @Date: 2021/11/15
 */
@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    Integer updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);
}
