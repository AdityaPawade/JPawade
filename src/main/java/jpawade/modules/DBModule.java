package jpawade.modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBModule {

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(DBModule.class);
	private Statement statement = null;
	private Connection con = null;

	public Connection createConnection() {
		String DB_DRIVER = "com.mysql.jdbc.Driver";
		String DB_CONNECTION = "jdbc:mysql://localhost";
		String DB_USER = "root";
		String DB_PASSWORD = "";
		String selectDB = "use jpawade";

		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			statement = con.createStatement();
			statement.execute(selectDB);
		} catch (SQLException e) {
			slf4jLogger.debug("Sql exception during GetDBConnection : "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			slf4jLogger.debug("class not found : " + e.getMessage());
		}

		if (con == null) {
			slf4jLogger.debug("Connection Failed");
		}
		if (statement == null) {
			slf4jLogger.debug("Statement Exec Failed");
		}

		return con;
	}

	public void closeConnection() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during CloseConnection : "
					+ e.getMessage());
		}
	}

	public ResultSet executeReturn(String query) {
		ResultSet rs = null;
		if (statement == null || con == null)
			return null;
		try {
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during Execute Result : "
					+ e.getMessage());
		} catch (Exception e) {
			slf4jLogger.debug("Exception during Execute Result : "
					+ e.getMessage());
		}
		return rs;
	}

	public ResultSet getGeneratedKeys() {
		ResultSet rs = null;
		if (statement == null || con == null)
			return null;
		try {
			rs = statement.getGeneratedKeys();
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during getGeneratedKeys : "
					+ e.getMessage());
		} catch (Exception e) {
			slf4jLogger.debug("Exception during getGeneratedKeys : "
					+ e.getMessage());
		}
		return rs;
	}

	public int executeNonReturn(String query) {
		try {
			statement.execute(query, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during Execute Non Result : "
					+ e.getMessage());
			return 0;
		}
		return 1;
	}

	public ResultSet getPreparedGeneratedKeys(PreparedStatement query) {
		ResultSet rs = null;
		if (statement == null || con == null)
			return null;
		try {
			rs = query.getGeneratedKeys();
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during getGeneratedKeys : "
					+ e.getMessage());
		} catch (Exception e) {
			slf4jLogger.debug("Exception during getGeneratedKeys : "
					+ e.getMessage());
		}
		return rs;
	}

	public ResultSet executePreparedReturn(PreparedStatement query) {
		ResultSet rs = null;
		if (statement == null || con == null)
			return null;
		try {
			rs = query.executeQuery();
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during Execute Result : "
					+ e.getMessage());
		} catch (Exception e) {
			slf4jLogger.debug("Exception during Execute Result : "
					+ e.getMessage());
		}
		return rs;
	}

	public int executePreparedNonReturn(PreparedStatement query) {
		if (statement == null || con == null)
			return 0;
		try {
			query.execute();
		} catch (SQLException e) {
			slf4jLogger.debug("Sql Exception during Execute Non Result : "
					+ e.getMessage());
			return 0;
		}
		return 1;
	}
}
