package com.eight.eightapp.service;

import com.eight.eightapp.bean.User;
import com.eight.eightapp.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {
//	private static final String LOGIN_USER = UserService.class.getName()
//			+ ".loginUser";

    @Autowired
	private UserDao userDao;
	
	public List<User> getByMap(Map<String,Object> map) {
	    return userDao.getByMap(map);
	}
	
	public User getById(Integer id) {
		return userDao.getById(id);
	}
	
	public User create(User user) {
		userDao.create(user);
		return user;
	}
	
	public User update(User user) {
		userDao.update(user);
		return user;
	}

	public void changePassword(String userId, String password) {
		userDao.changePassword(userId, password);
	}

	public int delete(Integer id) {
		return userDao.delete(id);
	}

	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	public User getLoginUser(){
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return null;
		}

		Session session = subject.getSession(true);
		if (session == null) {
			return null;
		}

		User loginUser = (User) session.getAttribute("user");
		if (loginUser == null) {
			if (subject.getPrincipal() != null) {
				loginUser = getByUserName((String) subject.getPrincipal());
				if (loginUser != null) {
					session.setAttribute("user", loginUser);
				}
			}

		}

		return loginUser;
	}
}