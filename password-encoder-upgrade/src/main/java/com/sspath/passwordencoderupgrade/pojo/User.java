package com.sspath.passwordencoderupgrade.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @FileName: User.java
 * @Description: user表实体类
 * @Author: ABCpril
 * @Date: 2021/11/15
 */

@Getter
@Setter
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
}
