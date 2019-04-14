package com.lxk.ChatRoom.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxk.ChatRoom.bean.User;
import com.lxk.ChatRoom.utils.BaseServlet;

/**
 * @author xiaokun·liu
 *
 */
public class UserServlet extends BaseServlet {
	
	/**
	 * 校验功能
	 */
	public String check(HttpServletRequest request,HttpServletResponse response) throws IOException {
		User existUser = (User) request.getSession().getAttribute("existUser");
		if(existUser == null){
			//登录信息过期
			response.getWriter().println("1");
		}else {
			response.getWriter().println("2");
		}
		return null;
	}

	public String exit(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return null;
	}
	/**
	 * 踢人功能
	 */
	public String kick(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		Map<User,HttpSession> userMap = (Map<User, HttpSession>) getServletContext().getAttribute("existUser");
		
		User user = new User();
		user.setId(id);
		
		HttpSession session = userMap.get(user);
		
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return null;
	}
	
	public String getMessage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String message = (String) getServletContext().getAttribute("message");
		if(message != null){
			response.getWriter().println(message);
		}
		return null;
	}
}
