package dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public void addUser(User user) {
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // ปล่อยให้ผู้เรียกจัดการข้อผิดพลาด
		} finally {
			session.close();
		}
	}

	// อัปเดตข้อมูลผู้ใช้
	public void updateUser(User user) {
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // ปล่อยให้ผู้เรียกจัดการข้อผิดพลาด
		} finally {
			session.close();
		}
	}

	// ลบข้อมูลผู้ใช้
	public void deleteUser(User user) {
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e; // ปล่อยให้ผู้เรียกจัดการข้อผิดพลาด
		} finally {
			session.close();
		}
	}

}