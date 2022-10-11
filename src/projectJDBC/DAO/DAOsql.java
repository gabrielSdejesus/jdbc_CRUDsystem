package projectJDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOsql {

	private static String dataBase;

	public static Connection newConnection() {
		try {
			final String stringConnection = "jdbc:mysql://localhost:3306/";
			final String stringUser = "root";
			final String stringPassword = "1234";

			return DriverManager.getConnection(stringConnection, stringUser, stringPassword);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void createDataBase(String dataBase) {
		try {
			DAOsql.dataBase = dataBase;
			Connection connection = DAOsql.newConnection();
			String sql = "CREATE DATABASE IF NOT EXISTS " + DAOsql.dataBase;
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void createTable() {
		try {
			Connection connection = DAOsql.newConnection();
			Statement stmt = connection.createStatement();
			String sqlUse = "USE " + dataBase;
			stmt.execute(sqlUse);
			String sql = "CREATE TABLE IF NOT EXISTS STUDENT" + " (REGISTRATION INT AUTO_INCREMENT PRIMARY KEY,"
					+ "NAME VARCHAR(30) NOT NULL," + "PHONE VARCHAR(15));";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getDataBase() {
		return dataBase;
	}

	public static void setDataBase(String dataBase) {
		DAOsql.dataBase = dataBase;
	}

}
