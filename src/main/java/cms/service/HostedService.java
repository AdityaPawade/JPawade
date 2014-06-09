package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			if (name != null)
				hosted = new Hosted(id, name, path, type);

		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return hosted;
	}

	public ArrayList<Hosted> getAllHosted() {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();
		ArrayList<Hosted> hosted = new ArrayList<Hosted>();

		try {
			PreparedStatement getDesc = con
					.prepareStatement("SELECT id,name,path,type FROM hosted");

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);

			while (rsDesc.next()) {
				int id = rsDesc.getInt("id");
				String name = rsDesc.getString("name");
				String path = rsDesc.getString("path");
				String type = rsDesc.getString("type");
				hosted.add(new Hosted(id, name, path, type));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return hosted;
	}

	public int modifyHosted(Hosted hosted) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		if (hosted != null)
			hosted.format();

		int res = 0;
		try {

			hosted.format();

			PreparedStatement dbPost = null;

			if (hosted.getId() != -1) {
				if (hosted.getPath() == null || hosted.getPath().equals("")) {
					dbPost = con
							.prepareStatement("UPDATE hosted SET name = ?, type= ? WHERE id=?");

					dbPost.setString(1, hosted.getName());
					dbPost.setString(2, hosted.getType());
					dbPost.setInt(3, hosted.getId());
				} else {
					dbPost = con
							.prepareStatement("UPDATE hosted SET name = ?, type = ?, path = ? WHERE id= ?");

					dbPost.setString(1, hosted.getName());
					dbPost.setString(3, hosted.getType());
					dbPost.setString(2, hosted.getPath());
					dbPost.setInt(4, hosted.getId());
				}
				res = dbModule.executePreparedNonReturn(dbPost);
				if (res != 0)
					res = hosted.getId();
			} else {

				dbPost = con
						.prepareStatement(
								"INSERT INTO `hosted` (`name`, `path`, `type`) "
										+ "VALUES (?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);

				dbPost.setString(1, hosted.getName());
				dbPost.setString(2, hosted.getPath());
				dbPost.setString(3, hosted.getType());

				res = dbModule.executePreparedNonReturn(dbPost);

				int id = -1;
				ResultSet genKeys = dbModule.getPreparedGeneratedKeys(dbPost);
				if (genKeys.next()) {
					id = genKeys.getInt(1);
					res = id;
				}
			}
			if (res == 0) {
				slf4jLogger.debug("Unable to add Hosted Project");
			}
		} catch (Exception e) {
			slf4jLogger.debug("Unable to add Hosted Project : "
					+ e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return res;
	}

	public int removeHosted(int id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		int res = 0;
		try {
			PreparedStatement deleteHosted = con
					.prepareStatement("Delete from hosted "
							+ "where `id` = ?");
			deleteHosted.setInt(1, id);
			res = dbModule.executePreparedNonReturn(deleteHosted);

			if (res == 0) {
				slf4jLogger.debug("Unable to remove Hosted");
			}
		} catch (Exception e) {
			slf4jLogger.debug("Unable to remove Hosted : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return res;
	}
}
