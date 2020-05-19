package com.springboot.smslogin.dao;

import com.springboot.smslogin.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author 武帅
 * @date 2020/5/18 20:13
 * @description
 */
public interface UserMapper {

    @Insert("insert into userInfo(phone,password) values(#{phone}, #{password})")
    public abstract Integer saveUserInfo(UserInfo userInfo);

    @Select("select * from userInfo where phone = #{phone} and password = #{password}")
    public UserInfo findUserInfoByPhoneAndPwd(UserInfo userInfo);

    @Select("select * from userInfo where phone = #{phone}")
    public abstract UserInfo findUserInfoByPhone(String phone);



}
