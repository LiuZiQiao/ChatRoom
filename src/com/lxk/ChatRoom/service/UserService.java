package com.lxk.ChatRoom.service;

import com.lxk.ChatRoom.bean.User;
import com.lxk.ChatRoom.dao.UserDao;
import com.lxk.ChatRoom.dao.UserDaoImpl;

public class UserService {
	
	UserDao ud = new UserDaoImpl();
	public int regist(User user){
		return ud.regist(user);
	}
	
	public User login(User user) {
		return ud.login(user);
	}
	
	public boolean find(String id) {
		// TODO Auto-generated method stub
		return ud.find(id);
	}
}
