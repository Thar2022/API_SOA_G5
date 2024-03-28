package dao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MemberLevel;

import java.util.ArrayList;
import java.util.List;

public class MemberLevelDAO {

    public ArrayList<MemberLevel> getAllMemberLevels() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from MemberLevel");
        ArrayList<MemberLevel> memberLevels = (ArrayList<MemberLevel>) query.list();
        session.close();
        return memberLevels;
    }

    public MemberLevel getMemberLevelById(int id) {
        Session session = SessionUtil.getSession();
        MemberLevel memberLevel = session.get(MemberLevel.class, id);
        session.close();
        return memberLevel;
    }

    public void addMemberLevel(MemberLevel memberLevel) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(memberLevel);
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

    public void updateMemberLevel(MemberLevel memberLevel) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(memberLevel);
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

    public void deleteMemberLevel(MemberLevel memberLevel) {
        Session session = SessionUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(memberLevel);
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
