package com.bjqf.dao;

import java.util.List;

import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.CountMapper;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.mapper.UserMapper;
import com.bjqf.util.JDBCUtil;
import jdk.nashorn.internal.scripts.JD;

public class UserDao {
	public List<User> login(String username, String userpwd){
		String sql = "select a.*,b.rolestate,b.rolename from user a "
				+ "inner join role b "
				+ "on a.roleid = b.roleid "
				+ "where username = ? and userpwd = ?";
		return (List<User>) JDBCUtil.executeQuery(sql, new UserMapper(), username,userpwd);
	}

	public List<User> selectAll() {
		String sql = "select * from user";
		return (List<User>) JDBCUtil.executeQuery(sql, new UserMapper(), null);
	}

	public int queryTotalNumber() {
		String sql = "select count(*) as count from user";
		return Integer.parseInt(JDBCUtil.executeQuery(sql, new CountMapper(), null).get(0).toString());
	}

	public List<User> queryByPage(int pageNo, int pageSize) {
//		String sql = "select user.*, role.rolename, role.rolestate from user, role where user.roleid=role.roleid limit ?,?;";
		String sql = "select user.*, role.rolename, role.rolestate from user " + "inner join role " + "on user.roleid = role.roleid limit ?,?";
		return (List<User>) JDBCUtil.executeQuery(sql, new UserMapper(), (pageNo - 1) * pageSize, pageSize);
	}

	public List<Role> queryRole() {
		String sql = "select * from role";
		return (List<Role>) JDBCUtil.executeQuery(sql, new RoleMapper(), null);
	}

	public int addUser(User user) throws UserException {
//		String sql = null;
//		sql = "select * from user where username = ?";
//		List<User> list = JDBCUtil.executeQuery(sql, new UserMapper(), user.getUsername());
//
//		if(list.size() == 0){
//			sql = "insert into user (roleid, username, userpwd, usertruename) values (?, ?, ?, ?)";
//			JDBCUtil.executeUpdate(sql, user.getRoleid(), user.getUsername(), user.getUserpwd(), user.getUsertruename());
//		}else{
//			throw new UserException("用户名已存在。");
//		}
		String sql = "insert into user (roleid, username, userpwd, usertruename, userstate) values (?,?,?,?,?)";
		int num = JDBCUtil.executeUpdate(sql, user.getRoleid(), user.getUsername(), user.getUserpwd(), user.getUsertruename(), user.isRolestate());
		return num;
	}

	public List<User> selectByUsername(String username) {
//		String sql = "select * from ((select user.*, role.rolename, role.rolestate from user, role where user.roleid=role.roleid) as t) where username = ?";
//		return (List<User>) JDBCUtil.executeQuery(sql, new UserMapper(), username);
		String sql = "select user.*, role.rolename, role.rolestate from user " + "inner join role " + "on user.roleid = role.roleid where username = ?";
		return (List<User>) JDBCUtil.executeQuery(sql, new UserMapper(), username);
	}

	public void updateUser(User user) {
		String sql = "update user set roleid=?, userpwd=?, usertruename=?, userstate=? where username=?";
		JDBCUtil.executeUpdate(sql, user.getRoleid(), user.getUserpwd(), user.getUsertruename(), user.isUserstate(), user.getUsername());
	}
}