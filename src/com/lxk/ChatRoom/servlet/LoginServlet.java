package com.lxk.ChatRoom.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxk.ChatRoom.bean.User;
import com.lxk.ChatRoom.service.UserService;
import com.lxk.ChatRoom.utils.BaseServlet;

public class LoginServlet extends BaseServlet {

	/**
	 * 登录的功能
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp){
		// 接收数据
//		Map<String, String[]> map = req.getParameterMap();
		String um = req.getParameter("usernum");
		String pw = req.getParameter("password");
		User user = new User(um,pw);
		// 封装数据
		try {
//			BeanUtils.populate(user, map);
			// 调用Service层处理数据 
			UserService us = new UserService();
			User existUser = us.login(user);
			System.out.println("LoginServlet.login()"+ existUser);
			if (existUser == null) {
				// 用户登录失败
				req.setAttribute("msg", "用户名或密码错误!");
				return "/index.jsp";
			} else {
				// 用户登录成功
				// 第一个BUG的解决:第二个用户登录后将之前的session销毁!
				req.getSession().invalidate();
				
				// 第二个BUG的解决:判断用户是否已经在Map集合中,存在：已经在列表中.销毁其session.
				// 获得到ServletCOntext中存的Map集合.
				Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext()
						.getAttribute("userMap");
				// 判断用户是否已经在map集合中'
//				if(!userMap.containsKey(existUser)){
//					// 说用map中有这个用户.
//					HttpSession session = userMap.get(existUser);
//					// 将这个session销毁.
//					session.invalidate();
//				}
//				
				// 使用监听器:HttpSessionBandingListener作用在JavaBean上的监听器.
				req.getSession().setAttribute("existUser", existUser);
				ServletContext application = getServletContext();

				String sourceMessage = "";

				if (null != application.getAttribute("message")) {
					sourceMessage = application.getAttribute("message")
							.toString();
				}

				sourceMessage += "系统公告：<font color='gray'>"
						+ existUser.getRealname() + "走进了聊天室！</font><br>";
				application.setAttribute("message", sourceMessage);

				resp.sendRedirect(req.getContextPath() + "/main.jsp");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.login(request, response);
	}
}
