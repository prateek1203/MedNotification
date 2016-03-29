package com;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AllPatient {
	public static List<User> getPatientList(String PatientName) {
		EntityManager entityManager = CreateEntity.getEntity();
		TypedQuery<User> query = entityManager.createQuery("select  c from User c where c.name like '%" + PatientName
				+ "%' OR c.userEmail like '%" + PatientName + "%'", User.class).setMaxResults(2);
		List<User> allpatients = (List<User>) query.getResultList();
		return allpatients;
	}
}
