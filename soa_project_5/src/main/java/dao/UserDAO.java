package dao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import model.User;

public class UserDAO {

	public ArrayList<User> getAllUsers() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from User");
		ArrayList<User> Users = (ArrayList<User>) query.list();
		session.close();
		return Users;
	}

	public ArrayList<User> getUserByName(String name) {
		ArrayList<User> cusOut = new ArrayList();
		List<User> cusList = getAllUsers();
		for (User cus : cusList) {
//			System.out.println(cus.getName());
			if (cus.getName().equalsIgnoreCase(name)) {
				cusOut.add(cus);
			}
		}

		return cusOut;
	}

	// เพิ่มข้อมูลผู้ใช้
	public boolean addUser(User user) {
		boolean status = true;
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			status = false;
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // ปล่อยให้ผู้เรียกจัดการข้อผิดพลาด
		} finally {
			session.close();
		}
		return status;
	}

	// อัปเดตข้อมูลผู้ใช้
	public boolean updateUser(int idUser, User user) {
		boolean status = true;
		Session session = SessionUtil.getSession();
		System.out.println(user.getTel());
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User userUpdate = session.get(User.class, idUser);
			System.out.println(userUpdate.getName());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setName(user.getName());
			userUpdate.setPassword(user.getPassword());
			userUpdate.setRole(user.getRole());
			userUpdate.setSurname(user.getSurname());
			userUpdate.setTel(user.getTel());
			session.update(userUpdate);
			tx.commit();
		} catch (RuntimeException e) {
			status = false;
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // ปล่อยให้ผู้เรียกจัดการข้อผิดพลาด
		} finally {
			session.close();
		}
		return status;
	}

	// ลบข้อมูลผู้ใช้
	public boolean deleteUser(int cus_id) {
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			Query query =session.createSQLQuery("DELETE FROM user WHERE id_user =" + Integer.toString(cus_id) );
			query.executeUpdate();
			//User cusDelete = session.get(User.class, cus_id);
			//session.delete(cusDelete);
			tx.commit();
			session.close();
		} catch (TransactionException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) { // ลบไม่ได้ null การใช้งานอาร์กิวเมนต์ที่เป็น null โดยที่ไม่ได้รับอนุญาต
			return false;
		}
		return true;
	}

	public String getClassTable() {
		Session session = SessionUtil.getSession();
//		 Query query = session.createQuery("SELECT ct.date, ce.name FROM ClassTable as ct INNER JOIN ct.classExercise ce"); 
		 Query query = session.createSQLQuery("SELECT  ce.name , ct.date FROM class_table as ct INNER JOIN class_exercise ce ON ct.id_class = ce.id_class"); 
			
		ArrayList<Object[]> table = (ArrayList<Object[]>) query.list();
		JSONArray jsonArray = new JSONArray();
		for(Object[] result: table) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("date",result[0]);
			jsonObject.put("name",result[1]);
			jsonArray.put(jsonObject);
			System.out.println(result);
		}
		session.close();
		return jsonArray.toString(); 
	}
}