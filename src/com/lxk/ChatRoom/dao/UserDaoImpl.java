package com.lxk.ChatRoom.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lxk.ChatRoom.bean.User;
import com.lxk.ChatRoom.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "select*from user where usernum=? and password=?";
		User existUser;
		try {
			existUser = queryRunner.query(sql, new BeanHandler<User>(User.class),user.getUsernum(),user.getPassword());
			System.out.println("UserDaoImpl UexistUser:"+existUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("µ«¬º ß∞‹");
		}
		
		return existUser;
	}

	@Override
	public int regist(User user) {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "INSERT INTO USER VALUE(NULL,?,?,?,?,?,?)";
		Object[] params = {user.getUsernum(),user.getPassword(),user.getRealname(),user.getAge(),user.getSex(),user.getType()};
		int res;
		try {
//			res = queryRunner.batch(sql, params).length;
			res = queryRunner.update(sql, params);
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("≤Â»Î ˝æ›ø‚¥ÌŒÛ£¨◊¢≤· ß∞‹");
		}
		return res;
	}

	@Override
	public boolean find(String id) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		User user = null;
		String sql = "select from user where userid=?";
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user==null?false:true;//¥Ê‘⁄‘Ú «true
	}

}
