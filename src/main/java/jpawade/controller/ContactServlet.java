package jpawade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpawade.model.Capcha;
import jpawade.model.Contact;
import jpawade.service.ContactService;

@WebServlet(urlPatterns = { "/Contact/*" })
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ContactService contactService = new ContactService();
		Capcha capcha = contactService.getCapha();
		req.setAttribute("capcha", capcha);

		RequestDispatcher dispatcher = req.getRequestDispatcher("contact.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String message = req.getParameter("message");
		String website = req.getParameter("website");
		String ans = req.getParameter("answer");
		String capid = req.getParameter("capid");

		// Capcha capcha = new Capcha(0, capid, ans)

		int cId = 0;
		try {
			cId = Integer.parseInt(capid);
		} catch (NumberFormatException e) {
			resp.getWriter().write("Incorrect Answer");
			return;
		}

		if (!check(name) || !check(email) || !check(message) || !check(ans)) {
			resp.getWriter().write("Please Enter the Required Fields");
			return;
		}

		if (!check(website)) {
			website = "";
		}

		ContactService contactService = new ContactService();

		if (contactService.checkCapha(new Capcha(cId, "", ans)) == 0) {
			resp.getWriter().write("Incorrect Answer");
			return;
		}

		if (contactService
				.addMessage(new Contact(name, email, message, website)) == 0) {
			resp.getWriter().write("An error occurred");
		} else {
			resp.getWriter().write("OK");
			// resp.sendRedirect("Contact");
		}
	}

	private boolean check(String input) {
		if (input != null && !input.equals(""))
			return true;
		return false;
	}

}
