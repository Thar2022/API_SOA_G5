package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.json.JSONArray;
import org.json.JSONObject;

import model.ClassExercise;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ClassExcerciseDAO {

	public ArrayList<ClassExercise> getAllClassExercises() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from ClassExercise");
		ArrayList<ClassExercise> classExercises = (ArrayList<ClassExercise>) query.list();
		session.close();
		return classExercises;
	}

	public ClassExercise getClassExerciseById(int id) {
		Session session = SessionUtil.getSession();
		ClassExercise classExercise = session.get(ClassExercise.class, id);
		session.close();
		return classExercise;
	}

	public boolean addClassExercise(ClassExercise classExercise) {
		boolean status = true;
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(classExercise);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return status;

	}
	public boolean updateClassExercise(int id, ClassExercise classExercise) {
		boolean status = true;
		Session session = SessionUtil.getSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ClassExercise classExUpdate = session.get(ClassExercise.class, id);
			System.out.println(classExUpdate.getName());
			classExUpdate.setName(classExercise.getName());
			classExUpdate.setDetailClass(classExercise.getDetailClass());
			classExUpdate.setTrainer(classExercise.getTrainer());
			classExUpdate.setCount(classExercise.getCount());
			session.update(classExUpdate);
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

//	public boolean updateClassExercise(ClassExercise classExercise) {
//		boolean status = true;
//		Session session = SessionUtil.getSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			session.update(classExercise);
//			tx.commit();
//		} catch (RuntimeException e) {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//			throw e;
//		} finally {
//			session.close();
//		}
//		return status;
//	}
	
	public boolean deleteClassExercise(int ex_id) {
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			Query query =session.createSQLQuery("DELETE FROM class_exercise WHERE id_class =" + Integer.toString(ex_id) );
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

//	public boolean deleteClassExercise(ClassExercise classExercise) {
//		boolean status = true;
//		Session session = SessionUtil.getSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			session.delete(classExercise);
//			tx.commit();
//		} catch (RuntimeException e) {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//			throw e;
//		} finally {
//			session.close();
//		}
//	}
//	public String getClassEx() {
//		Session session = SessionUtil.getSession();
////		 Query query = session.createQuery("SELECT ct.date, ce.name FROM ClassTable as ct INNER JOIN ct.classExercise ce"); 
//		 Query query = session.createSQLQuery("SELECT * FROM class_exercise "); 
//			
//		ArrayList<Object[]> table = (ArrayList<Object[]>) query.list();
//		JSONArray jsonArray = new JSONArray();
//		for(Object[] result: table) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("name",result[0]);
//			jsonObject.put("detail",result[1]);
//			jsonArray.put(jsonObject);
//			System.out.println(result);
//		}
//		session.close();
//		return jsonArray.toString(); 
//	}
}
