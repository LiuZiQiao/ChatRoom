package com.lxk.ChatRoom.bean;

public class User {

	private String username;
	private String password;
	private String realname;
	private int age;
	private String sex;
	private String type;
	
	
	public User() {}
	
	public User(String username, String password, String realname, int age,String sex, String type) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.age = age;
		this.sex = sex;
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", realname=" + realname + ", age=" + age + ", sex=" + sex
				+ ", type=" + type + "]";
	}

	
	
}
