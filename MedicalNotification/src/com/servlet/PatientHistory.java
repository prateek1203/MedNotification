package com.servlet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.CreateEntity;
import com.Doctor;
import com.Notification;
import com.PatientMedicine;
import com.Prescription;
import com.User;

public class PatientHistory {

	public static List<Prescription> getPatientId(String userEmail, String doctorEmail) {
		System.out.println(userEmail+""+doctorEmail);
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Doctor doctor = entityManager.find(Doctor.class, doctorEmail);
		User user = entityManager.find(User.class,userEmail);
		try {

			TypedQuery<Prescription> prescription = entityManager.createQuery(
					"select pres from Prescription as pres where pres.user=:user AND pres.doctor=:doctor",
					Prescription.class);
			prescription.setParameter("user", user);
			prescription.setParameter("doctor", doctor);
			List<Prescription> id = prescription.getResultList();

			return id;
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer addNewPrescription(String doctorEmail, String message, String userEmail) {

		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Doctor doctor = entityManager.find(Doctor.class, doctorEmail);
		User user = entityManager.find(User.class, userEmail);
		Prescription prescription = new Prescription();
		prescription.setDoctor(doctor);
		prescription.setPrescriptionDetails(message);
		prescription.setUser(user);
		entityManager.persist(prescription);
		entityManager.getTransaction().commit();
		return prescription.getPrescriptioId();
	}

	public static String updatePatientInfo(String userEmail, String password, String oldPass) {
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		try {
			User user = entityManager.find(User.class, userEmail);
			if (user.getPassword().equals(oldPass)) {
				// System.out.println(user.getPassword());
				user.setPassword(password);
				entityManager.getTransaction().commit();
				return " Password Changed.";
			} else {
				return "old Pass is Incorrect.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Something Went wrong.";
		} finally {
			entityManager.close();
		}
	}

	public static List<PatientMedicine> getMedicineInfo(Integer prescriptioId) {
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Prescription prescription = entityManager.find(Prescription.class, prescriptioId);
		try {
			TypedQuery<PatientMedicine> medicine = entityManager.createQuery(
					"select med from PatientMedicine med where med.prescription=:prescription", PatientMedicine.class);
			medicine.setParameter("prescription", prescription);
			List<PatientMedicine> medicineInfo = medicine.getResultList();

			return medicineInfo;
		} catch (Exception e) {
			System.out.println("Finaldate");
			return null;
		}
	}

	public static String getPrescriptionDetails(int id) {
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Prescription prescription = entityManager.find(Prescription.class, id);
		return prescription.getPrescriptionDetails();
	}

	public static String setMedicine(int prescriptionId, String medicineName, int doseInADay, int doseDay,
			int gapInMedicine, String startDate, int checkUpTime) throws ParseException {
		System.out.println("setMedicine called" + startDate);
		Date finalDate = new Date();
		/*String str1[] = startDate.split("-", 3);
		String date2 = str1[3] + "-" + str1[1] + "-" + str1[2];*/
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = (Date) formatter.parse(startDate);
		String str[] = date1.toString().split(" ", 6);
		String date = str[1] + "-" + str[2] + "-" + str[5];
		/*
		 * if (gapInMedicine == 0) { Calendar cal = new GregorianCalendar(2016,
		 * date1.getMonth(), date1.getDate()); cal.add(Calendar.DAY_OF_MONTH,
		 * (doseDay - 1)); finalDate = cal.getTime(); } if (gapInMedicine == 1)
		 * { Calendar cal = new GregorianCalendar(2016, date1.getMonth(),
		 * date1.getDate()); cal.add(Calendar.DAY_OF_MONTH, (doseDay * 2 - 2));
		 * finalDate = cal.getTime(); }
		 */
		
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Prescription prescription = entityManager.find(Prescription.class, prescriptionId);
		PatientMedicine patientMedicine = new PatientMedicine();
		patientMedicine.setDoseDay(doseDay);
		patientMedicine.setDoseInADay(doseInADay);
		patientMedicine.setGapInMedicine(gapInMedicine);
		patientMedicine.setMedicineName(medicineName);
		patientMedicine.setPrescription(prescription);
		patientMedicine.setStartDate(date1);
		patientMedicine.setFinaldate(finalDate);
		entityManager.persist(patientMedicine);
		entityManager.getTransaction().commit();

		finalDate = PatientHistory.timeTable(patientMedicine.getMedicineId(),doseInADay, doseDay, gapInMedicine,
				date1, checkUpTime);
		entityManager.getTransaction().begin();
		patientMedicine=entityManager.find(PatientMedicine.class,patientMedicine.getMedicineId());
		patientMedicine.setFinaldate(finalDate);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println(finalDate);
		return date;
	}

	public static void deleteMedicine(int PrescriptionId) {
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Prescription prescription = entityManager.find(Prescription.class, PrescriptionId);
		entityManager.remove(prescription);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static String deleteMedicineInfo(int prescriptionId, String MedicineName) {
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		Prescription prescription = entityManager.find(Prescription.class, prescriptionId);
		Query query = entityManager.createQuery(
				"delete from PatientMedicine p where p.prescription=:prescription AND p.medicineName=:MedicineName");
		query.setParameter("prescription", prescription);
		query.setParameter("MedicineName", MedicineName);
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		return "Medicine Deleted";
	}

	public static List<Prescription> getAllpatientPrescription(String UserEmail) {
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		User user = entityManager.find(User.class, UserEmail);
		TypedQuery<Prescription> prescriptionId = entityManager
				.createQuery("select pres from Prescription pres where pres.user=:user", Prescription.class);
		prescriptionId.setParameter("user", user);
		List<Prescription> presId = prescriptionId.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return presId;
	}

	public static String updatePatientProfile(String patient, String userName, String userPhNo) {
		System.out.println("updatePatientprofile called");
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		User user = entityManager.find(User.class, patient);
		user.setName(userName);
		user.setPhoneNo(userPhNo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return "profile Updated Successfuly.";
	}

	public static String updatePatientDoesTiming(String breakFast, String lunch, String dinner, String userEmail) {
		System.out.println(" Inside updatePatientDoesTiming ()");
		EntityManager entityManager = CreateEntity.getEntity();
		entityManager.getTransaction().begin();
		User user = entityManager.find(User.class, userEmail);
		user.setBreakFast(breakFast);
		user.setLunch(lunch);
		user.setDinner(dinner);
		entityManager.getTransaction().commit();
		entityManager.close();
		return "Timing Updated Successfully";
	}

	/*
	 * public static String sendPassword(String userEmail, String usertype) {
	 * String to = userEmail;// change accordingly EntityManager entityManager =
	 * CreateEntity.getEntity(); // Get the session object final String
	 * SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; Properties props =
	 * System.getProperties(); props.setProperty("proxySet","true");
	 * props.setProperty("socksProxyHost","192.168.155.1");
	 * props.setProperty("socksProxyPort","1080");
	 * props.setProperty("mail.smtp.host", "smtp.gmail.com");
	 * props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	 * props.setProperty("mail.smtp.socketFactory.fallback", "false");
	 * props.setProperty("mail.smtp.port", "465");
	 * props.setProperty("mail.smtp.socketFactory.port", "465");
	 * props.put("mail.smtp.auth", "true"); props.put("mail.debug", "true");
	 * props.put("mail.store.protocol", "pop3");
	 * props.put("mail.transport.protocol", "smtp"); // proxy setting String
	 * proxyHost = "10.222.37.12"; // your host String proxyPost = "25"; // your
	 * port Properties proxySet = System.getProperties();
	 * proxySet.put("http.proxyPort", proxyPost); proxySet.put("http.proxyHost",
	 * proxyHost);
	 * 
	 * Session session = Session.getInstance(props, new
	 * javax.mail.Authenticator() { protected PasswordAuthentication
	 * getPasswordAuthentication() { return new
	 * PasswordAuthentication("prateekmishra06@gmail.com", "mcaprateek2013");//
	 * change // accordingly
	 * 
	 * } }); Random rnd = new Random(); int n = 100000 + rnd.nextInt(900000); //
	 * compose message try { MimeMessage message = new MimeMessage(session);
	 * message.setFrom(new InternetAddress("prateekmishra06@gmail.com"));//
	 * change // accordingly message.addRecipient(RecipientType.TO, new
	 * InternetAddress(to)); message.setSubject("Temporary Password");
	 * message.setText("Your temporary Password is:-"+n); // send message
	 * Transport.send(message);
	 * 
	 * // data Base changing code entityManager.getTransaction().begin(); if
	 * (usertype.equals("Patient")) { User user = entityManager.find(User.class,
	 * userEmail); user.setPassword("" + n);
	 * 
	 * } if (usertype.equals("Doctor")) { Doctor doctor =
	 * entityManager.find(Doctor.class, userEmail); doctor.setDoctorPassword(""
	 * + n); } entityManager.getTransaction().commit(); return
	 * "A temporary password has been send to provided email add";
	 * 
	 * } catch (MessagingException e) { e.printStackTrace(); return
	 * " Something went Wrong"; } catch (Exception e) { return
	 * "This is not a registered Email Add."; } finally { entityManager.close();
	 * }
	 * 
	 * }
	 */
	@SuppressWarnings("deprecation")
	public static Date timeTable(int medicineId,int doseInADay, int doseDay,
			int gapInMedicine, Date date1, int checkUpTime) throws ParseException {
		System.out.println("timeTable() called."+medicineId+""+checkUpTime);
		EntityManager entityManager = CreateEntity.getEntity();
		PatientMedicine patientMedicine=entityManager.find(PatientMedicine.class, medicineId);
		
		/*DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = (Date) formatter.parse(startDate);*/
		Date finalDate = date1;
		Date today=new Date();
		if(today.getDate()==finalDate.getDate()){
		if ((checkUpTime >= 10 && checkUpTime <= 13) && doseInADay == 3 && gapInMedicine == 0) {
			for (int i = 1; i <= doseDay; i++) {
				if (i == 1) {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(0);
					notification.setLunch(1);
					notification.setDinner(1);
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				} else {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(1);
					notification.setLunch(1);
					notification.setDinner(1);
					Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
					cal.add(Calendar.DAY_OF_MONTH, 1);
					finalDate = cal.getTime();
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				}
			}
			Notification notification = new Notification();
			entityManager.getTransaction().begin();
			notification.setPatientMedicine(patientMedicine);
			notification.setBreakfast(1);
			notification.setLunch(0);
			notification.setDinner(0);
			Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			finalDate = cal.getTime();
			notification.setMedDate(finalDate);
			entityManager.persist(notification);
			entityManager.getTransaction().commit();

		}
		if ((checkUpTime >= 13 && checkUpTime <= 19) && doseInADay == 3 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				if (i == 1) {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(0);
					notification.setLunch(0);
					notification.setDinner(1);
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				} else {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(1);
					notification.setLunch(1);
					notification.setDinner(1);
					Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
					cal.add(Calendar.DAY_OF_MONTH, 1);
					finalDate = cal.getTime();
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				}
			}
			Notification notification = new Notification();
			entityManager.getTransaction().begin();
			notification.setPatientMedicine(patientMedicine);
			notification.setBreakfast(1);
			notification.setLunch(1);
			notification.setDinner(0);
			Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			finalDate = cal.getTime();
			notification.setMedDate(finalDate);
			entityManager.persist(notification);
			entityManager.getTransaction().commit();

		}
		if ((checkUpTime >= 9 && checkUpTime <= 19) && doseInADay == 2 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				if (i == 1) {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(0);
					notification.setLunch(0);
					notification.setDinner(1);
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				} else {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(1);
					notification.setLunch(0);
					notification.setDinner(1);
					Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
					cal.add(Calendar.DAY_OF_MONTH, 1);
					finalDate = cal.getTime();
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				}
			}
			Notification notification = new Notification();
			entityManager.getTransaction().begin();
			notification.setPatientMedicine(patientMedicine);
			notification.setBreakfast(1);
			notification.setLunch(0);
			notification.setDinner(0);
			Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			finalDate = cal.getTime();
			notification.setMedDate(finalDate);
			entityManager.persist(notification);
			entityManager.getTransaction().commit();

		}
		if (doseInADay == 1 && gapInMedicine == 0) {
			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				entityManager.getTransaction().commit();
			}
		}
		if ((checkUpTime >= 10 && checkUpTime <= 13) && doseInADay == 3 && gapInMedicine == 1) {
			for (int i = 1; i <= doseDay; i++) {
				if (i == 1) {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(0);
					notification.setLunch(1);
					notification.setDinner(1);
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				} else {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(1);
					notification.setLunch(1);
					notification.setDinner(1);
					Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
					cal.add(Calendar.DAY_OF_MONTH, 2);
					finalDate = cal.getTime();
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				}
			}
			Notification notification = new Notification();
			entityManager.getTransaction().begin();
			notification.setPatientMedicine(patientMedicine);
			notification.setBreakfast(1);
			notification.setLunch(0);
			notification.setDinner(0);
			Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
			cal.add(Calendar.DAY_OF_MONTH, 2);
			finalDate = cal.getTime();
			notification.setMedDate(finalDate);
			entityManager.persist(notification);
			entityManager.getTransaction().commit();

		}
		if ((checkUpTime >= 13 && checkUpTime <= 19) && doseInADay == 3 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				if (i == 1) {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(0);
					notification.setLunch(0);
					notification.setDinner(1);
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				} else {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(1);
					notification.setLunch(1);
					notification.setDinner(1);
					Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
					cal.add(Calendar.DAY_OF_MONTH, 2);
					finalDate = cal.getTime();
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				}
			}
			Notification notification = new Notification();
			entityManager.getTransaction().begin();
			notification.setPatientMedicine(patientMedicine);
			notification.setBreakfast(1);
			notification.setLunch(1);
			notification.setDinner(0);
			Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
			cal.add(Calendar.DAY_OF_MONTH,2);
			finalDate = cal.getTime();
			notification.setMedDate(finalDate);
			entityManager.persist(notification);
			entityManager.getTransaction().commit();
		}
		if ((checkUpTime >= 9 && checkUpTime <= 19) && doseInADay == 2 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				if (i == 1) {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(0);
					notification.setLunch(0);
					notification.setDinner(1);
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				} else {
					Notification notification = new Notification();
					entityManager.getTransaction().begin();
					notification.setPatientMedicine(patientMedicine);
					notification.setBreakfast(1);
					notification.setLunch(0);
					notification.setDinner(1);
					Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
					cal.add(Calendar.DAY_OF_MONTH, 2);
					finalDate = cal.getTime();
					notification.setMedDate(finalDate);
					entityManager.persist(notification);
					entityManager.getTransaction().commit();
				}
			}
			Notification notification = new Notification();
			entityManager.getTransaction().begin();
			notification.setPatientMedicine(patientMedicine);
			notification.setBreakfast(1);
			notification.setLunch(0);
			notification.setDinner(0);
			Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
			cal.add(Calendar.DAY_OF_MONTH, 2);
			finalDate = cal.getTime();
			notification.setMedDate(finalDate);
			entityManager.persist(notification);
			entityManager.getTransaction().commit();

		}
		if (doseInADay == 1 && gapInMedicine == 1) {
			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 2);
				finalDate = cal.getTime();
			}
		}
		/*if (doseInADay == 1 && gapInMedicine == 0) {
			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
			}
		}*/
		if (checkUpTime <= 7 && doseInADay == 3 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(1);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();

			}
		}
		if (checkUpTime <= 7 && doseInADay == 3 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(1);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 2);
				finalDate = cal.getTime();

			}
		}
		if (checkUpTime <= 7 && doseInADay == 2 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();

			}
		}
		if (checkUpTime <= 7 && doseInADay == 1 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 2);
				finalDate = cal.getTime();

			}
		}
		if (checkUpTime >19 && doseInADay == 3 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(1);
				notification.setDinner(1);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
			}
		}
		if (checkUpTime >19 && doseInADay == 3 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(1);
				notification.setDinner(1);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal1 = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal1.getTime();
			}
		}
		if (checkUpTime >19 && doseInADay == 2 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(0);
				notification.setDinner(1);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
			}
		}
		if (checkUpTime >19 && doseInADay == 2 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(0);
				notification.setDinner(1);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal1 = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal1.getTime();
			}
		}
		if (checkUpTime >19 && doseInADay == 1 && gapInMedicine == 0) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
			}
		}
		if (checkUpTime >19 && doseInADay == 1 && gapInMedicine == 1) {

			for (int i = 1; i <= doseDay; i++) {
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal1 = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal1.getTime();
			}
		}
		System.out.println("Inside timeTable()"+finalDate);
		return finalDate;
		}
		else{
			for(int i=1;(i<=doseDay&&gapInMedicine==0&&doseInADay==3);i++)
			{
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(1);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
			}
			for(int i=1;(i<=doseDay&&gapInMedicine==0&&doseInADay==2);i++)
			{
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
			}
			for(int i=1;(i<=doseDay&&gapInMedicine==0&&doseInADay==1);i++)
			{
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				finalDate = cal.getTime();
			}

			for(int i=1;(i<=doseDay&&gapInMedicine==1&&doseInADay==3);i++)
			{
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(1);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 2);
				finalDate = cal.getTime();
			}
			for(int i=1;(i<=doseDay&&gapInMedicine==1&&doseInADay==2);i++)
			{
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(1);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 2);
				finalDate = cal.getTime();
			}
			for(int i=1;(i<=doseDay&&gapInMedicine==1&&doseInADay==1);i++)
			{
				Notification notification = new Notification();
				entityManager.getTransaction().begin();
				notification.setPatientMedicine(patientMedicine);
				notification.setBreakfast(0);
				notification.setLunch(0);
				notification.setDinner(1);
				notification.setMedDate(finalDate);
				entityManager.persist(notification);
				entityManager.getTransaction().commit();
				Calendar cal = new GregorianCalendar(2016, finalDate.getMonth(), finalDate.getDate());
				cal.add(Calendar.DAY_OF_MONTH, 2);
				finalDate = cal.getTime();
			}
		return finalDate;
		}
	}

	public static User getPatientInfo(String userEmail) {
		EntityManager entityManager=CreateEntity.getEntity();
		User user=entityManager.find(User.class,userEmail);
		return user;
	}
}