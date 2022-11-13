package com.example.controller;


import com.example.dto.UserDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qinlun
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    @GetMapping("/deleteUserByName")
    public void deleteUserByName(@RequestParam String name){
        userService.deleteUserByName(name);
    }

}

