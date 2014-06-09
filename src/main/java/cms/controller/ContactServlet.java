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

import jpawade.model.Contact;
import jpawade.modules.Mailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cms.service.ContactService;

@WebServlet(urlPatterns = { "/cms/Contact/*" })
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ContactServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("login") == null
				|| !session.getAttribute("login").toString().equals("admin")) {
			resp.sendRedirect("Login");
			return;
		}

		ContactService contactService = new ContactService();
		ArrayList<Contact> messages = contactService.getMessages();
		req.setAttribute("messages", messages);

		RequestDispatcher dispatcher = req.getRequestDispatcher("contact.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String func = req.getParameter("func");
		ContactService contactService = new ContactService();

		if (func.equals("mail")) {
			String email = req.getParameter("email");
			String message = req.getParameter("message");
			Mailer mailer = new Mailer();
			if (mailer.sendMail(email, message) == 0) {
				slf4jLogger.debug("Unable to send Mail");
			}
		} else if (func.equals("remove")) {
			int messageId = 0;
			String message_id = req.getParameter("messageId");
			try {
				messageId = Integer.parseInt(message_id);
			} catch (NumberFormatException e) {
				resp.sendRedirect("Contact");
				return;
			}
			contactService.removeMessage(messageId);
		}
		resp.sendRedirect("Contact");
	}

}
