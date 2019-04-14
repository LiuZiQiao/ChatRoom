package com.lxk.ChatRoom.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lxk.ChatRoom.bean.User;
import com.lxk.ChatRoom.service.UserService;

public class LoginServlet extends HttpServlet {

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
			try {
				BeanUtils.populate(user, map);	//Êý¾Ý·â×°
				UserService us = new UserService();
				User existUser = us.login(user);
				if(existUser ==null){
					request.setAttribute("msg", "ÕËºÅ»òÃÜÂë´íÎó");
					System.out.println(existUser);
					return request.getContextPath()+"/index.jsp";
				}else{
					request.getSession().setAttribute("existUser", existUser);
					response.sendRedirect(request.getContextPath()+"/main.jsp");
					return null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.login(request, response);
	}

}
