package jpawade.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jpawade.model.Hosted;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostedService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(HostedService.class);

	public Hosted getHosted(int id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();
		Hosted hosted = null;

		try {

			PreparedStatement getDesc = con
					.prepareStatement("SELECT name,path,type FROM hosted where id = ?");

			getDesc.setInt(1, id);

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);

			String name = null;
			String path = null;
			String type = null;

			while (rsDesc.next()) {
				name = rsDesc.getString("name");
				path = rsDesc.getString("path");
				type = rsDesc.getString("type");
			}

			hosted = new Hosted(name, path, type);

		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return hosted;
	}
}
