package com.bjqf.test;
import java.util.List;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.mapper.RoleMapper;
import com.bjqf.service.UserService;
import com.bjqf.util.JDBCUtil;

public class Test {
	public static void main(String[] args) {
		//创建Service层对象
		UserService userService = new UserService();
		//调用方法
		try {
			List<User> list = userService.login("jackma", "mayunalibaba");
			System.out.println(list.get(0).getRolename());
		} catch (UserException e) {
			//在控制台打印自定义异常信息
			System.out.println(e.getMessage());
		}
	}
}
