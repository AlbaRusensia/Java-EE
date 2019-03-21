package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.ServiceException;
import service.UserService;
import service.UserServiceImpl;
import util.Checker;
import util.SwitcherUtil;

/**
 * Servlet Filter implementation class SessionSecurityFilter
 */
public class SessionSecurityFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SessionSecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */

	private UserService userService;
	private HttpSession session;
	private final String START_URL = "http://localhost:8080/MyStoreSecond/";
	private final String WELCOM_URL = "http://localhost:8080/MyStoreSecond/singIn";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		session = req.getSession();
		String currentURL = String.valueOf(req.getRequestURL());
		if (!START_URL.equals(currentURL) && !WELCOM_URL.equals(currentURL)) {
			try {
				if (Checker.isNull(session) || !userService.singIn(String.valueOf(session.getAttribute("login")),
						String.valueOf(session.getAttribute("password")))) {
					SwitcherUtil.relay(req, res, "singIn.jsp");
					return;
				} else {
					chain.doFilter(request, response);
					return;
				}
			} catch (ServiceException e) {
				System.err.println("SessionSecurityFilter.doFilter() : ");
				e.printStackTrace();
			}
		} else {
			chain.doFilter(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		userService = new UserServiceImpl();
	}

}
