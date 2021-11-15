package com.sspath.passwordencoderupgrade.service;

import com.sspath.passwordencoderupgrade.mapper.UserMapper;
import com.sspath.passwordencoderupgrade.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * @FileName: UserService.java
 * @Description: 实现UserDetailsPasswordService接口，重写updatePassword功能
 * @Author: ABCpril
 * @Date: 2021/11/15
 */

@Service
public class UserService implements UserDetailsService, UserDetailsPasswordService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        Integer result = userMapper.updatePassword(userDetails.getUsername(), newPassword);
        if (result == 1) {
            ((User) userDetails).setPassword(newPassword);
        }
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.loadUserByUsername(username);
    }
}
