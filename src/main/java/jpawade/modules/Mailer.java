package jpawade.modules;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mailer {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(Mailer.class);

	public int sendMail(String email, String content) {
		int res = 1;
		final String username = "adityap174@gmail.com";
		final String pass = "diabolus in musica";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, pass);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Mail from JPawade");
			message.setText(content);

			Transport.send(message);

			System.out.println("Done");
		} catch (MessagingException e) {
			slf4jLogger.debug("Messaging Exception : " + e);
			res = 0;
		} catch (Exception e) {
			slf4jLogger.debug("Exception : " + e);
			res = 0;
		}
		return res;
	}
}
