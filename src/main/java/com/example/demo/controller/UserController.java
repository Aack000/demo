package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 1. 获取用户信息（查）
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable("id") Long id) {
        //将原本直接返回的 String 包装入 Result.success() 中
        String data="查询成功，正在返回 ID 为"+id+"的用户信息";
        return Result.success(data);
    }

    // 2. 新增用户（增）- 接收 JSON 格式数据
    @PostMapping
    public Result<String> createUser(@RequestBody User user) {
        String data="新增成功，接收到用户：" + user.getName() + "，年龄：" + user.getAge();
        return Result.success(data);
    }

    // 3. 全量更新用户信息（改）
    @PutMapping("/{id}")
    public Result<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        String data="更新成功，ID " + id + " 的用户已修改为：" + user.getName();
        return Result.success(data);
    }

    // 4. 删除用户（删）
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        String data="删除成功，已移除 ID 为 " + id + " 的用户";
        return Result.success(data);
    }

    //附加题
    @GetMapping("/{id}/error")
    public Result<String> getErroruser(@PathVariable("id") Long id) {
        // 故意触发异常（除零错误）
        int a = 1 / 0;
        return Result.success("查询成功，正在返回 ID 为 " + id + " 的用户信息");
    }
}