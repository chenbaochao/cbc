package com.cbc.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.cbc.domain.Department;
import com.cbc.exception.core.ValidationError;
import com.cbc.exception.core.ValidationException;
import com.cbc.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cbc on 2017/7/20.
 */
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/api/department")
    public List<Department> getDepartments(@RequestParam(value = "page",defaultValue = "0")Integer page, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        List<Department> departments = this.departmentService.getDepartments(
                new Page<Department>(page,pageSize)
                );
        return departments;
    }

    @GetMapping("/api/departments/{departmentId}")
    public Department getDepartmentById(@PathVariable Long departmentId) {
        Department Department = departmentService.getDepartmentById(departmentId);
        return Department;
    }

    @GetMapping(" /api/departments/search/{keyword}")
    public List<Department> searchDepartment(@PathVariable String keyword) {
        List<Department> departments = departmentService.getDepartmentByName(keyword);
        return departments;
    }

    @PostMapping("/api/departments")
    public Integer createDepartment(@RequestBody @Valid Department Department) {
        Integer i = 0;
        try {
             i = departmentService.saveDepartment(Department);
        }catch (Exception e){
            throw new ValidationException(new ValidationError("id","department_exist"));
        }
        return i;
    }

    @PutMapping(" /api/departments")
    public Department updateDepartment(@RequestBody @Valid Department Department) {
        Department result = departmentService.updateDepartment(Department);
        return result;
    }

    @DeleteMapping("/api/departments/{departmentId}")
    public void deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

}
