package jpawade.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpawade.model.Project;
import jpawade.service.PortfolioService;

@WebServlet(urlPatterns = { "/Portfolio/*" })
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PortfolioService portfolioService = new PortfolioService();
		ArrayList<Project> projects = portfolioService.getProjects();
		req.setAttribute("projects", projects);

		ArrayList<String> services = new ArrayList<String>();
		for (Project project : projects) {
			if (!services.contains(project.getService()))
				services.add(project.getService());
		}
		req.setAttribute("services", services);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("portfolio.jsp");
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
