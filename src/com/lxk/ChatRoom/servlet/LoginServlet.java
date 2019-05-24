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
	 * ��¼�Ĺ���
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp){
		// ��������
//		Map<String, String[]> map = req.getParameterMap();
		String um = req.getParameter("usernum");
		String pw = req.getParameter("password");
		User user = new User(um,pw);
		// ��װ����
		try {
//			BeanUtils.populate(user, map);
			// ����Service�㴦������ 
			UserService us = new UserService();
			User existUser = us.login(user);
			System.out.println("LoginServlet.login()"+ existUser);
			if (existUser == null) {
				// �û���¼ʧ��
				req.setAttribute("msg", "�û������������!");
				return "/index.jsp";
			} else {
				// �û���¼�ɹ�
				// ��һ��BUG�Ľ��:�ڶ����û���¼��֮ǰ��session����!
				req.getSession().invalidate();
				
				// �ڶ���BUG�Ľ��:�ж��û��Ƿ��Ѿ���Map������,���ڣ��Ѿ����б���.������session.
				// ��õ�ServletCOntext�д��Map����.
				Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext()
						.getAttribute("userMap");
				// �ж��û��Ƿ��Ѿ���map������'
//				if(!userMap.containsKey(existUser)){
//					// ˵��map��������û�.
//					HttpSession session = userMap.get(existUser);
//					// �����session����.
//					session.invalidate();
//				}
//				
				// ʹ�ü�����:HttpSessionBandingListener������JavaBean�ϵļ�����.
				req.getSession().setAttribute("existUser", existUser);
				ServletContext application = getServletContext();

				String sourceMessage = "";

				if (null != application.getAttribute("message")) {
					sourceMessage = application.getAttribute("message")
							.toString();
				}

				sourceMessage += "ϵͳ���棺<font color='gray'>"
						+ existUser.getRealname() + "�߽��������ң�</font><br>";
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
