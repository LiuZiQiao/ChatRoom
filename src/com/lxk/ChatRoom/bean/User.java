package com.lxk.ChatRoom.bean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{

	private String id;
	private String usernum;
	private String password;
	private String realname;
	private int age;
	private String sex;
	private String type;
	
	
	public User() {}
	public User(String usernum,String password)
	{
		this.usernum = usernum;
		this.password = password;
	}
	public User(String usernum, String password, String realname,
			int age, String sex, String type) {
		super();
		this.usernum = usernum;
		this.password = password;
		this.realname = realname;
		this.age = age;
		this.sex = sex;
		this.type = type;
	}
	
	
	
	public User(String id, String usernum, String password, String realname,
			int age, String sex, String type) {
		super();
		this.id = id;
		this.usernum = usernum;
		this.password = password;
		this.realname = realname;
		this.age = age;
		this.sex = sex;
		this.type = type;
	}

	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usernum=" + usernum + ", password="
				+ password + ", realname=" + realname + ", age=" + age
				+ ", sex=" + sex + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((realname == null) ? 0 : realname.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((usernum == null) ? 0 : usernum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (usernum == null) {
			if (other.usernum != null)
				return false;
		} else if (!usernum.equals(other.usernum))
			return false;
		return true;
	}
	
//	public void valueBound(HttpSessionBindingEvent event) {
//		HttpSession session = event.getSession();
//
//		@SuppressWarnings("unchecked")
//		Map<User, HttpSession> userMap = new HashMap<>();
//		userMap.put(this, session);
//		session.getServletContext().setAttribute("userMap", userMap);
//		System.out.println("User.valueBound():"+userMap);
//		
//	}

	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("进入了....");
		HttpSession session = event.getSession();

		@SuppressWarnings("unchecked")
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
				.getServletContext().getAttribute("userMap");
		userMap.put(this, session);
		System.out.println("User.valueBound():"+userMap);
	}
	
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(getRealname()+"退出了");
		HttpSession session = event.getSession();
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
		userMap.remove(this);
	}
}
