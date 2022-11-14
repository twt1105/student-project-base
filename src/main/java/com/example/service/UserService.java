package com.example.service;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qinlun
 * @since 2022-11-10
 */
public interface UserService extends IService<User> {

    void addUser(UserDto userDto);

    void deleteUserByName(String name);

    void updateUserByName(UserDto userDto);
}
