package jpawade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpawade.model.Hosted;
import jpawade.service.HostedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = { "/Hosted/*" })
public class HostedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(HostedServlet.class);

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String string_id = req.getParameter("id");

		try {
			int hosted_id = Integer.parseInt(string_id);
			HostedService hostedService = new HostedService();
			Hosted hosted = hostedService.getHosted(hosted_id);

			if (hosted == null) {
				slf4jLogger.debug("No hosted project received");
				throw new Exception();
			}

			req.setAttribute("hosted", hosted);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher(hosted.getType() + ".jsp");
			dispatcher.forward(req, resp);

		} catch (NumberFormatException e) {
			slf4jLogger.debug("HostedServlet get number Exception : "
					+ e.getMessage());
			resp.sendRedirect("Portfolio");
		} catch (Exception e) {
			slf4jLogger.debug("HostedServlet get Exception : "
					+ e.getMessage());
			resp.sendRedirect("Portfolio");
		}
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
