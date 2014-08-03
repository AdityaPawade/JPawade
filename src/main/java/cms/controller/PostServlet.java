package cms.controller;

import cms.service.PostService;
import cms.service.SidebarService;
import jpawade.model.Archive;
import jpawade.model.BlogPost;
import jpawade.model.Comment;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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

        Boolean multiPart = ServletFileUpload.isMultipartContent(req);
        PostService postService = new PostService();
        String func, post_id;
        Map<String, String> parameters = new HashMap<String, String>();
        InputStream imageStream = null;

        if(multiPart) {
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fName = item.getFieldName();
                        String fValue = item.getString();
                        parameters.put(fName, fValue);
                    } else {
                        String fileName = FilenameUtils.getName(item.getName());
                        parameters.put("imageName", fileName);
                        imageStream = item.getInputStream();
                    }
                }

            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            func = parameters.get("func");
            post_id = parameters.get("postId");
        } else {
            func = req.getParameter("func");
            post_id = req.getParameter("postId");
        }


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
            String title = parameters.get("title");
            String content = parameters.get("content");
            String desc = parameters.get("description");
            String tags = parameters.get("tags");
            String imgName = parameters.get("imageName");

            if (title.equals("") || content.equals("") || desc.equals("")) {
                resp.sendRedirect("Post?id=" + postId);
                return;
            }

            List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));

            BlogPost post = null;

            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-hh-mm-ss-");

            if (imgName != null && imgName.length() > 15)
                imgName = imgName.substring(0, 15);

            if (imgName != null && !imgName.equals("")) {
                imgName = format.format(date) + imgName;
                String server_path = getServletContext().getRealPath("/");
                String upload_directory = "/style/images/posts/";
                String complete_path = server_path + upload_directory;
                String filePath = complete_path + File.separator + imgName;
                OutputStream outputStream = new FileOutputStream(filePath);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = imageStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

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
