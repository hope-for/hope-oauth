package com.hope.oauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.oauth.mapper.UserInfoMapper;
import com.hope.oauth.model.bean.UserInfoBean;
import com.hope.oauth.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * UserInfoServiceImpl
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-22
 */
@Service
public class IUserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoBean> implements IUserInfoService {
}
