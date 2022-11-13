package com.example.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "users")
@Component
public class User {

    private String name;

    private int age;

    private Date birth;

    private Map<Object,Object> maps;

    private List<String> lists;

    private Dog dog;
}
