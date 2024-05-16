package com.up.pet.util;

import java.sql.*;

import com.up.pet.AppConstants;

public class DBUtil {
	private static DBUtil db;

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private DBUtil() {

	}

	public static DBUtil getDBUtil() {
		if (db == null) {
			db = new DBUtil();
		}
		return db;
	}

	// Method to execute SQL update statements
	public int executeUpdate(String sql) {
		// Initialize result to -1 indicating failure
		int result = -1;

		// Check if a database connection is available
		if (getConn() == null) {
			// If no connection available, return -1 indicating failure
			return result;
		}

		try {
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			// Execute the SQL statement and store the result
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// If an SQL exception occurs, print the stack trace
			e.printStackTrace();
		}

		// Return the result of the SQL execution
		return result;
	}

	// Method to execute SQL update statements with parameters
	public int executeUpdate(String sql, Object[] obj) {
		// Initialize result to -1 indicating failure
		int result = -1;

		// Check if a database connection is available
		if (getConn() == null) {
			// If no connection available, return -1 indicating failure
			return result;
		}

		try {
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			// Set parameters for the prepared statement
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}

			// Execute the SQL statement and store the result
			result = ps.executeUpdate();

			// Close the connection
			close();
		} catch (SQLException e) {
			// If an SQL exception occurs, print the stack trace
			e.printStackTrace();
		}

		// Return the result of the SQL execution
		return result;
	}

	// Method to execute SQL queries without parameters
	public ResultSet executeQuery(String sql) {
		// Check if a database connection is available
		if (getConn() == null) {
			// If no connection available, return null
			return null;
		}

		try {
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			// Execute the SQL query and store the result set
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// If an SQL exception occurs, print the stack trace
			e.printStackTrace();
		}

		// Return the result set
		return rs;
	}

	// Method to execute SQL queries with parameters
	public ResultSet executeQuery(String sql, Object[] obj) {
		// Check if a database connection is available
		if (getConn() == null) {
			// If no connection available, return null
			return null;
		}

		try {
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			// Set parameters for the prepared statement
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}

			// Execute the SQL query and store the result set
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// If an SQL exception occurs, print the stack trace
			e.printStackTrace();
		}

		// Return the result set
		return rs;
	}

	// Method to execute SQL statements
	public boolean execute(String sql) {
		// Check if a database connection is available
		if (getConn() == null) {
			// If no connection available, return false
			return false;
		}
		try {
			// Create a statement object
			Statement statement = conn.createStatement();

			// Execute the SQL statement
			statement.execute(sql);

			// Close the statement
			statement.close();

			// Return true to indicate successful execution
			return true;
		} catch (SQLException e) {
			// If an SQL exception occurs, print the stack trace
			// Suppress printing the stack trace as it's commented out
			// e.printStackTrace();

			// Return false to indicate failure
			return false;
		}
	}

	// Method to get a database connection
	private Connection getConn() {
		try {
			// Check if the connection is null or closed
			if (conn == null || conn.isClosed()) {
				// Load the JDBC driver and establish a new connection
				Class.forName(AppConstants.JDBC_DRIVER);
				conn = DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME,
						com.up.pet.AppConstants.JDBC_PASSWORD);
			}
		} catch (ClassNotFoundException e) {
			// Handle ClassNotFoundException by printing a message
			System.out.println("jdbc driver is not found.");

			// Print the stack trace
			e.printStackTrace();
		} catch (SQLException e) {
			// Handle SQLException by printing the stack trace
			e.printStackTrace();
		}
		// Return the connection
		return conn;
	}

	// Method to close the result set, prepared statement, and connection
	public void close() {
		try {
			// Close the result set if it's not null
			if (rs != null) {
				rs.close();
			}
			// Close the prepared statement if it's not null
			if (ps != null) {
				ps.close();
			}
			// Close the connection if it's not null
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// Handle SQLException by printing the stack trace
			e.printStackTrace();
		}
	}

}
