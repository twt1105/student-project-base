package com.example.dto;

import lombok.Data;

@Data
public class UserDto {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;
}
