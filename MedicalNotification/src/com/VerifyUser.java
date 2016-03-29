package com;

import javax.persistence.EntityManager;


public class VerifyUser {

	public static User VerifyPatientSignin(String email,String password) {

		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		User u= entityManager.find(User.class,email);
		if(u!=null && u.getPassword().equals(password))
			return u;
		else
			return null;
	}

	public static Doctor VerifyDoctorSignin(String email,String password) {

		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Doctor d= entityManager.find(com.Doctor.class, email);
		if (d!=null && d.getDoctorPassword().equals(password))
		return d;
		else
			return null;
	}

	public static User VerifyPatientSignUp(String email) {

		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		return entityManager.find(User.class,email);
	}

	public static Doctor VerifyDoctorSignUp(String email) {

		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		return entityManager.find(com.Doctor.class, email);
		
	}
	public static void createPatient(String name,String email,String password,String PhoneNo) {

		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setUserEmail(email);
		user.setPhoneNo(PhoneNo);
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();

	}
	public static void createDoctor(String name,String email,String password,String PhoneNo){
		System.out.println("createDoctor called");
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Doctor doctor=new Doctor();
		doctor.setDoctorEmail(email);
		doctor.setDoctorPassword(password);
		doctor.setName(name);
		doctor.setPhoneNo(PhoneNo);
		entityManager.persist(doctor);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}