package jpawade.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jpawade.model.News;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(NewsService.class);

	public ArrayList<News> getNews() {
		ArrayList<News> news = new ArrayList<News>();
		DBModule dbModule = new DBModule();
		dbModule.createConnection();

		try {
			String getDesc = "SELECT id,title,message,date FROM news order by date desc limit 10;";

			ResultSet rsDesc = dbModule.executeReturn(getDesc);
			while (rsDesc.next()) {
				int id = rsDesc.getInt("id");
				String title = rsDesc.getString("title");
				String message = rsDesc.getString("message");
				Date date = rsDesc.getDate("date");

				news.add(new News(id, title, message, date));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return news;
	}
}
