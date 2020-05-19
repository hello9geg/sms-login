package com.springboot.smslogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 武帅
 * @date 2020/5/16 10:48
 * @description
 */
@Controller
@RequestMapping("/indexController")
public class IndexController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/registered")
    public String registered(){
        return "registered";
    }

}
