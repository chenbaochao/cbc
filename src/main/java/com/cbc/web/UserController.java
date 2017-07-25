package com.cbc.web;

import com.cbc.domain.Department;
import com.cbc.domain.User;
import com.cbc.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cbc on 2017/7/20.
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(" /api/users/search/by/department/{departmentId}")
    public List<User> getUsersByDepartmentId(@PathVariable Long departmentId){
        List<User> result = userService.getUsersByDepartmentId(departmentId);
        return result;
    }

    @GetMapping("/cbc/getDepartmentByUser")
    public Department getDepartmentByUser(@RequestBody User user){
        Department department = userService.getDepartmentByUser(user);
        return department;
    }

    @PostMapping("/cbc/saveUser")
    public User saveUser(@RequestBody @Valid User user){
        User result = userService.saveUser(user);
        return result;
    }

}
