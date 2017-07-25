package com.cbc.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import com.cbc.security.DepartmentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by cbc on 2017/7/20.
 */
@Data
@Builder
@TableName("atl_Department")
public class Department{
    @TableId(type=IdType.AUTO)
    private Long id;

    @TableField(value = "department_name",strategy = FieldStrategy.NOT_NULL)
    private String name;

    @TableField(value = "code")
    private String code;

    @TableField(value="department_status",strategy=FieldStrategy.NOT_NULL)
    private DepartmentStatus status;

    @TableField(value="created_date",strategy=FieldStrategy.NOT_NULL)
    private ZonedDateTime createdDate;
}
