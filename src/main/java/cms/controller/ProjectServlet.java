package cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jpawade.model.Project;
import jpawade.model.Service;
import jpawade.modules.StaticModules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cms.service.ProjectService;

@MultipartConfig
@WebServlet(urlPatterns = { "/cms/Project/*" })
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ProjectServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("login") == null
				|| !session.getAttribute("login").toString().equals("admin")) {
			resp.sendRedirect("Login");
			return;
		}

		String string_id = req.getParameter("id");
		ProjectService projectService = new ProjectService();
		int project_id = -1;

		try {
			project_id = Integer.parseInt(string_id);
		} catch (NumberFormatException e) {
			ArrayList<Service> services = projectService.getServices();
			req.setAttribute("services", services);
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("project.jsp");
			dispatcher.forward(req, resp);
			return;
		}

		try {

			Project project = projectService.getProject(project_id);

			if (project != null) {
				ArrayList<String> pics = projectService.getPics(project_id);
				project.setPicUrls(pics);
				req.setAttribute("project", project);

				ArrayList<Service> services = projectService.getServices();
				req.setAttribute("services", services);

			} else {
				ArrayList<Service> services = projectService.getServices();
				req.setAttribute("services", services);

			}

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("project.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			slf4jLogger.debug("ProjectServlet get Exception : "
					+ e.getMessage());
			resp.sendRedirect("project.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String project_id = req.getParameter("projectId");
		int projectId = -1;
		try {
			projectId = Integer.parseInt(project_id);
		} catch (NumberFormatException e) {
			slf4jLogger.debug("number exception");
			projectId = -1;
		}

		String func = req.getParameter("func");
		ProjectService projectService = new ProjectService();

		if (func == null) {
			resp.sendRedirect("Project?id=" + projectId);
			return;
		}

		if (func.equals("remove")) {
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");

			int res = projectService.removeProject(projectId);
			if (res == 1)
				resp.getWriter().write("OK");
			else
				resp.getWriter().write("Error Occured");
			return;
		} else if (func.equals("modify")) {

			String title = req.getParameter("title");
			String desc = req.getParameter("desc");
			String url = req.getParameter("url");

			String service = req.getParameter("service");
			int serviceId = 0;
			try {
				serviceId = Integer.parseInt(service);
			} catch (NumberFormatException e) {
				slf4jLogger.debug("service format exception : " + service);
				resp.sendRedirect("Project?id=" + projectId);
				return;
			}

			Project project = new Project(projectId, title, desc, url,
					serviceId);

			int resAddProject = projectService.modifyProject(project);

			if (resAddProject == 0) {
				slf4jLogger.debug("project servlet sql exception");
				resp.sendRedirect("Project?id=" + projectId);
				return;
			} else {
				ArrayList<String> picUrls = new ArrayList<String>();

				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part.getName().equals("image")) {
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat(
								"dd-MM-hh-mm-ss-");
						String imgName = StaticModules.getFilename(part);

						if (imgName != null && imgName.length() > 15)
							imgName = imgName.substring(0, 15);

						if (imgName != null && !imgName.equals("")) {
							imgName = format.format(date) + imgName;
							String server_path = getServletContext()
									.getRealPath(
											"/");
							String upload_directory = "style/images/projects/";
							String complete_path = server_path
									+ upload_directory;

							part.write(complete_path + File.separator + imgName);
							String dbPath = "projects/" + imgName;
							picUrls.add(dbPath);
						}
					}
				}
				if (picUrls.size() > 0) {
					int res = projectService.modifyPics(resAddProject, picUrls);
					if (res == 0) {
						resp.sendRedirect("Project?id=" + projectId);
						return;
					}
				}
			}
			resp.sendRedirect("Project?id=" + resAddProject);
		}
	}
}
