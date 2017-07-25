package com.cbc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cbc.domain.Department;
import com.cbc.persistence.DepartmentMapper;
import com.cbc.security.DepartmentStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by cbc on 2017/7/25.
 */
public class DepartmentServiceTest {

    public static final String DATE = "2017-01-01T10:00:00+08:00[Asia/Shanghai]";

    private DepartmentMapper departmentMapper;
    @Before
    public void setUp() throws Exception {
        departmentMapper = PowerMockito.mock(DepartmentMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.verifyNoMoreInteractions(departmentMapper);

    }

    @Test
    public void saveDepartment() throws Exception {

        Department department = Department.builder().id(1L)
                .name("汉得")
                .code("hand")
                .status(DepartmentStatus.DISABLED)
                .createdDate(ZonedDateTime.parse(DATE))
                .build();
        ArgumentCaptor<Department> argumentCaptor = ArgumentCaptor.forClass(Department.class);
        PowerMockito.when(departmentMapper.insert(argumentCaptor.capture())).thenReturn(1);

        Integer i = new DepartmentService(departmentMapper).saveDepartment(department);
            assertThat(argumentCaptor.getValue().getName()).isEqualTo("汉得");
            assertThat(i).isEqualTo(1);
        Mockito.verify(departmentMapper,Mockito.times(1)).insert(argumentCaptor.capture());

    }

    @Test
    public void getDepartments() throws Exception {
        ArgumentCaptor<Page> pageArgumentCaptor = ArgumentCaptor.forClass(Page.class);
        PowerMockito.when(departmentMapper.selectPage(
               pageArgumentCaptor.capture(),new EntityWrapper<Department>()
               ));
        List<Department> departments = new DepartmentService(departmentMapper).getDepartments(new Page<Department>(0,10));
        assertThat(pageArgumentCaptor.getValue().getSize()).isEqualTo(1);
        assertThat(departments.get(0).getName()).isEqualTo("qqq111dddd1111");
        Mockito.verify(departmentMapper,Mockito.times(1)).selectPage(pageArgumentCaptor.capture(),null);

    }

}