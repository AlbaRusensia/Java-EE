package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import exception.ServiceException;
import model.UserAccount;
import service.UserService;
import service.UserServiceImpl;
import util.Checker;
import util.SwitcherUtil;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SwitcherUtil.relay(request, response, "loginView.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserAccount user = null;

		singIn(request, user);
		

		request.getRequestDispatcher("loginView.jsp").forward(request, response);

	}

	private void singIn(HttpServletRequest request, UserAccount user) {
		
		}
	}

