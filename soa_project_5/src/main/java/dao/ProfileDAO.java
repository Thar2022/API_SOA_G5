
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

public class ProfileDAO {

	

	// เพิ่มข้อมูลผู้ใช้
	public boolean addProfile(User user) {
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
	public boolean updateProfile(int idUser, User user) {
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
	public boolean deleteProfile(int cus_id) {
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			User cusDelete = session.get(User.class, cus_id);
			session.delete(cusDelete);
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

	public String getProfile() {
		Session session = SessionUtil.getSession();
//		 Query query = session.createQuery("SELECT ct.date, ce.name FROM ClassTable as ct INNER JOIN ct.classExercise ce"); 
		 Query query = session.createSQLQuery("SELECT user.name,user.surname,user.tel,user.email,member_level.leveltype,order_level.date_order,order_level.date_exp,order_level.note,order_level.status FROM `user`\r\n"
		 		+ "INNER JOIN order_level ON order_level.id_user = user.id_user\r\n"
		 		+ "INNER JOIN member_level ON member_level.id_member_level = order_level.id_member_level;"); 
			
		ArrayList<Object[]> table = (ArrayList<Object[]>) query.list();
		JSONArray jsonArray = new JSONArray();
		for(Object[] result: table) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name",result[0]);
			jsonObject.put("surname",result[1]);
			jsonObject.put("tel",result[2]);
			jsonObject.put("email",result[3]);
			jsonObject.put("leveltype",result[4]);
			jsonObject.put("date_order",result[5]);
			jsonObject.put("date_exp",result[6]);
			jsonObject.put("note",result[7]);
			jsonObject.put("status",result[8]);
			jsonArray.put(jsonObject);
			System.out.println(result);
		}
		session.close();
		return jsonArray.toString(); 
}
	}