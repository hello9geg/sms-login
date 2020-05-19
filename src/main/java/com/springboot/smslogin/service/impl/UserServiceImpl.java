package com.springboot.smslogin.service.impl;

import com.springboot.smslogin.dao.UserMapper;
import com.springboot.smslogin.entity.UserInfo;
import com.springboot.smslogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 武帅
 * @date 2020/5/18 20:17
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(UserInfo userInfo) {
        Integer rows = userMapper.saveUserInfo(userInfo);
        if(rows > 0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean login(UserInfo userInfo) {
        UserInfo user = userMapper.findUserInfoByPhoneAndPwd(userInfo);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean findUserInfoByPhone(String phone) {
        UserInfo userInfo = userMapper.findUserInfoByPhone(phone);

        //这边条件必须为对象， 不能拿属性比较 否则会出空指针异常
        if(userInfo != null){
            return false;
        }
        return true;
    }
}
