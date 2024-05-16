package com.up.pet;

/**
 * Module Description: Constants
 *
 */
public class AppConstants {
	// jdbc
	public static final String JDBC_URL = "jdbc:sqlite:test.db";
	public static final String JDBC_USERNAME = "test";
	public static final String JDBC_PASSWORD = "test";
	public static final String JDBC_DRIVER = "org.sqlite.JDBC";

	// pet fields
	public static final String PET_NAME = "Name";
	public static final String PET_NO = "ID";
	public static final String PET_SPECIES = "Species";
	public static final String PET_BREED = "Breed";
	public static final String PET_AGE = "Age";
	public static final String PET_COLOR = "Color";
	public static final String PET_OWNER = "Owner";
	public static final String PET_CONTACT = "Contact";

	// login view
	public static final String LOGIN_TITLE = "Login";
	public static final String LOGIN_USERNAME = "Username";
	public static final String LOGIN_PASSWORD = "Password";
	public static final String LOGIN = "Login";
	public static final String RESET = "Reset";

	// main view
	public static final String MAINVIEW_TITLE = "Pet Information Management System";
	public static final String MAINVIEW_PAGENUM_JLABEL_DI = "Page ";
	public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/99";
	public static final String MAINVIEW_FIND_JLABEL = "Search Results";
	public static final String MAINVIEW_FIRST = "First";
	public static final String MAINVIEW_LAST = "Last";
	public static final String MAINVIEW_PRE = "Previous";
	public static final String MAINVIEW_NEXT = "Next";
	public static final String PARAM_FIND_CONDITION = "";
	public static final String PARAM_FIND = "Search";
	public static final String PARAM_ADD = "Add";
	public static final String PARAM_DELETE = "Delete";
	public static final String PARAM_UPDATE = "Update";

	// add view
	public static final String ADDVIEW_TITLE = "Add Pet Information";
	public static final String ADDVIEW_ADDBUTTON = "Add";
	public static final String EXITBUTTON = "Exit";

	// delete view
	public static final String DELETEVIEW_TITLE = "Delete Pet Information";
	public static final String DELETEVIEW_DELETEBUTTON = "Delete";

	// update view
	public static final String UPDATEVIEW_TITLE = "Update Pet Information";
	public static final String UPDATEVIEW_UPDATEBUTTON = "Update";

}
