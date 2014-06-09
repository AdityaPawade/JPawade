package cms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jpawade.model.Project;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortfolioService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(PortfolioService.class);

	public ArrayList<Project> getProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {
			String getDesc = "SELECT Distinct(ps.title),ps.id,ps.service,i.pic_url FROM "
					+ "(SELECT p.id,p.title,s.service FROM projects p "
					+ "left join services s "
					+ "on (p.service=s.id)) ps "
					+ "left join project_pics i "
					+ "on (ps.id=i.project_id)  group by title order by ps.id;";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			while (rsDesc.next()) {
				int id = rsDesc.getInt("id");
				String title = rsDesc.getString("title");
				String service = rsDesc.getString("service");
				String pic_url = rsDesc.getString("pic_url");

				if (pic_url == null)
					pic_url = "projects/single1.jpg";

				projects.add(new Project(id, title, service, pic_url));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return projects;
	}
}
