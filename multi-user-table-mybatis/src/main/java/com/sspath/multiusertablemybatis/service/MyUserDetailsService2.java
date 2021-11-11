package com.sspath.multiusertablemybatis.service;

import com.sspath.multiusertablemybatis.mapper.UserMapper2;
import com.sspath.multiusertablemybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @FileName: MyUserDetailsService2.java
 * @Description: 不同UserDetailsService代表不同数据源，自定义UserDetailsService类，重写loadUserByUsername方法
 * @Author: ABCpril
 * @Date: 2021/11/10
 */

@Service
public class MyUserDetailsService2 implements UserDetailsService {
    @Autowired
    UserMapper2 userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(userMapper.getRoleByUid(user.getId()));
        return user;
    }
}
