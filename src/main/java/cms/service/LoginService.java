package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(LoginService.class);

	public boolean checkLogin(String name, String pass) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();
		String dbPass = "";
		try {
			PreparedStatement getDesc = con
					.prepareStatement("SELECT pass FROM login where name= ?");
			getDesc.setString(1, name);

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);
			while (rsDesc.next()) {
				dbPass = rsDesc.getString("pass");
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		if (pass.equals(dbPass))
			return true;
		return false;
	}
}
