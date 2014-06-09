package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

	public void addNews(News news) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		if (news != null)
			news.format();

		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String date = format.format(new Date());

			PreparedStatement addNews = con
					.prepareStatement("INSERT INTO `news` (`title`, `message`, `date`)"
							+ " VALUES (?, ?, ?)");

			addNews.setString(1, news.getTitle());
			addNews.setString(2, news.getMessage());
			addNews.setString(3, date);

			dbModule.executePreparedNonReturn(addNews);
		} catch (Exception e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
	}

	public void removeNews(Integer id) {
		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		try {
			PreparedStatement removeNews = con
					.prepareStatement("DELETE FROM news where id = ?");
			removeNews.setInt(1, id);

			dbModule.executePreparedNonReturn(removeNews);
		} catch (Exception e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
	}
}
