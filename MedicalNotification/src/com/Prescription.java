package com;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Prescription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prescriptioId;
	/*private String userEmail;
	private String doctorEmail;*/
	private String prescriptionDetails;

	public int getPrescriptioId() {
		return prescriptioId;
	}

	public int getPrescriptionId() {
		return prescriptioId;
	}

	public void setPrescriptioId(int prescriptioId) {
		this.prescriptioId = prescriptioId;
	}

	/*public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;

	}

	public String getPrescriptionDetails() {
		return prescriptionDetails;
	}

	public void setPrescriptionDetails(String prescriptionDetails) {
		this.prescriptionDetails = prescriptionDetails;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@ManyToOne
	@JoinColumn(name="userEmail")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="doctorEmail")
	private Doctor doctor;

	@Override
	public String toString() {
		return "{ \"prescriptioId\":\"" + prescriptioId + "\", \"prescriptionDetails\":\"" + prescriptionDetails + "\",\"Doctor\":\""+ doctor.getName()+"\"}";
	}
	
	

}
