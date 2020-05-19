package com.springboot.smslogin.service;

import com.springboot.smslogin.entity.UserInfo;

/**
 * @author 武帅
 * @date 2020/5/18 20:16
 * @description
 */
public interface UserService {

    public abstract boolean register(UserInfo userInfo);

    public abstract boolean login(UserInfo userInfo);

    public abstract boolean findUserInfoByPhone(String phone);


}
