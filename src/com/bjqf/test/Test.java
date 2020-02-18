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
		//����Service�����
		UserService userService = new UserService();
		//���÷���
		try {
			List<User> list = userService.login("jackma", "mayunalibaba");
			System.out.println(list.get(0).getRolename());
		} catch (UserException e) {
			//�ڿ���̨��ӡ�Զ����쳣��Ϣ
			System.out.println(e.getMessage());
		}
	}
}
