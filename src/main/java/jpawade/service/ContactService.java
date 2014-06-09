package jpawade.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import jpawade.model.Capcha;
import jpawade.model.Contact;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ContactService.class);

	public int addMessage(Contact message) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		int res = 1;

		if (message != null)
			message.format();

		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String date = format.format(new Date());

			PreparedStatement addMessage = con
					.prepareStatement(
							"INSERT INTO messages (name, email, message, website, date)"
									+ " VALUES (?, ?, ?, ?, ?);",
							Statement.RETURN_GENERATED_KEYS);

			addMessage.setString(1, message.getName());
			addMessage.setString(2, message.getEmail());
			addMessage.setString(3, message.getMessage());
			addMessage.setString(4, message.getWebsite());
			addMessage.setString(5, date);

			dbModule.executePreparedNonReturn(addMessage);
		} catch (Exception e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
			res = 0;
		} finally {
			dbModule.closeConnection();
		}

		return res;
	}

	public Capcha getCapha() {
		Capcha capcha = null;
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		Random random = new Random();

		try {
			ArrayList<Capcha> capchas = new ArrayList<Capcha>();

			String getCapcha = "SELECT id,question FROM capcha;";

			ResultSet rsCapcha = dbModule.executeReturn(getCapcha);
			while (rsCapcha.next()) {
				int id = rsCapcha.getInt("id");
				String question = rsCapcha.getString("question");

				capchas.add(new Capcha(id, question, ""));
			}

			int randId = random.nextInt(capchas.size());
			capcha = capchas.get(randId);
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return capcha;
	}

	public int checkCapha(Capcha capcha) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();
		String answer = "";
		int ret = 0;

		try {
			PreparedStatement getCapcha = con
					.prepareStatement("SELECT answer FROM capcha where id = ?");

			getCapcha.setInt(1, capcha.getId());

			ResultSet rsCapcha = dbModule.executePreparedReturn(getCapcha);
			while (rsCapcha.next()) {
				answer = rsCapcha.getString("answer");
			}

			if (capcha.getAnswer().equals(answer))
				ret = 1;

		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
			ret = 0;
		} finally {
			dbModule.closeConnection();
		}

		return ret;
	}
}
