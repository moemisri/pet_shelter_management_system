package com.up.pet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.up.pet.base.BaseDAO;
import com.up.pet.model.Pet;

public class PetDAO extends BaseDAO {
	private final int fieldNum = 9;
	private final int showNum = 15;
	private static PetDAO sd = null;

	public static synchronized PetDAO getInstance() {
		if (sd == null) {
			sd = new PetDAO();
		}
		return sd;
	}

	// update
	public boolean update(Pet pet) {
		boolean result = false;
		if (pet == null) {
			return result;
		}
		try {
			// check
			if (queryByno(pet.getno()) == 0) {
				return result;
			}
			// update
			String sql = "update pet set species=?,breed=?,age=?,owner=?,contact=?,color=? where name=? and no=?";
			String[] param = { pet.getspecies(), pet.getbreed(), pet.getage(), pet.getowner(), pet.getcontact(),
					pet.getcolor(),
					pet.getName(), pet.getno() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// delete
	public boolean delete(Pet pet) {
		boolean result = false;
		if (pet == null) {
			return result;
		}
		String sql = "delete from pet where name=? and no=?";
		String[] param = { pet.getName(), pet.getno() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add
	public boolean add(Pet pet) {
		boolean result = false;
		if (pet == null) {
			return result;
		}
		try {
			// check
			if (queryByno(pet.getno()) == 1) {
				return result;
			}
			// insert
			String sql = "insert into pet(name,no,species,breed,color,age,owner,contact) values(?,?,?,?,?,?,?,?)";
			String[] param = { pet.getName(), pet.getno(), pet.getspecies(), pet.getbreed(), pet.getcolor(),
					pet.getage(), pet.getowner(), pet.getcontact() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// query by name
	public String[][] queryByName(String name) {
		String[][] result = null;
		if (name.length() < 0) {
			return result;
		}
		List<Pet> pets = new ArrayList<Pet>();
		int i = 0;
		String sql = "select * from pet where name like ?";
		String[] param = { "%" + name + "%" };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, pets, i);
				i++;
			}
			if (pets.size() > 0) {
				result = new String[pets.size()][fieldNum];
				for (int j = 0; j < pets.size(); j++) {
					buildResult(result, pets, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// query
	public String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		List<Pet> pets = new ArrayList<Pet>();
		int i = 0;
		int beginNum = (pageNum - 1) * showNum;
		String sql = "select * from pet limit ?,?";
		Integer[] param = { beginNum, showNum };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, pets, i);
				i++;
			}
			if (pets.size() > 0) {
				result = new String[pets.size()][fieldNum];
				for (int j = 0; j < pets.size(); j++) {
					buildResult(result, pets, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	private void buildList(ResultSet rs, List<Pet> list, int i) throws SQLException {
		Pet pet = new Pet();
		pet.setId(i + 1);
		pet.setName(rs.getString("name"));
		pet.setbreed(rs.getString("breed"));
		pet.setage(rs.getString("age"));
		pet.setowner(rs.getString("owner"));
		pet.setcolor(rs.getString("color"));
		pet.setspecies(rs.getString("species"));
		pet.setno(rs.getString("no"));
		pet.setcontact(rs.getString("contact"));
		list.add(pet);
	}

	private void buildResult(String[][] result, List<Pet> pets, int j) {
		Pet pet = pets.get(j);
		result[j][0] = String.valueOf(pet.getId());
		result[j][1] = pet.getName();
		result[j][2] = pet.getno();
		result[j][3] = pet.getspecies();
		result[j][4] = pet.getbreed();
		result[j][5] = pet.getcolor();
		result[j][6] = pet.getage();
		result[j][7] = pet.getowner();
		result[j][8] = pet.getcontact();
	}

	// query by no
	private int queryByno(String no) throws SQLException {
		int result = 0;
		if ("".equals(no) || no == null) {
			return result;
		}
		String checkSql = "select * from pet where no=?";
		String[] checkParam = { no };
		rs = db.executeQuery(checkSql, checkParam);
		if (rs.next()) {
			result = 1;
		}
		return result;
	}

}
