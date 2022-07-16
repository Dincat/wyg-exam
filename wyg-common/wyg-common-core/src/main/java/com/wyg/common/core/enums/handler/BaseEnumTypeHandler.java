package com.wyg.common.core.enums.handler;

import com.wyg.common.core.enums.BaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }


    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        BaseEnum[] baseEnumArr = this.type.getEnumConstants();
        E baseEnum = valueOfString(baseEnumArr, s);
        return baseEnum;
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        BaseEnum[] baseEnumArr = this.type.getEnumConstants();
        E baseEnum = valueOfString(baseEnumArr, s);
        return baseEnum;
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        BaseEnum[] baseEnumArr = this.type.getEnumConstants();
        E baseEnum = valueOfString(baseEnumArr, s);
        return baseEnum;
    }

    @SuppressWarnings("unchecked")
    private E valueOfString(BaseEnum[] baseEnumArr, String value) {
        if (baseEnumArr == null || StringUtils.isBlank(value)) {
            return null;
        }

        for (BaseEnum baseEnum : baseEnumArr) {
            if (value.equalsIgnoreCase(baseEnum.getValue()+"")) {
                return (E) baseEnum;
            }
        }

        return null;
    }

}