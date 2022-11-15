package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.UserDto;
import com.example.dto.UserPageDto;
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
        //1：根据LambdaQueryWrapper修改用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, userDto.getName());

        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setSex(userDto.getSex());

        update(user, lambdaQueryWrapper);

//        //2：根据LambdaUpdateWrapper修改用户信息
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

    @Override
    public User getUserById(int id) {
        return getById(id);
    }

    /**
     * 查询一个姓张的，年龄大于15
     *
     * @param name
     * @param age
     * @return
     */
    @Override
    public List<User> getUserByNameAndAge(String name, Integer age) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
//                .eq(User::getName,name)
//                .like(User::getName, name)
//                .likeLeft(User::getName, name)
                .likeRight(User::getName, name)
                .gt(User::getAge, age);
        //根据条件查询单条数据
//        User user = getOne(lambdaQueryWrapper);
        //根据条件查询多条数据
        List<User> list = list(lambdaQueryWrapper);
        //查询所有数据
//        List<User> list = list();
        return list;
    }

    @Override
    public List<UserDto> getUserBySQL(String name) {
        List<UserDto> users = userMapper.getUserDto(name);
        return users;
    }

    /**
     * 根据用户名称模糊查询，根据性别查询
     *
     * @param userPageDto
     * @return
     */
    @Override
    public Page<User> getUserByPage(UserPageDto userPageDto) {
        Page<User> page = new Page<>();
        page.setCurrent(userPageDto.getCurrentPage());
        page.setSize(userPageDto.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isBlank(userPageDto.getName()), User::getName, userPageDto.getName())
                .eq(!StringUtils.isBlank(userPageDto.getSex()), User::getSex, userPageDto.getSex());
        Page<User> pageUser = page(page, lambdaQueryWrapper);
        return pageUser;
    }
}
