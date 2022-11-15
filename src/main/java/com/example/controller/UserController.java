package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.UserDto;
import com.example.dto.UserPageDto;
import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author qinlun
 * @since 2022-11-10
 */
@Api(value = "UserController", tags = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户信息
     *
     * @param userDto
     */
    @PostMapping("/addUser")
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    /**
     * 删除用户信息
     *
     * @param name
     */
    @GetMapping("/deleteUserByName")
    public void deleteUserByName(@RequestParam String name) {
        userService.deleteUserByName(name);
    }

    /**
     * 根据名称修改用户信息
     *
     * @param userDto
     */
    @PostMapping("/updateUserByName")
    public void updateUserByName(@RequestBody UserDto userDto) {
        userService.updateUserByName(userDto);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public User getUserById(@RequestParam int id) {
        return userService.getUserById(id);
    }


    /**
     * 根据名称和年龄列表查询
     *
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/getUserByNameAndAge")
    public List<User> getUserByNameAndAge(@RequestParam String name, @RequestParam Integer age) {
        return userService.getUserByNameAndAge(name, age);
    }

    @GetMapping("/getUserBySQL")
    public List<UserDto> getUserBySQL(@RequestParam String name) {
        return userService.getUserBySQL(name);
    }


    /**
     * 根据名称和年龄分页查询
     *
     * @param userPageDto
     * @return
     */
    @ApiOperation(value = "根据名称和年龄分页查询")
    @PostMapping("/getUserByPage")
    public Page<User> getUserByPage(@RequestBody UserPageDto userPageDto) {
        return userService.getUserByPage(userPageDto);
    }

}

