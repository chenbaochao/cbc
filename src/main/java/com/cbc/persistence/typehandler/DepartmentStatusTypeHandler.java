/*
 * Copyright (c) 2017. Shanghai Zhenhui Information Technology Co,. ltd.
 * All rights are reserved.
 */

package com.cbc.persistence.typehandler;

import com.cbc.security.DepartmentStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by markfredchen on 11/07/2017.
 */
public class DepartmentStatusTypeHandler extends BaseTypeHandler<DepartmentStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DepartmentStatus departmentStatus, JdbcType jdbcType) throws SQLException {
        if (departmentStatus != null) {
            ps.setInt(i, departmentStatus.getId());
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public DepartmentStatus getNullableResult(ResultSet rs, String s) throws SQLException {
        return DepartmentStatus.parse(rs.getInt(s));
    }

    @Override
    public DepartmentStatus getNullableResult(ResultSet rs, int i) throws SQLException {
        return DepartmentStatus.parse(rs.getInt(i));
    }

    @Override
    public DepartmentStatus getNullableResult(CallableStatement cs, int i) throws SQLException {
        return DepartmentStatus.parse(cs.getInt(i));

    }

}
