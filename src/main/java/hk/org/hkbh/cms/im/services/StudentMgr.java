package hk.org.hkbh.cms.im.services;
import java.io.IOException;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hk.org.hkbh.cms.im.models.eos.Student;
import hk.org.hkbh.cms.im.utils.HibernateUtils;
public class StudentMgr {
	public static void main(String[] args) {
		//打开一个处理DOM4J数据的Session
		Session dom4jSession = HibernateUtils.getSessionFactory().openSession().getSession(EntityMode.DOM4J);
		Transaction transaction = dom4jSession.beginTransaction();
		Element studentElement = (Element) dom4jSession.get(Student.class, 5);
		transaction.commit();
		dom4jSession.close();
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out,format);
			writer.write(studentElement);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		//打开一个普通的session
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction1 = session.beginTransaction();
		Student stu = (Student) session.get(Student.class, 5);
		System.out.println("id="+stu.getId());
		System.out.println("name="+stu.getName());
		System.out.println("address="+stu.getAddress());
		System.out.println("age="+stu.getAge());
		transaction1.commit();
		session.close();
	}
}
