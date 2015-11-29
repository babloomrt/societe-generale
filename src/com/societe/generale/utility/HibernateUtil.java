package com.societe.generale.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static final EntityManagerFactory entityManagerFactory;
	static {
		try {
			 entityManagerFactory = Persistence.createEntityManagerFactory("societeGeneral");
		} catch (Throwable ex) {
			System.err.println("Initial entityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}


}
