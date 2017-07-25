package com.cbc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cbc.domain.Department;
import com.cbc.domain.User;
import com.cbc.persistence.DepartmentMapper;
import com.cbc.persistence.UserMapper;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cbc on 2017/7/20.
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User>{
    private final UserMapper userMapper;

    private final DepartmentMapper departmentMapper;

    public UserService(UserMapper userMapper, DepartmentMapper departmentMapper) {
        this.userMapper = userMapper;
        this.departmentMapper = departmentMapper;
    }

    public List<User> getUsersByDepartmentId(Long DepartmentId) {
        List<User> users = userMapper.selectList(
                new EntityWrapper<User>().eq("departementId", DepartmentId)
        );
        return users;
    }

    public Department getDepartmentByUser(User user) {
        Department department = departmentMapper.selectById(user.getDepartementId());
        return department;
    }

    @Transactional
    public User saveUser(User user) {
        Integer i = userMapper.insert(user);
        return user;
    }
}
