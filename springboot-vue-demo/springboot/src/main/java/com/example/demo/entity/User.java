package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 */
@TableName("user")//与数据库表明对应
@Data//lombok 的Data注解,不用写get/set方法
public class User {
    @TableId(type = IdType.AUTO)//主键id自动生成
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String gender;
    private String address;
}