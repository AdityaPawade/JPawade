package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jpawade.model.Project;
import jpawade.model.Service;
import jpawade.modules.DBModule;
import jpawade.modules.StaticModules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ProjectService.class);

	public Project getProject(int id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();
		Project project = null;

		try {
			String getIds = "SELECT id FROM projects p;";

			ResultSet rsIds = dbModule.executeReturn(getIds);

			ArrayList<Integer> ids = new ArrayList<Integer>();

			while (rsIds.next()) {
				ids.add(rsIds.getInt("id"));
			}

			PreparedStatement getDesc = con
					.prepareStatement("SELECT p.title,p.description,p.url,s.id as service_id,s.service FROM projects p "
							+ "left join services s "
							+ "on (p.service = s.id) "
							+ "where p.id = ?");
			getDesc.setInt(1, id);

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);

			String title = null;
			String url = null;
			String service = null;
			String description = null;
			int serviceId = 0;

			while (rsDesc.next()) {
				title = rsDesc.getString("title");
				url = rsDesc.getString("url");
				if (url == null)
					url = "projects/single1.jpg";
				service = rsDesc.getString("service");
				description = rsDesc.getString("description");
				serviceId = rsDesc.getInt("service_id");
			}

			if (title != null) {
				project = new Project(id, title, description, url, service);
				project.setServiceId(serviceId);

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

			}

		} catch (SQLException e) {
			slf4jLogger.debug("get project sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return project;
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
			slf4jLogger.debug("get pics sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return pics;
	}

	public ArrayList<Service> getServices() {
		ArrayList<Service> services = new ArrayList<Service>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {
			String getDesc = "SELECT id,service FROM services;";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			while (rsDesc.next()) {
				services.add(new Service(rsDesc.getInt("id"), rsDesc
						.getString("service")));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("get services sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return services;
	}

	public int modifyProject(Project project) {
		DBModule dbModule = new DBModule();

		Connection con = dbModule.createConnection();

		if (project != null)
			project.format();

		int res = 0;
		try {

			PreparedStatement dbPost = null;
			project.format();

			if (project.getId() != -1) {
				dbPost = con
						.prepareStatement("UPDATE `projects` "
								+ "SET `title`=?, `description`=?, `url`=?, `service`=? "
								+ "WHERE `id`=?");
				dbPost.setString(1, project.getTitle());
				dbPost.setString(2, project.getDescription());
				dbPost.setString(3, project.getUrl());
				dbPost.setInt(4, project.getServiceId());
				dbPost.setInt(5, project.getId());

				res = dbModule.executePreparedNonReturn(dbPost);
				if (res != 0)
					res = project.getId();
			} else {
				dbPost = con
						.prepareStatement(
								"INSERT INTO `projects` (`title`, `description`, `url`, `service`) "
										+ "VALUES (?, ?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);

				dbPost.setString(1, project.getTitle());
				dbPost.setString(2, project.getDescription());
				dbPost.setString(3, project.getUrl());
				dbPost.setInt(4, project.getServiceId());

				res = dbModule.executePreparedNonReturn(dbPost);

				int id = -1;
				ResultSet genKeys = dbModule.getPreparedGeneratedKeys(dbPost);
				if (genKeys.next()) {
					id = genKeys.getInt(1);
					res = id;
				}
			}
			if (res == 0) {
				slf4jLogger.debug("Unable to add Post");
			}
		} catch (Exception e) {
			slf4jLogger.debug("Unable to add Post : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return res;
	}

	public int modifyPics(int id, ArrayList<String> picUrls) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		for (String string : picUrls) {
			StaticModules.dbFormat(string);
		}

		int res = 0;
		try {

			PreparedStatement deletePics = con
					.prepareStatement("Delete from project_pics "
							+ "where `project_id` = ?");
			deletePics.setInt(1, id);
			res = dbModule.executePreparedNonReturn(deletePics);

			for (String url : picUrls) {
				PreparedStatement addTag = con
						.prepareStatement("INSERT INTO `project_pics` (`project_id`, `pic_url`) "
								+ "VALUES (?, ?);");
				addTag.setInt(1, id);
				addTag.setString(2, url);

				int result = dbModule.executePreparedNonReturn(addTag);
				if (result == 0) {
					res = 0;
				}
			}

			if (res == 0) {
				slf4jLogger.debug("Unable to add Post");
			}
		} catch (Exception e) {
			slf4jLogger.debug("Unable to add Post : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return res;
	}

	public int removeProject(int id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		int res = 0;
		try {

			PreparedStatement deletePics = con
					.prepareStatement("Delete from project_pics "
							+ "where `project_id` = ?");
			deletePics.setInt(1, id);
			res = dbModule.executePreparedNonReturn(deletePics);
			if (res == 0) {
				slf4jLogger.debug("Unable to remove Project Pics");
			}

			PreparedStatement deleteProject = con
					.prepareStatement("Delete from projects "
							+ "where `id` = ?");
			deleteProject.setInt(1, id);
			res = dbModule.executePreparedNonReturn(deleteProject);

			if (res == 0) {
				slf4jLogger.debug("Unable to remove Project");
			}
		} catch (Exception e) {
			slf4jLogger.debug("Unable to remove Post : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return res;
	}
}
