package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateEntity {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Employee");
	
	public static EntityManager getEntity(){
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager;
	}
}
