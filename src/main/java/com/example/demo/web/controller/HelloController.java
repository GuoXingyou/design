package com.example.demo.web.controller;


import com.example.demo.web.anotation.TestAno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/17/9:23
 * @Desc:
 **/
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/say")
    @ResponseBody
    @TestAno
    public String sayHello(){

        return think();
    }

//    @TestAno
    public String think(){
        return "I'm thinking...";
    }

}
