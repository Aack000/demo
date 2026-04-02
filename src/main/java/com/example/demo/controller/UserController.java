package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 1.新增用户(注册)-路径为POST/api/users
    @PostMapping
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 2.用户登录-路径为 POST /api/users/login
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 3.获取用户信息(查)-用于测试拦截器放行
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable("id") Long id) {
        return Result.success("查询成功，正在返回ID为" + id + "的用户信息");
    }
}