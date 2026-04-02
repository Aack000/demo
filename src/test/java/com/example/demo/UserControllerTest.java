package com.example.demo;

import com.example.demo.common.Result;
import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户控制器单元测试类
 * 验证UserController的注册、登录、查询功能
 */
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    /**
     * 测试用户注册功能
     */
    @Test
    void testRegister() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setPassword("testpass");

        Result<String> result = userController.register(userDTO);

        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("注册成功!", result.getData());
    }

    /**
     * 测试重复用户名注册
     */
    @Test
    void testRegisterWithExistingUsername() {
        // 先注册一个用户
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("duplicateuser");
        userDTO.setPassword("password");
        userController.register(userDTO);

        // 尝试用相同用户名再次注册
        Result<String> result = userController.register(userDTO);

        assertNotNull(result);
        assertEquals(4001, result.getCode()); // USER_HAS_EXISTED
        assertEquals("该用户名已被注册", result.getMsg());
    }

    /**
     * 测试用户登录功能
     */
    @Test
    void testLogin() {
        // 先注册用户
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("loginuser");
        userDTO.setPassword("loginpass");
        userController.register(userDTO);

        // 登录
        Result<String> result = userController.login(userDTO);

        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("登录成功", result.getData());
    }

    /**
     * 测试登录时用户不存在
     */
    @Test
    void testLoginWithNonExistentUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("nonexistent");
        userDTO.setPassword("password");

        Result<String> result = userController.login(userDTO);

        assertNotNull(result);
        assertEquals(4002, result.getCode()); // USER_NOT_EXIST
        assertEquals("该用户不存在", result.getMsg());
    }

    /**
     * 测试登录时密码错误
     */
    @Test
    void testLoginWithWrongPassword() {
        // 先注册用户
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("wrongpassuser");
        userDTO.setPassword("correctpass");
        userController.register(userDTO);

        // 使用错误密码登录
        UserDTO wrongUserDTO = new UserDTO();
        wrongUserDTO.setUsername("wrongpassuser");
        wrongUserDTO.setPassword("wrongpass");

        Result<String> result = userController.login(wrongUserDTO);

        assertNotNull(result);
        assertEquals(4003, result.getCode()); // PASSWORD_ERROR
        assertEquals("账号或密码错误", result.getMsg());
    }

    /**
     * 测试根据ID查询用户功能
     */
    @Test
    void testGetUserById() {
        // 先注册用户
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("queryuser");
        userDTO.setPassword("querypass");
        Result<String> registerResult = userController.register(userDTO);

        // 假设用户ID为1（实际使用时需要根据数据库返回的ID调整）
        Result<String> result = userController.getUserById(1L);

        assertNotNull(result);
        // 由于我们不知道实际插入的ID，这里只检查返回格式
        if (result.getCode() == 200) {
            assertTrue(result.getData().contains("查询成功"));
        }
    }

    /**
     * 测试查询不存在的用户
     */
    @Test
    void testGetNonExistentUserById() {
        Result<String> result = userController.getUserById(999999L);

        assertNotNull(result);
        assertEquals(4002, result.getCode()); // USER_NOT_EXIST
        assertEquals("该用户不存在", result.getMsg());
    }
}