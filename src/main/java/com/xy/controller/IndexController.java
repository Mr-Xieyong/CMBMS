package com.xy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xieyong
 * @date 2019/12/17 - 15:30
 */
@Controller
@RequestMapping("/indexController")
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        System.out.println("进入登录页。。。");
        return "/index.jsp";
    }

}
