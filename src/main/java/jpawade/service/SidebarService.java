package jpawade.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jpawade.model.Archive;
import jpawade.model.BlogPost;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SidebarService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(SidebarService.class);

	public ArrayList<BlogPost> getTopPosts() {
		ArrayList<BlogPost> posts = new ArrayList<BlogPost>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {

			String getDesc = "select p.id,p.date,p.title,count(c.message) as commsize "
					+ "from blog_post p left join comment c "
					+ "on (p.id=c.post_id) "
					+ "group by c.post_id "
					+ "order by (commsize) desc limit 3;";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			if (rsDesc == null)
				return null;
			while (rsDesc.next()) {
				int id = rsDesc.getInt("id");
				Date date = rsDesc.getDate("date");
				String title = rsDesc.getString("title");
				int commsize = rsDesc.getInt("commsize");

				posts.add(new BlogPost(id, date, title, commsize));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return posts;
	}

	public ArrayList<String> getTags() {
		ArrayList<String> tags = new ArrayList<String>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {

			String getDesc = "SELECT distinct(tag) FROM tags";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			while (rsDesc.next()) {
				String tag = rsDesc.getString("tag");
				tags.add(tag);
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return tags;
	}

	public ArrayList<Archive> getArchives() {
		ArrayList<Archive> archives = new ArrayList<Archive>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {

			String getDesc = "SELECT COUNT(*) as count,DATE_FORMAT(date, '%M %Y') as dateArchive "
					+ "FROM jpawade.blog_post GROUP BY DATE_FORMAT(date, '%M %Y') ORDER BY date desc;";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			while (rsDesc.next()) {
				Archive archive = new Archive(rsDesc.getInt("count"),
						rsDesc.getString("dateArchive"));
				archives.add(archive);
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return archives;
	}
}
