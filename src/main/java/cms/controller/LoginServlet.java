package cms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cms.service.LoginService;

@WebServlet(urlPatterns = { "/cms/Login/*" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String exit = req.getParameter("ext");
		if (exit != null && exit.equals("1")) {
			HttpSession session = req.getSession();
			session.removeAttribute("login");
			resp.sendRedirect("Login");
			return;
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		LoginService loginService = new LoginService();

		if (loginService.checkLogin(name, pass)) {
			HttpSession session = req.getSession();
			session.setAttribute("login", "admin");
			resp.getWriter().write("OK");
		} else {
			resp.getWriter().write("Incorrect Login");
		}

	}
}
