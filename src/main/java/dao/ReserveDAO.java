package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Reserve; 

public class ReserveDAO {

    // เพิ่มข้อมูลการจอง
    public void addReserve(Reserve reserve) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(reserve);
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

    // อัปเดตข้อมูลการจอง
    public void updateReserve(Reserve reserve) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(reserve);
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

    // ลบข้อมูลการจอง
    public void deleteReserve(Reserve reserve) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(reserve);
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

    // ดึงข้อมูลการจองทั้งหมด
    public ArrayList<Reserve> getAllReserves() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Reserve");
        ArrayList<Reserve> reserves = (ArrayList<Reserve>) query.list();
        session.close();
        return reserves;
    }

}
