package com.cbc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cbc.domain.Department;
import com.cbc.exception.core.ValidationError;
import com.cbc.exception.core.ValidationException;
import com.cbc.persistence.DepartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by cbc on 2017/7/20.
 */
@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department> {

    private final DepartmentMapper departmentMapper;


    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public Department getDepartmentById(Long id) {
        Department department = departmentMapper.selectById(id);
        return department;
    }

    @Transactional
    public Integer saveDepartment(Department department) {
 /*       if(department.getId()!=null){
            throw new ValidationException(new ValidationError("id","department_exist"));
        }*/
       /* if(departments==null||departments.size()==0){
            throw new ValidationException(new ValidationError("name","department_name_already_exist"));
        }*/
        department.setCreatedDate(ZonedDateTime.now());
        Integer i = departmentMapper.insert(department);
        return i;
    }

    @Transactional
    public Department updateDepartment(Department department) {
        if (department.getId() == null) {
            throw new ValidationException(new ValidationError("id", "should.be.Notempty"));
        }
        Integer i = departmentMapper.updateById(department);
        return department;
    }

    @Transactional
    public Integer deleteDepartment(Long id) {
        Integer i = departmentMapper.deleteById(id);
        return i;
    }

    public List<Department> getDepartments(Page<Department> page) {
        List<Department> departments = departmentMapper.selectPage(
                page,
                new EntityWrapper<Department>()
        );
        return departments;
    }


    public List<Department> getDepartmentByName(String name) {
        List<Department> departments = departmentMapper.selectList(
                new EntityWrapper<Department>().eq("name", name)
        );
        return departments;
    }
}
