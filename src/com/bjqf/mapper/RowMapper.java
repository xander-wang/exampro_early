package com.bjqf.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	public abstract Object rowMapper(ResultSet rs) throws SQLException;
}
