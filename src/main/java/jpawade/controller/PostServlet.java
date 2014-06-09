package jpawade.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpawade.model.Archive;
import jpawade.model.BlogPost;
import jpawade.model.Comment;
import jpawade.service.PostService;
import jpawade.service.SidebarService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = { "/Post/*" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(PostServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String string_id = req.getParameter("id");

		try {
			int post_id = Integer.parseInt(string_id);
			PostService postService = new PostService();

			BlogPost post = postService.getPost(post_id);
			if (post == null) {
				slf4jLogger.debug("No post received");
				throw new Exception();
			}
			req.setAttribute("post", post);

			SidebarService sidebarService = new SidebarService();
			ArrayList<BlogPost> topPosts = sidebarService.getTopPosts();
			ArrayList<String> tags = sidebarService.getTags();
			ArrayList<Archive> archives = sidebarService.getArchives();

			req.setAttribute("popular", topPosts);
			req.setAttribute("tags", tags);
			req.setAttribute("archives", archives);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("blog-post.jsp");
			dispatcher.forward(req, resp);

		} catch (NumberFormatException e) {
			slf4jLogger.debug("PostServlet get number Exception : "
					+ e.getMessage());
			resp.sendRedirect("Blog");
		} catch (Exception e) {
			slf4jLogger.debug("PostServlet get Exception : " + e.getMessage());
			resp.sendRedirect("Blog");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String website = req.getParameter("website");
		String message = req.getParameter("message");
		String parent_id = req.getParameter("replyId");
		String post_id = req.getParameter("postId");

		Integer parentId = null;
		try {
			parentId = Integer.parseInt(parent_id);
		} catch (NumberFormatException e) {
			parentId = null;
		}

		int postId = 0;
		try {
			postId = Integer.parseInt(post_id);
		} catch (NumberFormatException e) {
			resp.sendRedirect("Blog");
			return;
		}

		if (name.equals("") || email.equals("") || message.equals("")) {
			resp.sendRedirect("Post?id=" + postId);
			return;
		}

		Comment comment = new Comment(postId, parentId, message, name, email,
				website);

		PostService postService = new PostService();
		if (postService.addComment(comment) == 0) {
			resp.sendRedirect("Post?id=" + postId);
			return;
		}

		resp.sendRedirect("Post?id=" + postId);
		return;
	}

}
