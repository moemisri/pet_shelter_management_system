package com.up.pet.run;

import com.up.pet.util.DBUtil;
import com.up.pet.view.LoginView;

/**
 * Module Description: Main function
 */
public class Main {
	public static boolean initDB() {
		DBUtil dbUtil = DBUtil.getDBUtil();

		// Check if the database is initialized
		if (dbUtil.execute("SELECT 1 FROM admin")) {
			return true;
		}

		// Initialize the database
		// admin table
		dbUtil.execute("CREATE TABLE IF NOT EXISTS admin (id INT PRIMARY KEY, " +
				"name VARCHAR(32), " +
				"username VARCHAR(32), " +
				"password VARCHAR(32))");
		dbUtil.execute("INSERT INTO admin (id, name, username, password) VALUES (1, 'admin', 'moe', 'hithere!')");

		// pet table
		dbUtil.execute("CREATE TABLE IF NOT EXISTS pet (" +
				"id INT PRIMARY KEY, " +
				"no varchar(16)," +
				"name VARCHAR(32), " +
				"species VARCHAR(32), " +
				"breed VARCHAR(32), " +
				"age INT, " +
				"color VARCHAR(32), " +
				"owner VARCHAR(32), " +
				"contact VARCHAR(32))");
		return false;
	}

	public static void main(String[] args) {
		initDB();
		new LoginView();
		DBUtil.getDBUtil().close();
	}
}
