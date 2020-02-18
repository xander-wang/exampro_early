package com.bjqf.service;
import java.util.List;
import com.bjqf.dao.UserDao;
import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.util.PageModel;
import sun.jvm.hotspot.debugger.Page;

public class UserService {
	UserDao userDao = new UserDao();

	public List<User> login(String username,String userpwd) throws UserException{
		List<User> list = userDao.login(username, userpwd);
		if(list.size() == 0){
			throw new UserException("user not exist");
		}else if(!list.get(0).isRolestate()){
			throw new UserException("role disabled");
		}else{
			return list;
		}
	}

	public List<User> selectAll() {
		return userDao.selectAll();
	}

	public PageModel queryByPage(int pageNo, int pageSize) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);

		int count = userDao.queryTotalNumber();
		pageModel.setCount(count);

		List<User> list = userDao.queryByPage(pageNo, pageSize);
		pageModel.setDataList(list);
		return pageModel;
	}

	public List<Role> queryRole() {
		return userDao.queryRole();
	}

	public void addUser(User user) throws UserException {
		int num = userDao.addUser(user);
		if(num == 0){
			throw new UserException("用户名已存在。");
		}
	}

	public List<User> selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

}
