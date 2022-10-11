package projectJDBC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import projectJDBC.entities.Student;

public class DAOstudent {
	
	public static void newStudent(Scanner scan) {
		try (Connection connection = DAOsql.newConnection()){
			Statement stmt = connection.createStatement();
			String sqlUse = "USE " + DAOsql.getDataBase();
			stmt.execute(sqlUse);
			
			System.out.println("New Student");
			System.out.print("Name:");
			String name = scan.nextLine();
			System.out.print("Phone:");
			String phone = scan.nextLine();
			System.out.println("\n");

			String sql = "INSERT INTO STUDENT VALUES (NULL,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.execute();

			pstmt.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void queryStudents() {
		try (Connection connection = DAOsql.newConnection()){
			Statement stmt = connection.createStatement();
			String sqlUse = "USE " + DAOsql.getDataBase();
			stmt.execute(sqlUse);
			
			String sql = "SELECT * FROM STUDENT";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();

			List<Student> studentQuery = new ArrayList<>();

			while (resultSet.next()) {
				studentQuery.add(new Student(resultSet.getInt("REGISTRATION"), resultSet.getString("NAME"),
						resultSet.getString("PHONE")));
			}

			for (Student query : studentQuery) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("Registration:" + query.getRegistration() + ", Name:" + query.getName() + ", Phone:"
						+ query.getPhone());
				System.out.println("-----------------------------------------------------------\n");
			}
			
			pstmt.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void queryOneStudent(String name) {
		try (Connection connection = DAOsql.newConnection()){
			Statement stmt = connection.createStatement();
			String sqlUse = "USE " + DAOsql.getDataBase();
			stmt.execute(sqlUse);
			
			String sql = "SELECT * FROM STUDENT WHERE NAME LIKE ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet resultSet = pstmt.executeQuery();

			List<Student> studentQuery = new ArrayList<>();

			while (resultSet.next()) {
				studentQuery.add(new Student(resultSet.getInt("REGISTRATION"), resultSet.getString("NAME"),
						resultSet.getString("PHONE")));
			}

			for (Student query : studentQuery) {
				System.out.println("Registration:" + query.getRegistration() + ", Name:" + query.getName() + ", Phone:"
						+ query.getPhone());
			}
			
			pstmt.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void updateStudent(Scanner scan) {
		try (Connection connection = DAOsql.newConnection()){
			System.out.println("-------------------------------");
			System.out.println("	    Update");
			System.out.println("-------------------------------");
			System.out.print("Student's old name:");
			String studentUpdate = scan.nextLine();
			System.out.print("New new name:");
			String newName = scan.nextLine();
			System.out.print("New new phone:");
			String newPhone = scan.nextLine();
			System.out.println("-------------------------------\n\n");
			
			Statement stmt = connection.createStatement();
			String sqlUse = "USE " + DAOsql.getDataBase();
			stmt.execute(sqlUse);
			
			String sqlStudent = "SELECT * FROM STUDENT WHERE NAME LIKE ?";
			PreparedStatement pstmt = connection.prepareStatement(sqlStudent);
			pstmt.setString(1, studentUpdate);
			ResultSet resultSet = pstmt.executeQuery();
			
			List<Student> studentQuery = new ArrayList<>();

			while (resultSet.next()) {
				studentQuery.add(new Student(resultSet.getInt("REGISTRATION"), resultSet.getString("NAME"),
						resultSet.getString("PHONE")));
			}
			
			Integer registration = 0;
			for (Student query : studentQuery) {
				registration = query.getRegistration();
			}
			
			String sqlUpdate = "UPDATE STUDENT SET NAME = ?,  PHONE = ? WHERE REGISTRATION = ?;";
			pstmt = connection.prepareStatement(sqlUpdate);
			pstmt.setString(1,newName);
			pstmt.setString(2,newPhone);
			pstmt.setInt(3, registration);
			pstmt.execute();
			
			stmt.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void removeStudent(String name) {
		try (Connection connection = DAOsql.newConnection()){
			
			Statement stmt = connection.createStatement();
			String sqlUse = "USE " + DAOsql.getDataBase();
			stmt.execute(sqlUse);
			
			String sql = "DELETE FROM STUDENT WHERE NAME LIKE ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.execute();
			
			System.out.println("Sucess!");
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}