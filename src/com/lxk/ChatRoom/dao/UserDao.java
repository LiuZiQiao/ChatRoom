package com.lxk.ChatRoom.dao;

import com.lxk.ChatRoom.bean.User;

public interface UserDao {
	public User login(User user);

	public int regist(User user);

	public boolean find(String id);
}
