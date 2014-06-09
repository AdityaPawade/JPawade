package jpawade.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jpawade.model.BlogPost;
import jpawade.model.Comment;
import jpawade.modules.DBModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostService {
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(PostService.class);

	public BlogPost getPost(int id) {
		DBModule dbModule = new DBModule();

		Connection con = dbModule.createConnection();
		BlogPost post = null;

		try {
			PreparedStatement getTags = con
					.prepareStatement("select tag from tags where post_id = ?");
			getTags.setInt(1, id);

			ResultSet tags = dbModule.executePreparedReturn(getTags);
			ArrayList<String> postTags = new ArrayList<String>();

			while (tags.next()) {
				String tag = tags.getString("tag");
				postTags.add(tag);
			}

			int cSize = 0;

			PreparedStatement getComments = con
					.prepareStatement("select id,parent_id,message,name,email,website,date from comment where post_id = ?");
			getComments.setInt(1, id);

			ResultSet comments = dbModule.executePreparedReturn(getComments);
			ArrayList<Comment> postComments = new ArrayList<Comment>();

			while (comments.next()) {
				String message = comments.getString("message");
				String name = comments.getString("name");
				String email = comments.getString("email");
				String website = comments.getString("website");
				Date date = comments.getDate("date");
				int commentId = comments.getInt("id");
				Integer parentId = comments.getInt("parent_id");
				if (comments.wasNull()) {
					parentId = -1;
					cSize++;
				}
				postComments.add(new Comment(commentId, parentId, message,
						name, email, website, date));
			}

			PreparedStatement getDesc = con
					.prepareStatement("select date,title,content,pic_url from blog_post where id = ?");
			getDesc.setInt(1, id);

			ResultSet rsDesc = dbModule.executePreparedReturn(getDesc);
			while (rsDesc.next()) {
				Date date = rsDesc.getDate("date");
				String title = rsDesc.getString("title");
				String pic_url = rsDesc.getString("pic_url");
				String content = rsDesc.getString("content");
				post = new BlogPost(id, date, title, pic_url, content,
						postComments.size(), postTags, true);
				post.setComments(postComments);
				post.setCommentsSize(cSize);
			}

		} catch (SQLException e) {
			slf4jLogger.debug("sql exception : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return post;
	}

	public int addComment(Comment comment) {
		DBModule dbModule = new DBModule();

		Connection con = dbModule.createConnection();

		if (comment != null)
			comment.format();

		int res = 0;
		try {
			Date currentDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String date = format.format(currentDate);

			PreparedStatement addComment = con
					.prepareStatement("INSERT INTO `comment` (`post_id`, `parent_id`, `message`, `name`, `email`, `website`, `date`) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			addComment.setInt(1, comment.getPostId());
			if (comment.getParentId() != null) {
				addComment.setInt(2, comment.getParentId());
			} else {
				addComment.setNull(2, Types.INTEGER);
			}
			addComment.setString(3, comment.getMessage());
			addComment.setString(4, comment.getName());
			addComment.setString(5, comment.getEmail());
			if (comment.getWebsite() != null
					&& !comment.getWebsite().equals("")) {
				addComment.setString(6, comment.getWebsite());
			} else {
				addComment.setNull(6, Types.VARCHAR);
			}
			addComment.setString(7, date);

			res = dbModule.executePreparedNonReturn(addComment);
			if (res == 0) {
				slf4jLogger.debug("Unable to add Comment");
			}
		} catch (Exception e) {
			slf4jLogger.debug("Unable to add Comment : " + e.getMessage());
		} finally {
			dbModule.closeConnection();
		}
		return res;
	}
}
