package jpawade.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpawade.model.News;
import jpawade.service.NewsService;

@WebServlet(urlPatterns = { "/News/*" })
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NewsService newsService = new NewsService();
		ArrayList<News> news = newsService.getNews();
		req.setAttribute("news", news);

		RequestDispatcher dispatcher = req.getRequestDispatcher("news.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

}
