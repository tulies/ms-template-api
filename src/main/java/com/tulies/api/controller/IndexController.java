package com.tulies.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("index")
    public String index(){
        return "hello!";
    }
}
