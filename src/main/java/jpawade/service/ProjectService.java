package jpawade.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jpawade.model.Project;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ProjectService.class);

	public Project getProject(int id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		try {
			String getIds = "SELECT id FROM projects p;";

			ResultSet rsIds = dbModule.executeReturn(getIds);

			ArrayList<Integer> ids = new ArrayList<Integer>();

			while (rsIds.next()) {
				ids.add(rsIds.getInt("id"));
			}

			PreparedStatement getDesc = con
					.prepareStatement("SELECT p.title,p.description,p.url,s.service FROM projects p "
							+ "left join services s "
							+ "on (p.service = s.id) "
							+ "where p.id = ?");
			getDesc.setInt(1, id);

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);

			String title = null;
			String url = null;
			String service = null;
			String description = null;

			while (rsDesc.next()) {
				title = rsDesc.getString("title");
				url = rsDesc.getString("url");
				service = rsDesc.getString("service");
				description = rsDesc.getString("description");
			}

			Project project = new Project(title, description, url, service);
			int pos = ids.indexOf(id);
			if (pos == 0) {
				project.setPrevious(ids.get(ids.size() - 1));
			} else {
				project.setPrevious(ids.get(pos - 1));
			}
			if (pos == (ids.size() - 1)) {
				project.setNext(ids.get(0));
			} else {
				project.setNext(ids.get(pos + 1));
			}

			return project;

		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return null;
	}

	public ArrayList<String> getPics(int id) {
		ArrayList<String> pics = new ArrayList<String>();
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		try {
			PreparedStatement getDesc = con
					.prepareStatement("SELECT pic_url FROM project_pics "
							+ "where project_id = ?");
			getDesc.setInt(1, id);

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);
			while (rsDesc.next()) {
				pics.add(rsDesc.getString("pic_url"));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return pics;
	}
}
