package hk.org.hkbh.cms.im.utils;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import hk.org.hkbh.cms.im.daos.orm.interceptors.AuditInterceptor;

public class HibernateUtils {
	private final static Logger logger = Logger.getLogger(getClassName());
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static int currentSessionCount;
	private static HibernateUtils hibernateUtils;
	
	public void printStackTrace(String msg) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		if (stacks != null && stacks.length > 10) {
			stacks = (StackTraceElement[]) Arrays.copyOfRange(stacks, 0, 10);
		}
		msg = "currentSessionCount=" + currentSessionCount + ",currentThreadId: " + Thread.currentThread().getId() + ":" + msg;
		logger.debug(printStackTrace(msg, stacks));
	}
	private String printStackTrace(String message, StackTraceElement[] elems) {
		StringBuilder sb = new StringBuilder();
		if (message != null) {
			sb.append(message);
			sb.append("\n");
		}
		if (elems != null) {
			for (int k = 0; k < elems.length && k < 20; k++) {
				sb.append(elems[k].toString() + " (" + elems[k].getLineNumber() + "\n");
			}
		}
		return sb.toString();

	}
	public HibernateUtils() throws Exception {
		init();
	}
	
	public int getCurrentSessionCount() {
		return currentSessionCount;
	}
	
	public void returnSession(Session session) throws Exception {
		if (session != null) {
			session.close();
			session = null;
			deductCurrentSessionCount();
		}
	}
	public static HibernateUtils getInstance() throws Exception {
		try {

			if (hibernateUtils == null) {
				hibernateUtils = new HibernateUtils();
			}
		}
		catch (Exception e) {
			logger.error(getClassName() + ".getSessionFactory() - ", e);
			throw e;
		}
		return hibernateUtils;
	}
	
	private static String getClassName() {
		String className = null;
		try {
			className = HibernateUtils.class.getName();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return className;
	}
	private void init() throws Exception {
		try {
			initSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void deductCurrentSessionCount() {
		currentSessionCount--;
	}
	private void increaseCurrentSessionCount() {
		currentSessionCount++;
	}
	private void initSessionFactory() throws Exception {
		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();

				
				// ! ensure that every entity must have a primary key! otherwise, hibernate cannot be initialize 
				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
				
				// Create SessionFactory
				sessionFactory = sessionFactoryBuilder.build();

			} catch (Exception e) {
				logger.error(getClassName() + ".getSessionFactory() - ", e);
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
				throw e;
			}
		}

	}

	public Session getSession() throws Exception {
		Session session = null;
		try {
			session = getSession(false);
			

		} catch (Exception e) {
			logger.error(getClassName() + ".getSession() - ", e);
			throw e;
		}
		return session;
	}
	
	public Session getSession(Boolean isCreatedWithInterceptor) throws Exception {
		Session session = null;
		try {
			if (isCreatedWithInterceptor != null && isCreatedWithInterceptor) {
				AuditInterceptor auditInterceptor = new AuditInterceptor();
				session = sessionFactory.withOptions().interceptor(auditInterceptor).openSession();
			} else {
				session = sessionFactory.openSession();
				boolean isOpen = session.isOpen();
				if (!isOpen) {
					session = sessionFactory.openSession();
				}
			}
			increaseCurrentSessionCount();
			
			printStackTrace(getClassName() + ".getSession() - open ----> session" + session);
		} catch (Exception e) {
			logger.error(getClassName() + ".getSession() - isCreatedWithInterceptor=" +isCreatedWithInterceptor, e);
			throw e;
		}
		return session;
	}
	
	public void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
