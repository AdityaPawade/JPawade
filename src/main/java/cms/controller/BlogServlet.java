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

import jpawade.model.Archive;
import jpawade.model.BlogPost;
import cms.service.BlogService;
import cms.service.SidebarService;

@WebServlet(urlPatterns = { "/cms/Blog/*" })
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	double postPerPage = 3.0;

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		if (session.getAttribute("login") == null
				|| !session.getAttribute("login").toString().equals("admin")) {
			resp.sendRedirect("Login");
			return;
		}

		String filter = "";
		String search = req.getParameter("s");
		if (search == null) {
			search = "";
		} else {
			filter = filter.concat("s=" + search + "&");
		}
		String filterDate = req.getParameter("date");
		if (filterDate == null) {
			filterDate = "";
		} else {
			filter = filter.concat("date=" + filterDate + "&");
		}
		String filterTag = req.getParameter("tag");
		if (filterTag == null) {
			filterTag = "";
		} else {
			filter = filter.concat("tag=" + filterTag + "&");
		}
		req.setAttribute("filter", filter);

		String page = req.getParameter("page");
		if (page == null || page == "")
			page = "1";

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			pageNo = 1;
		}

		BlogService blogService = new BlogService();

		int count = blogService.getPostCount(search, filterDate, filterTag);
		req.setAttribute("pages", Math.ceil(count / postPerPage));
		req.setAttribute("page", pageNo);

		ArrayList<BlogPost> posts = blogService.getBlogPosts(search,
				filterDate, filterTag, pageNo, postPerPage);
		req.setAttribute("posts", posts);

		SidebarService sidebarService = new SidebarService();
		ArrayList<BlogPost> topPosts = sidebarService.getTopPosts();
		ArrayList<String> tags = sidebarService.getTags();
		ArrayList<Archive> archives = sidebarService.getArchives();

		req.setAttribute("popular", topPosts);
		req.setAttribute("tags", tags);
		req.setAttribute("archives", archives);

		RequestDispatcher dispatcher = req.getRequestDispatcher("blog.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
}
