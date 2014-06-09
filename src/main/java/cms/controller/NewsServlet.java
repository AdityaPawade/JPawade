package cms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jpawade.model.News;
import cms.service.NewsService;

@WebServlet(urlPatterns = { "/cms/News/*" })
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("login") == null
				|| !session.getAttribute("login").toString().equals("admin")) {
			resp.sendRedirect("Login");
			return;
		}

		NewsService newsService = new NewsService();
		ArrayList<News> news = newsService.getNews();
		req.setAttribute("news", news);

		RequestDispatcher dispatcher = req.getRequestDispatcher("news.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String func = req.getParameter("func");
		NewsService newsService = new NewsService();

		if (func.equals("add")) {
			String title = req.getParameter("title");
			String message = req.getParameter("message");
			News news = new News(title, message);
			newsService.addNews(news);
		} else {
			int postId = 0;
			String post_id = req.getParameter("newsId");
			try {
				postId = Integer.parseInt(post_id);
			} catch (NumberFormatException e) {
				resp.sendRedirect("News");
				return;
			}
			newsService.removeNews(postId);
		}
		resp.sendRedirect("News");
	}

}
