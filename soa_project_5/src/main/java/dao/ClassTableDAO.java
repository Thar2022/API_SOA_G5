package dao;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.ClassTable;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

public class ClassTableDAO {
	@Transactional 
    public ArrayList<ClassTable> getAllClassTables() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from ClassTable");
        ArrayList<ClassTable> classTables = (ArrayList<ClassTable>) query.list();
        session.close(); 
        return classTables;
    }

    public ClassTable getClassTableById(int id) {
        Session session = SessionUtil.getSession();
        ClassTable classTable = session.get(ClassTable.class, id);
        session.close();
        return classTable;
    }

    public void addClassTable(ClassTable classTable) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(classTable);
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

    public void updateClassTable(ClassTable classTable) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(classTable);
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

    public void deleteClassTable(ClassTable classTable) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(classTable);
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
