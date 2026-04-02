package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.common.ResultCode;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //必须添加该注解，将业务类交给Spring容器管理
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        //1.校验用户是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User existUser = userMapper.selectOne(queryWrapper);
        
        if (existUser != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }
        
        //2.存入数据库
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);
        
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        //1.校验用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);
        
        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        
        //2.校验密码是否正确
        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        
        //3.生成模拟的Token
        String token = "Bearer " + System.currentTimeMillis() + "_" + userDTO.getUsername();
        return Result.success(token);
    }
}