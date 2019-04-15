package com.lxk.ChatRoom.servlet;

import java.io.IOException;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.lxk.ChatRoom.bean.User;
import com.lxk.ChatRoom.service.UserService;
import com.lxk.ChatRoom.utils.BaseServlet;

public class LoginServlet extends BaseServlet {

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
//		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		user.setUsernum(request.getParameter("usernum"));
		user.setPassword(request.getParameter("password"));
		System.out.println("servlet--user"+user);
			try {
//				BeanUtils.populate(user, map);	//数据封装
				UserService us = new UserService();
				User existUser = us.login(user);
				System.out.println(existUser);
				if(existUser == null){
					request.setAttribute("msg", "账号或密码错误");
					response.sendRedirect(request.getContextPath()+"/index.jsp");
//					return "/index.jsp";
				}else{
					System.out.println("zhengque");
					System.out.println(existUser);
//					request.getSession().invalidate();//如果用户第二次登录就将第一次登录的session销毁
//					//并且如果在在线用户列表中存在也需要销毁
//					Map<User,HttpSession> userMap = (Map<User,HttpSession>) getServletContext().getAttribute("userMap");
//					System.out.println("userMap:"+userMap);
//					if(userMap.containsKey(existUser)){
//						HttpSession session = userMap.get(existUser);
//						session.invalidate();
//					}
					
					//登录成功用监听器：HttpSessionBandingListener作用在Javabean上的监听器
					request.getSession().setAttribute("existUser", existUser);
					ServletContext application = getServletContext();
					
					String sourceMessage = null;
					if(null != application.getAttribute("message")){
						sourceMessage = application.getAttribute("message").toString();
					}
					sourceMessage += "系统公告：<font color='gray'>"+existUser.getRealname()+"走进了聊天室！</font><br/>";
					application.setAttribute("message", sourceMessage);
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
