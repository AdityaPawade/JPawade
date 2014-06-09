package jpawade.modules;

import javax.servlet.http.Part;

import org.apache.commons.lang.StringEscapeUtils;

public class StaticModules {
	public static String dbFormat(String ip) {
		/*if (ip != null) {
			ip = ip.replace("'", "\\'").replace("\"", "\\\"");
			ip = ip.replace("\\\\\'", "\\'").replace("\\\\\"", "\\\"");
		}*/
		return ip;
	}

	public static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	public static String escapeHtml(String ip) {
		if (ip != null) {
			ip = StringEscapeUtils.escapeHtml(ip);
		}
		return ip;
	}
}
