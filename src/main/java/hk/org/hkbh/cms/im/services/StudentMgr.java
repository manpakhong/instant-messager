package hk.org.hkbh.cms.im.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hk.org.hkbh.cms.im.models.eos.OpUserEo;
import hk.org.hkbh.cms.im.utils.HibernateUtils;
public class StudentMgr {
	public static void main(String[] args) {
		try {
			// 打开一个处理DOM4J数据的Session
			HibernateUtils hibernateUtils = HibernateUtils.getInstance();
			/*
			 * Session dom4jSession = hibernateUtils.getSession(); Transaction transaction =
			 * dom4jSession.beginTransaction(); Element studentElement = (Element)
			 * dom4jSession.get(OpUserEo.class, 5); transaction.commit();
			 * dom4jSession.close();
			 * 
			 * try { OutputFormat format = OutputFormat.createPrettyPrint(); XMLWriter
			 * writer = new XMLWriter(System.out, format); writer.write(studentElement); }
			 * catch (IOException e) { throw new RuntimeException(); }
			 */

			// 打开一个普通的session

			List<Predicate> predicateList = null;
			CriteriaBuilder builder = null;
			CriteriaQuery<OpUserEo> query = null;
			Query<OpUserEo> q = null;
			Root<OpUserEo> root = null;
			
			Session session = hibernateUtils.getSession();
			Transaction transaction1 = session.beginTransaction();
			
			builder = session.getCriteriaBuilder();
			query = builder.createQuery(OpUserEo.class);
			root = query.from(OpUserEo.class);
			predicateList = new ArrayList<Predicate>();
			Predicate predicate = builder.equal(root.get("id"), 1);
			predicateList.add(predicate);
			
			if (predicateList != null) {
				query.select(root).where(predicateList.toArray(new Predicate[] {}));
			}
			
			q = session.createQuery(query);
			List<OpUserEo> opCodeEoList = q.getResultList();
			
//			OpUserEo stu = (OpUserEo) session.get(OpUserEo.class, 5);
//			System.out.println("id=" + stu.getId());
//			System.out.println("name=" + stu.getName());
//			System.out.println("address=" + stu.getAddress());
//			System.out.println("age=" + stu.getAge());
			transaction1.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
