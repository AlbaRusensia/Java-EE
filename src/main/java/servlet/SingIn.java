package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.ServiceException;
import service.UserService;
import service.UserServiceImpl;
import util.Checker;
import util.SwitcherUtil;

@SuppressWarnings("serial")
public class SingIn extends HttpServlet {
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SwitcherUtil.relay(req, resp, "singIn.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		try {
			if (!Checker.areNull(login, password) && userService.singIn(login, password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("login", login);
				session.setAttribute("password", password);
				SwitcherUtil.relay(request, response, "home.jsp");
				return;
			} else {
				SwitcherUtil.relay(request, response, "singIn.jsp");
				return;
			}
		} catch (ServiceException e) {
			System.err.println("SingIn.doPost : ");
			e.printStackTrace();
		}

	}

}
