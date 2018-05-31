package com.sunfy.fyjobtomysql.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/sfy")
public class HelloController {


    @Value("${cupSize}")
    private String cupSize;

    @Value("${content}")
    private String content;

    @GetMapping(value = "/hello")
    public String say(@RequestParam(value = "id",required = false, defaultValue = "0") Integer id){
        return "cupSize:"+cupSize;
    }
}
