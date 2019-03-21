package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ServiceException;
import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import util.Checker;

@SuppressWarnings("serial")
public class ProductServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl();
	private final String SHOW = "show";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String active = request.getParameter("active");
		if (!Checker.isNull(active)) {
			treatment(request, response, active);
		} else {
			System.err.println("ProductServlet.doGet() - active is NULL");
			treatment(request, response, SHOW);
		}
	}

	private void treatment(HttpServletRequest request, HttpServletResponse response, String active)
			throws ServletException, IOException {
		switch (active) {
		case "delete":
			if (Checker.isNumber(request.getParameter("id"))) {
				Integer id = Integer.parseInt(request.getParameter("id"));
				try {
					productService.remove(id);
				} catch (ServiceException e) {
					System.err.println("ProductServlet.treatment(), case \"delete\"");
					e.printStackTrace();
					treatment(request, response, SHOW);
//						request.getRequestDispatcher("error.html").forward(request, response);
				}
			}
			showProducts(request, response);
			break;
		case "write":
			writeProduct(request, response);
		case "show":
			showProducts(request, response);
			break;
		case "save":
			saveProduct(request, response);
			request.getRequestDispatcher("createProductView.jsp").forward(request, response);
			break;
		default:
			System.err.println("ProductServlet.treatment() - active is invalid");
			treatment(request, response, SHOW);
//			request.getRequestDispatcher("error.html").forward(request, response);
			break;
		}

	}

	private void saveProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Checker.isNumber(request.getParameter("id"))) {
			Product product = null;
			try {
				product = productService.find(request.getParameter("id")).get(0);
				request.setAttribute("product", product);
			} catch (ServiceException e) {
				request.getRequestDispatcher("error.html").forward(request, response);
				e.printStackTrace();
			}
		}
	}

	private void writeProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		if (Checker.isNumber(request.getParameter("id"))) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			product.setId(id);
		}
		product.setCode(request.getParameter("code"));
		product.setName(request.getParameter("name"));

		if (Checker.isNumber(request.getParameter("price"))) {
			Float price = Float.parseFloat(request.getParameter("price"));
			product.setPrice(price);
		} else {
			product.setPrice(0.0f);
		}
		try {
			productService.save(product);
		} catch (ServiceException e) {
			request.getRequestDispatcher("error.html").forward(request, response);
			e.printStackTrace();
		}
	}

	private void showProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("productList", productService.getAll());
		} catch (ServiceException e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		request.getRequestDispatcher("productListView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
