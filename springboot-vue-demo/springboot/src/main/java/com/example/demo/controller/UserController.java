package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * User控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping
    public  Result<?> save(@RequestBody User user){
        if(user.getPassword() == null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }
    @PutMapping
    public  Result<?> update(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public  Result<?> delete(@PathVariable Long id){
        userMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping
    public  Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickName,search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(userPage);
    }

}