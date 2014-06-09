package cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jpawade.model.Archive;
import jpawade.model.BlogPost;
import jpawade.model.Comment;
import jpawade.modules.StaticModules;
import cms.service.PostService;
import cms.service.SidebarService;

@MultipartConfig
@WebServlet(urlPatterns = { "/cms/Post/*" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private static final Logger slf4jLogger = LoggerFactory
	// .getLogger(PostServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String string_id = req.getParameter("id");

		HttpSession session = req.getSession();
		if (session.getAttribute("login") == null
				|| !session.getAttribute("login").toString().equals("admin")) {
			resp.sendRedirect("Login");
			return;
		}

		PostService postService = new PostService();
		try {
			int post_id = Integer.parseInt(string_id);
			BlogPost post = postService.getPost(post_id);
			req.setAttribute("post", post);
		} catch (NumberFormatException e) {
		}

		try {
			SidebarService sidebarService = new SidebarService();
			ArrayList<BlogPost> topPosts = sidebarService.getTopPosts();
			ArrayList<String> allTags = sidebarService.getTags();
			ArrayList<Archive> archives = sidebarService.getArchives();

			req.setAttribute("popular", topPosts);
			req.setAttribute("tags", allTags);
			req.setAttribute("archives", archives);

			ArrayList<String> tags = postService.getTags();
			req.setAttribute("tags", tags);
		} catch (Exception e) {
		}
		RequestDispatcher dispatcher = req
				.getRequestDispatcher("blog-post.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String func = req.getParameter("func");
		String post_id = req.getParameter("postId");

		PostService postService = new PostService();
		int postId = 0;
		try {
			postId = Integer.parseInt(post_id);
		} catch (NumberFormatException e) {
			resp.sendRedirect("Blog");
			return;
		}

		if (func.equals("comment")) {

			String message = req.getParameter("message");
			String parent_id = req.getParameter("replyId");

			Integer parentId = null;
			try {
				parentId = Integer.parseInt(parent_id);
			} catch (NumberFormatException e) {
				parentId = null;
			}

			if (message.equals("")) {
				resp.sendRedirect("Post?id=" + postId);
				return;
			}

			Comment comment = new Comment(postId, parentId, message,
					"Aditya Pawade ( Admin )", "adityap174@gmail.com", null);

			if (postService.addComment(comment) == 0) {
				resp.sendRedirect("Post?id=" + postId);
				return;
			}

			resp.sendRedirect("Post?id=" + postId);
			return;
		} else if (func.equals("post")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String desc = req.getParameter("description");
			String tags = req.getParameter("tags");

			if (title.equals("") || content.equals("") || desc.equals("")) {
				resp.sendRedirect("Post?id=" + postId);
				return;
			}

			tags = tags.replace(", ", ",");
			tags = tags.replace(" ,", ",");
			tags = tags.trim();
			String[] postTags = tags.split(",");
			ArrayList<String> tagList = new ArrayList<String>();
			for (String tag : postTags) {
				tagList.add(tag);
			}

			Part image = req.getPart("image");

			BlogPost post = null;

			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-hh-mm-ss-");
			String imgName = StaticModules.getFilename(image);

			if (imgName != null && imgName.length() > 15)
				imgName = imgName.substring(0, 15);

			if (imgName != null && !imgName.equals("")) {
				imgName = format.format(date) + imgName;
				String server_path = getServletContext().getRealPath("/");
				String upload_directory = "style/images/posts/";
				String complete_path = server_path + upload_directory;

				image.write(complete_path + File.separator + imgName);
				String dbPath = "posts/" + imgName;
				post = new BlogPost(postId, new Date(), title, dbPath, content,
						desc, 0, tagList);
			} else {
				post = new BlogPost(postId, new Date(), title, null, content,
						desc, 0, tagList);
			}

			int resAddPost = postService.modifyPost(post);
			if (resAddPost == 0) {
				resp.sendRedirect("Post");
				return;
			} else {
				resp.sendRedirect("Post?id=" + resAddPost);
				return;
			}
		} else if (func.equals("delComment")) {
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			try {
				postService.removeComment(postId);
				resp.getWriter().write("OK");
			} catch (Exception e) {
				resp.getWriter().write(e.getMessage());
			}
		} else if (func.equals("delPost")) {
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");

			int res = postService.removePost(postId);
			if (res == 1)
				resp.getWriter().write("OK");
			else
				resp.getWriter().write("Error Occured");
			return;
		}
	}
}
