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

import jpawade.model.Hosted;
import jpawade.modules.StaticModules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cms.service.HostedService;

@MultipartConfig
@WebServlet(urlPatterns = { "/cms/Hosted/*" })
public class HostedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(HostedServlet.class);

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
		if (string_id == null)
			string_id = "";
		HostedService hostedService = new HostedService();
		int hosted_id = -1;

		try {
			ArrayList<Hosted> allHosted = hostedService.getAllHosted();
			req.setAttribute("allHosted", allHosted);

			hosted_id = Integer.parseInt(string_id);
			Hosted hosted = hostedService.getHosted(hosted_id);
			req.setAttribute("hosted", hosted);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("hosted.jsp");
			dispatcher.forward(req, resp);
		} catch (NumberFormatException e) {
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("hosted.jsp");
			dispatcher.forward(req, resp);
			return;
		} catch (Exception e) {
			slf4jLogger.debug("HostedServlet get Exception : "
					+ e.getMessage());
			resp.sendRedirect("Hosted");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			String project_id = req.getParameter("id");
			int projectId = -1;
			try {
				projectId = Integer.parseInt(project_id);
			} catch (NumberFormatException e) {
				slf4jLogger.debug("number exception");
				projectId = -1;
			}

			String func = req.getParameter("func");
			HostedService hostedService = new HostedService();

			if (func == null) {
				resp.sendRedirect("Hosted");
			} else if (func.equals("remove")) {
				resp.setContentType("text/plain");
				resp.setCharacterEncoding("UTF-8");

				Hosted hosted = hostedService.getHosted(projectId);
				String server_path = getServletContext().getRealPath("/");
				String path = hosted.getType() + "/" + hosted.getPath();
				String filePath = server_path + "hosted/" + path;
				File file = new File(filePath);
				try {
					if (!file.delete()) {
						slf4jLogger
								.debug("Hosted File Not Deleted " + filePath);
					}
				} catch (Exception e) {
					slf4jLogger.debug("HostedServlet File Delete Exception : "
							+ e.getMessage());
				}

				int res = hostedService.removeHosted(projectId);
				if (res == 1)
					resp.getWriter().write("OK");
				else
					resp.getWriter().write("Error Occured");
				return;
			} else if (func.equals("modify")) {
				String name = req.getParameter("name");
				String type = req.getParameter("type");
				String dbPath = "";

				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part == null || part.getName() == null)
						continue;
					if (part.getName().equals("file")) {
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat(
								"dd-MM-hh-mm-ss-");
						String origName = StaticModules.getFilename(part);
						if (origName == null || origName.equals("")) {
							dbPath = "";
							continue;
						}

						String fileName = format.format(date)
								+ origName;

						/*if (fileName.length() > 30)
							fileName = fileName.substring(0, 30);*/

						if (!fileName.equals("")) {
							String server_path = getServletContext()
									.getRealPath(
											"/");
							String upload_directory = "hosted/" + type + "/";
							String complete_path = server_path
									+ upload_directory;

							File dir = new File(complete_path);
							if (!dir.exists()) {
								dir.mkdir();
							}

							part.write(complete_path + File.separator
									+ fileName);
							dbPath = fileName;
						}
					}
				}

				Hosted hosted = new Hosted(projectId, name, dbPath, type);
				int resAddProject = hostedService.modifyHosted(hosted);

				resp.sendRedirect("Hosted?id=" + resAddProject);
			}
		} catch (Exception e) {
			slf4jLogger.debug("HostedServlet post Exception : "
					+ e.getMessage());
			resp.sendRedirect("Hosted");
		}

	}
}
