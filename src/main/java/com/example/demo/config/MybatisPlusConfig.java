package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {
    // 简化配置，移除分页插件，避免兼容性问题
}