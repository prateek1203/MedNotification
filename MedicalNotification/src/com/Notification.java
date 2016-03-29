package com;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int norificationId;
	private int breakfast;
	private int lunch;
	private int dinner;
	@Temporal(TemporalType.DATE)
	private Date medDate;

	public int getNorificationId() {
		return norificationId;
	}

	public void setNorificationId(int norificationId) {
		this.norificationId = norificationId;
	}

	public int getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(int breakfast) {
		this.breakfast = breakfast;
	}

	public int getLunch() {
		return lunch;
	}

	public void setLunch(int lunch) {
		this.lunch = lunch;
	}

	public int getDinner() {
		return dinner;
	}

	public void setDinner(int dinner) {
		this.dinner = dinner;
	}

	public Date getMedDate() {
		return medDate;
	}

	public void setMedDate(Date medDate) {
		this.medDate = medDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PatientMedicine getPatientMedicine() {
		return patientMedicine;
	}

	public void setPatientMedicine(PatientMedicine patientMedicine) {
		this.patientMedicine = patientMedicine;
	}



	@ManyToOne
	@JoinColumn(name = "medicineId")
	private PatientMedicine patientMedicine;

}
