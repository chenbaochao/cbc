/*
 * Copyright (c) 2017. Shanghai Zhenhui Information Technology Co,. ltd.
 * All rights are reserved.
 */

package com.cbc.security;

import com.cbc.domain.SysEnum;
import org.springframework.util.Assert;

/**
 * Created by markfredchen on 11/07/2017.
 */
public enum DepartmentStatus implements SysEnum {
    ENABLED(0), DISABLED (1);

    private int id;

    DepartmentStatus(int id) {
        this.id = id;
    }

    public static DepartmentStatus parse(int id) {
        DepartmentStatus departmentStatus = null;
        for (DepartmentStatus status : DepartmentStatus.values()) {
            if (status.getId() == id) {
                departmentStatus = status;
            }
        }
        Assert.notNull(departmentStatus, "DepartmentStatus[id=" + id + "] not found");
        return departmentStatus;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
