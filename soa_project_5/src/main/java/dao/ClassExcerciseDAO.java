package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.ClassExercise;

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

	public void addClassExercise(ClassExercise classExercise) {
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
	}

	public void updateClassExercise(ClassExercise classExercise) {
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(classExercise);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	public void deleteClassExercise(ClassExercise classExercise) {
		Session session = SessionUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(classExercise);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
}
