
package com.up.pet.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.up.pet.DAO;
import com.up.pet.dao.AdminDAO;
import com.up.pet.dao.PetDAO;
import com.up.pet.util.DBUtil;

public abstract class BaseDAO {
	protected final DBUtil db = DBUtil.getDBUtil();
	protected ResultSet rs;
	private static BaseDAO baseDAO;

	public BaseDAO() {
		init();
	}

	private void init() {
		// buildAbilityDAO();
	}

	// protected abstract void buildAbilityDAO();

	public static synchronized BaseDAO getAbilityDAO(DAO dao) {
		switch (dao) {
			case AdminDAO:
				if (baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
					baseDAO = AdminDAO.getInstance();
				}
				break;
			case PetDAO:
				if (baseDAO == null || baseDAO.getClass() != PetDAO.class) {
					baseDAO = PetDAO.getInstance();
				}
				break;
			default:
				break;
		}
		return baseDAO;
	}

	protected void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
