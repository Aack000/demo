package com.example.demo;

import com.example.demo.common.Result;
import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户控制器单元测试类
 * 验证UserController的CRUD功能
 */
@SpringBootTest
class UserControllerTest {
    
    private UserController userController;
    
    @BeforeEach
    void setUp() {
        userController = new UserController();
    }
    
    /**
     * 测试创建用户功能
     */
    @Test
    void testCreateUser() {
        User user = new User();
        user.setName("测试用户");
        user.setAge(25);
        
        Result<User> result = userController.createUser(user);
        
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertTrue(result.getMsg().contains("创建用户成功"));
        assertNotNull(result.getData());
        assertNotNull(result.getData().getId());
        assertEquals("测试用户", result.getData().getName());
        assertEquals(25, result.getData().getAge());
    }
    
    /**
     * 测试查询用户功能
     */
    @Test
    void testGetUser() {
        // 先创建用户
        User user = new User();
        user.setName("查询测试用户");
        user.setAge(30);
        Result<User> createResult = userController.createUser(user);
        Long userId = createResult.getData().getId();
        
        // 查询用户
        Result<User> result = userController.getUser(userId);
        
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertTrue(result.getMsg().contains("查询用户成功"));
        assertNotNull(result.getData());
        assertEquals(userId, result.getData().getId());
        assertEquals("查询测试用户", result.getData().getName());
        assertEquals(30, result.getData().getAge());
    }
    
    /**
     * 测试查询不存在的用户
     */
    @Test
    void testGetNonExistentUser() {
        Result<User> result = userController.getUser(999999L);
        
        assertNotNull(result);
        assertEquals(404, result.getCode());
        assertTrue(result.getMsg().contains("用户不存在"));
        assertNull(result.getData());
    }
    
    /**
     * 测试更新用户功能
     */
    @Test
    void testUpdateUser() {
        // 先创建用户
        User user = new User();
        user.setName("更新前用户");
        user.setAge(25);
        Result<User> createResult = userController.createUser(user);
        Long userId = createResult.getData().getId();
        
        // 更新用户
        User updatedUser = new User();
        updatedUser.setName("更新后用户");
        updatedUser.setAge(30);
        
        Result<User> result = userController.updateUser(userId, updatedUser);
        
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertTrue(result.getMsg().contains("更新用户成功"));
        assertNotNull(result.getData());
        assertEquals(userId, result.getData().getId());
        assertEquals("更新后用户", result.getData().getName());
        assertEquals(30, result.getData().getAge());
    }
    
    /**
     * 测试删除用户功能
     */
    @Test
    void testDeleteUser() {
        // 先创建用户
        User user = new User();
        user.setName("删除测试用户");
        user.setAge(25);
        Result<User> createResult = userController.createUser(user);
        Long userId = createResult.getData().getId();
        
        // 删除用户
        Result<String> result = userController.deleteUser(userId);
        
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertTrue(result.getMsg().contains("删除用户成功"));
        assertNull(result.getData());
        
        // 验证用户已被删除
        Result<User> getResult = userController.getUser(userId);
        assertEquals(404, getResult.getCode());
    }
    
    /**
     * 测试获取所有用户列表功能
     */
    @Test
    void testGetAllUsers() {
        // 先创建几个用户
        User user1 = new User();
        user1.setName("用户1");
        user1.setAge(25);
        userController.createUser(user1);
        
        User user2 = new User();
        user2.setName("用户2");
        user2.setAge(30);
        userController.createUser(user2);
        
        // 获取所有用户
        var result = userController.getAllUsers();
        
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertTrue(result.getMsg().contains("获取用户列表成功"));
        assertNotNull(result.getData());
        assertTrue(result.getData().size() >= 2);
    }
}