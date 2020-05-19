package com.springboot.smslogin.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.springboot.smslogin.entity.UserInfo;
import com.springboot.smslogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

/**
 * @author 武帅
 * @date 2020/5/17 10:36
 * @description
 */
@Controller
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/sms")
    @ResponseBody   //因为后台传来的ajax所以要加这个注解
    //smsphone 前台传来的smsphone(键)
    public String smsCode(String smsphone){

        String json = null;
        //判断手机号是否被注册过
        boolean result = userService.findUserInfoByPhone(smsphone);

        if(result == false){
            json = "{\"message\":"+ false +"}";
            return json;
        }else{
            //发送短信 获取返回值
            String sms = SMS(smsphone);
            json = "{\"message\":"+ true +",\"sms\":\""+ sms +"\"}";
            return json;
        }
    }



    //发送短信 返回发送的验证码
    private String SMS(String phone) {

        String phoneNumber = phone;

        //短信内容 templateId这个Id对应着短信内容
        int templateId = 200461;

        //验证码
        String[] params = new String[1];

        //生成一个随机的验证码
        String code = "";
        Random r = new Random();
        for (int i = 1; i <=4 ; i++) {
            code += r.nextInt(10);
        }
        //存放随机的一个验证码
        params[0] = code;

        System.out.println(code+"=====================");

        //签名
        String sign = "tLain公众号";

        //拿到发送短信的核心类
        SmsSingleSender sender  = new SmsSingleSender(1400142856,"21306d751cfdf61ed433e506da242522");
        //发送短信
        try {
            SmsSingleSenderResult result = sender.sendWithParam("86", phoneNumber, templateId, params, sign, "", "");
            //也可以拿到返回值 这里的返回值用于查看你的错误信息
            System.out.println(result);
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }

    @RequestMapping("/register")
    public String register(UserInfo userInfo){
        boolean result = userService.register(userInfo);

        if(result){
            //重定向到登录页面
            return "redirect:/indexController/index";
        }else{
            return "redirect:/indexController/registered";
        }
    }

    @RequestMapping("/login")
    public String login(UserInfo userInfo, Model model, HttpSession session){
        boolean result = userService.login(userInfo);
        if (result){

            //注册成功重定向到主页面 顺便把用户存取到会话中, 防止表单重复提交,重定向到后台页面
            session.setAttribute("userInfo",userInfo);
            return "login_success";
        }else{
            model.addAttribute("message","用户名或者密码错误");
            return "index";
        }
    }

}
