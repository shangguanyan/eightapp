package com.eight.eightapp.dao;

import com.eight.eightapp.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

	List<User> getByMap(Map<String, Object> map);
	User getById(Integer id);
	Integer create(User user);
	int update(User user);
	int delete(Integer id);
	User getByUserName(String userName);
    void changePassword(@Param("userId") String userId, @Param("password") String password);
}