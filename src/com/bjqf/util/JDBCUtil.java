package com.bjqf.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjqf.mapper.RowMapper;

public class JDBCUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.31.156:3306/exampro?useSSL=false";
	private static final String USER="root";
	private static final String PWD = "123456";

	public static int executeUpdate(String sql,Object...params){
		int num = 0;
		try {

			Class.forName(DRIVER);

			Connection conn = DriverManager.getConnection(URL,USER,PWD);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);

			num = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public static List executeQuery(String sql, RowMapper rowMapper, Object...params){
		List list = new ArrayList();
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				Object obj = rowMapper.rowMapper(rs);

				list.add(obj);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void setParams(PreparedStatement pstmt,Object...params) throws SQLException{
		if(params != null){
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
		}
	}
}
