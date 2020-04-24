package com.hope.oauth.model.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * UserInfoBean
 * 用户信息表，根据自己业务配置自己数据的用户bean
 *
 * @author 低调小熊猫(aodeng)
 * @date 2020-04-22
 */
@TableName("supplier")
@Data
public class UserInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String mobile;
    private String password;
}
