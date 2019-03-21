package util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SwitcherUtil {

	public static void relay(HttpServletRequest req, HttpServletResponse resp, String nextURL)
			throws ServletException, IOException {
		req.getRequestDispatcher(nextURL).forward(req, resp);
	}
}
