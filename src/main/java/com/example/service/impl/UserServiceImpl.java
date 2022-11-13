package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
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
//        userMapper.insert(user);
//        User user2 = new User();
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user2);
//        saveBatch(users);
    }

    /**
     * 根据名称删除用户信息
     * @param name
     */
    @Override
    public void deleteUserByName(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getName,name);
//        userQueryWrapper.eq("name",name);
        remove(userQueryWrapper);
    }
}
