package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController类
 * 用于测试RESTful API的示例控制器
 */
@RestController
public class HelloController {

    /**
     * 简单的Hello接口
     * @return 欢迎信息
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
