package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jpawade.model.BlogPost;
import jpawade.model.Tag;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlogService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(BlogService.class);

	public ArrayList<BlogPost> getBlogPosts(String search, String filterDate,
			String filterTag, int page, double postPerPage) {
		ArrayList<BlogPost> posts = new ArrayList<BlogPost>();

		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		try {
			String getTags = "select post_id,tag from tags";
			ResultSet rsTags = dbModule.executeReturn(getTags);
			ArrayList<Tag> allTags = new ArrayList<Tag>();
			while (rsTags.next()) {
				String tag = rsTags.getString("tag");
				int post_id = rsTags.getInt("post_id");
				allTags.add(new Tag(post_id, tag));
			}

			PreparedStatement getDesc = con
					.prepareStatement("select distinct(title),pc.id,date,description,pic_url,commsize from "
							+ "(select p.id,p.date,p.title,p.description,p.pic_url,count(c.message) as commsize "
							+ "from blog_post p left join comment c "
							+ "on (p.id=c.post_id) "
							+ "where ((p.title like ? || p.description like ? || p.content like ?) "
							+ "&& DATE_FORMAT(p.date, '%M %Y') like ? ) group by p.id order by p.date desc) pc "
							+ "left join tags t on (pc.id = t.post_id) "
							+ "where t.tag like ? limit "
							+ (int) postPerPage
							+ " offset "
							+ (int) (postPerPage * (page - 1)));

			getDesc.setString(1, "%" + search + "%");
			getDesc.setString(2, "%" + search + "%");
			getDesc.setString(3, "%" + search + "%");
			getDesc.setString(4, "%" + filterDate + "%");
			getDesc.setString(5, "%" + filterTag + "%");

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);

			while (rsDesc.next()) {
				int id = rsDesc.getInt("id");
				Date date = rsDesc.getDate("date");
				String title = rsDesc.getString("title");
				String pic_url = rsDesc.getString("pic_url");
				String desc = rsDesc.getString("description");
				int commsize = rsDesc.getInt("commsize");

				ArrayList<String> tags = new ArrayList<String>();
				for (Tag tag : allTags) {
					if (tag.post_id == id) {
						tags.add(tag.tag);
					}
				}
				posts.add(new BlogPost(id, date, title, pic_url, desc,
						commsize, tags, false));
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return posts;
	}

	public int getPostCount(String search, String filterDate, String filterTag) {
		int count = 0;

		DBModule dbModule = new DBModule();
		Connection con = dbModule.createConnection();

		try {
			PreparedStatement getCount = con
					.prepareStatement("select count(*) as count from (select distinct(title) from "
							+ "(select id,title "
							+ "from blog_post "
							+ "where ((title like ? || description like ? || content like ?)  "
							+ "&& DATE_FORMAT(date, '%M %Y') like ? )) p "
							+ "left join tags t on (p.id = t.post_id) where t.tag like ?) pt");

			getCount.setString(1, "%" + search + "%");
			getCount.setString(2, "%" + search + "%");
			getCount.setString(3, "%" + search + "%");
			getCount.setString(4, "%" + filterDate + "%");
			getCount.setString(5, "%" + filterTag + "%");

			ResultSet rsCount = dbModule.executePreparedReturn(getCount);

			while (rsCount.next()) {
				count = rsCount.getInt("count");
			}
		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}

		return count;
	}
}
