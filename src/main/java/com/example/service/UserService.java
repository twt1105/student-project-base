package com.example.service;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qinlun
 * @since 2022-11-10
 */
public interface UserService extends IService<User> {

    /**
     * 新增用户信息
     * @param userDto
     */
    void addUser(UserDto userDto);

    /**
     * 删除用户信息
     * @param name
     */
    void deleteUserByName(String name);

    /**
     * 根据名称修改用户信息
     * @param userDto
     */
    void updateUserByName(UserDto userDto);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(int id);

    List<User> getUserByNameAndAge(String name, Integer age);
}
