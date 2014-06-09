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
import jpawade.service.ProjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = { "/Project/*" })
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ProjectServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String string_id = req.getParameter("id");

		try {
			int project_id = Integer.parseInt(string_id);
			ProjectService projectService = new ProjectService();
			Project project = projectService.getProject(project_id);

			if (project == null) {
				slf4jLogger.debug("No project received");
				throw new Exception();
			}

			ArrayList<String> pics = projectService.getPics(project_id);
			project.setPicUrls(pics);

			req.setAttribute("project", project);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("project.jsp");
			dispatcher.forward(req, resp);
		} catch (NumberFormatException e) {
			slf4jLogger.debug("ProjectServlet get number Exception : "
					+ e.getMessage());
			resp.sendRedirect("Portfolio");
		} catch (Exception e) {
			slf4jLogger.debug("ProjectServlet get Exception : "
					+ e.getMessage());
			resp.sendRedirect("Portfolio");
		}

	}
}
