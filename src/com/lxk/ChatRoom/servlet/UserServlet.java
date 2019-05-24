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
 * @author xiaokun��liu
 *
 */
public class UserServlet extends BaseServlet {
	
	/**
	 * У�鹦��
	 */
	public String check(HttpServletRequest request,HttpServletResponse response) throws IOException {
		User existUser = (User) request.getSession().getAttribute("existUser");
		if(existUser == null){
			//��¼��Ϣ����
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
	 * ���˹���
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
	 * ������������
	 * @throws IOException 
	 */
	public String sendMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.�������� ��
		System.out.println("sendMessage invoke....");
		String from = req.getParameter("from"); // ������
		String face = req.getParameter("face"); // ����
		String to = req.getParameter("to"); // ������
		String color = req.getParameter("color"); // ������ɫ
		String content = req.getParameter("content"); // ��������
		// ����ʱ�� ���������ʹ��SimpleDateFormat
		String sendTime = new Date().toLocaleString(); // ����ʱ��
		// ���ServletContext����.
		ServletContext application = getServletContext();
		//  ��ServletContext�л�ȡ��Ϣ
		String sourceMessage = (String) application.getAttribute("message");
		// ƴ�ӷ��Ե�����:xx �� yy ˵ xxx
		sourceMessage += "<font color='blue'><strong>" + from
				+ "</strong></font><font color='#CC0000'>" + face
				+ "</font>��<font color='green'>[" + to + "]</font>˵��"
				+ "<font color='" + color + "'>" + content + "</font>��"
				+ sendTime + "��<br>";
		// ����Ϣ���뵽application�ķ�Χ
		application.setAttribute("message", sourceMessage);
		return getMessage(req, resp);
	}
}
