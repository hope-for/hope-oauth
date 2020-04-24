package com.hope.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hope.oauth.mapper.UserInfoMapper;
import com.hope.oauth.model.bean.UserInfoBean;
import com.hope.oauth.model.doman.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsServiceImpl
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /*重写UserDetailsService的loadUserByUsername方法*/
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {

        //根据手机号查询这个用户信息
        UserInfoBean userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfoBean>().like("mobile", mobile));

        List<Authority> authorityList = new ArrayList<Authority>();
        authorityList.add(new Authority(1L, "ROLE_ADMIN"));
        authorityList.add(new Authority(2L, "ROLE_USER"));

        return new User(userInfo.getName(), userInfo.getPassword(), true, true, true, true, authorityList);
    }
}
