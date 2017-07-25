/*
 * Copyright (c) 2017. Shanghai Zhenhui Information Technology Co,. ltd.
 * All rights are reserved.
 */

package com.cbc.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by markfredchen on 06/06/2017.
 */
@Data
@TableName("atl_user")
@JsonPropertyOrder({
        "id",
        "departementId",
        "username",
        "password",
        "createdDate"
})
public class User {
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private Long id;
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String departementId;
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String username;
    @TableField(value = "password", strategy = FieldStrategy.NOT_NULL)
    private String password;

    private String fullName;
}
