package com.bjqf.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMapper implements RowMapper {

    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        return rs.getInt("count");
    }
}
