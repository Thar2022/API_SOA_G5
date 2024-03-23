package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.OrderLevel; 

public class OrderLevelDAO {

    public ArrayList<OrderLevel> getAllOrderLevels() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from OrderLevel");
        ArrayList<OrderLevel> orderLevels = (ArrayList<OrderLevel>) query.list();
        session.close();
        return orderLevels;
    }

    public void addOrderLevel(OrderLevel orderLevel) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(orderLevel);
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

    public void updateOrderLevel(OrderLevel orderLevel) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(orderLevel);
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

    public void deleteOrderLevel(OrderLevel orderLevel) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(orderLevel);
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
