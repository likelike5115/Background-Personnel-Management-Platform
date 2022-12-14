package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;

/**
 * User的持久层
 */
@Controller
//继承BaseMapper接口
public interface UserMapper extends BaseMapper<User> {

}