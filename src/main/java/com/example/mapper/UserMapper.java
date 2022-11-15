package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qinlun
 * @since 2022-11-10
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页获取未推送的教师工号
     */
    @Select("select t1.teacher_code from teacher_report_index t1 left join \n" +
            "teacher_report_push_log t2 on t1.teacher_code = t2.teacher_code \n" +
            "where t2.push_type = 0 and ( t2.push_result != 1 or t2.push_result is null ) \n" +
            "limit #{start},#{limit}")
    List<String> getNotPushTeacherReportList(@Param("start") int start, @Param("limit") int limit);

    @Select("select * from user where name = #{name} and age > #{age}")
    List<User> getUserListByNameAndAge(@Param("name") String name, @Param("age") int age);

    @Select("select name from user where id = #{id}")
    String getNameById(@Param("id") int id);

    @Select("select name,age,sex from user where name LIKE CONCAT('%',#{name},'%')")
    List<UserDto> getUserDto(@Param("name") String name);
}
