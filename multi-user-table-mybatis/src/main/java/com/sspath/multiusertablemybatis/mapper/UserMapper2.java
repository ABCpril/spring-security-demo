package com.sspath.multiusertablemybatis.mapper;

import com.sspath.multiusertablemybatis.pojo.Role;
import com.sspath.multiusertablemybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @FileName: UserMapper2.java
 * @Description: TODO
 * @Author: ABCpril
 * @Date: 2021/11/10
 */
public interface UserMapper2 {
    List<Role> getRoleByUid(Integer id);
    User loadUserByUserName(String username);
}
