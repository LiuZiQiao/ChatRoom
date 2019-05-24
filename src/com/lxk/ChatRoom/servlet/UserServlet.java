package com.lxk.ChatRoom.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
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
	
	
	/**
	 * 发送聊天内容
	 * @throws IOException 
	 */
	public String sendMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.接收数据 。
		System.out.println("sendMessage invoke....");
		String from = req.getParameter("from"); // 发言人
		String face = req.getParameter("face"); // 表情
		String to = req.getParameter("to"); // 接收者
		String color = req.getParameter("color"); // 字体颜色
		String content = req.getParameter("content"); // 发言内容
		// 发言时间 正常情况下使用SimpleDateFormat
		String sendTime = new Date().toLocaleString(); // 发言时间
		// 获得ServletContext对象.
		ServletContext application = getServletContext();
		//  从ServletContext中获取消息
		String sourceMessage = (String) application.getAttribute("message");
		// 拼接发言的内容:xx 对 yy 说 xxx
		sourceMessage += "<font color='blue'><strong>" + from
				+ "</strong></font><font color='#CC0000'>" + face
				+ "</font>对<font color='green'>[" + to + "]</font>说："
				+ "<font color='" + color + "'>" + content + "</font>（"
				+ sendTime + "）<br>";
		// 将消息存入到application的范围
		application.setAttribute("message", sourceMessage);
		return getMessage(req, resp);
	}
}
