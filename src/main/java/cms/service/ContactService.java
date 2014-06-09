package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jpawade.model.Contact;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ContactService.class);

	public ArrayList<Contact> getMessages() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {
			String getDesc = "SELECT id,name,email,message,website,date FROM messages order by date desc limit 10;";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			while (rsDesc.next()) {
				int id = rsDesc.getInt("id");
				String name = rsDesc.getString("name");
				String email = rsDesc.getString("email");
				String message = rsDesc.getString("message");
				String website = rsDesc.getString("website");
				Date date = rsDesc.getDate("date");

				contacts.add(new Contact(id, name, email, message, website,
						date));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return contacts;
	}

	public void removeMessage(Integer id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		try {
			PreparedStatement removeMessage = con
					.prepareStatement(
					"DELETE FROM messages where id = ?");
			removeMessage.setInt(1, id);

			dbModule.executePreparedNonReturn(removeMessage);
		} catch (Exception e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
	}
}
