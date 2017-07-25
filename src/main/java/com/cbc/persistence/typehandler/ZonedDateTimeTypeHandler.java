package com.cbc.persistence.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by cbc on 2017/7/21.
 */
@MappedTypes(ZonedDateTime.class)
public class ZonedDateTimeTypeHandler extends BaseTypeHandler<ZonedDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime zonedDateTime, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(
                i,
                Timestamp.from(zonedDateTime.toInstant()),
                GregorianCalendar.from(zonedDateTime)
        );
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, String s) throws SQLException {
        Timestamp ts = rs.getTimestamp(s, Calendar.getInstance());
        if (ts != null) {
            return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, int i) throws SQLException {
        Timestamp ts = rs.getTimestamp(i, Calendar.getInstance());
        if(ts!=null){
            return ZonedDateTime.ofInstant(ts.toInstant(),ZoneId.systemDefault());
        }
        return null;
    }

    @Override
    public ZonedDateTime getNullableResult(CallableStatement cs, int i) throws SQLException {
        Timestamp ts = cs.getTimestamp(i, Calendar.getInstance());
        if(ts!=null){
            return ZonedDateTime.ofInstant(ts.toInstant(),ZoneId.systemDefault());
        }
        return null;
    }
}
