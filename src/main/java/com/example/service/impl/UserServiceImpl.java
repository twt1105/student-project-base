package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qinlun
 * @since 2022-11-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setSex("男");
        this.save(user);
        userMapper.insert(user);
        User user2 = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        saveBatch(users);
    }

    /**
     * 根据名称删除用户信息
     *
     * @param name
     */
    @Override
    public void deleteUserByName(String name) {
        //1：使用userQueryWrapper.lambda()
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getName, name);
        //2：直接输入字段名称
        userQueryWrapper.eq("name", name);
        //3：使用LambdaQueryWrapper
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, name);
        remove(userQueryWrapper);
    }

    @Override
    public void updateUserByName(UserDto userDto) {
        //根据LambdaQueryWrapper修改用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, userDto.getName());

        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setSex(userDto.getSex());

        update(user, lambdaQueryWrapper);

//        //根据LambdaUpdateWrapper修改用户信息
//        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(User::getName,userDto.getName())
//                .set(User::getName,userDto.getName())
//                .set(User::getAge,userDto.getAge())
//                .set(User::getSex,userDto.getSex());
//        update(updateWrapper);
//
//        //3：根据id修改用户信息
//        user.setId(9);
//        updateById(user);

    }
}
