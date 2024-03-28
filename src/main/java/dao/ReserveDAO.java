package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.json.JSONArray;
import org.json.JSONObject;

import model.Reserve;

public class ReserveDAO {

    // เพิ่มข้อมูลการจอง
    public boolean addReserve(Reserve user) {
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
            throw e;
        } finally {
            session.close();
        }
        return true;
    }

    // ลบข้อมูลการจอง
    public boolean deleteReserve(int userId) {
    	try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			Query query =session.createSQLQuery("DELETE FROM reserve WHERE id_reserve =" + Integer.toString(userId) );
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

    // ดึงข้อมูลการจองทั้งหมด
    public String getAllReserves() {
        Session session = SessionUtil.getSession();
        Query query = session.createSQLQuery("SELECT r.id_reserve as id ,r.date as date , r.time as time ,ce.name as class , u.name as name , r.status as status \r\n"
        		+ "FROM reserve as r \r\n"
        		+ "INNER JOIN user as u ON r.id_user = u.id_user\r\n"
        		+ "INNER JOIN class_table as ct ON ct.id_classtable = r.id_classtable\r\n"
        		+ "INNER JOIN class_exercise as ce ON ce.id_class = ct.id_class");
        //ArrayList<Reserve> reserves = (ArrayList<Reserve>) query.list();
        ArrayList<Object[]> table = (ArrayList<Object[]>) query.list();
		JSONArray jsonArray = new JSONArray();
		for(Object[] result: table) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id",result[0]);
			jsonObject.put("date",result[1]);
			jsonObject.put("time",result[2]);
			jsonObject.put("class",result[3]);
			jsonObject.put("name",result[4]);
			jsonObject.put("status",result[5]);
			
			jsonArray.put(jsonObject);
			System.out.println(result);
		}
		session.close();
		return jsonArray.toString(); 

    }

}
