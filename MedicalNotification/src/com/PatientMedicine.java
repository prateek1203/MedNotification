package com;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
public class PatientMedicine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medicineId;
	/*private int prescriptioId;*/
	private String medicineName;
	private int doseInADay;
	private int doseDay;
	private int gapInMedicine;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date finaldate;
	
	
	public Date getFinaldate() {
		return finaldate;
	}

	public void setFinaldate(Date finaldate) {
		this.finaldate = finaldate;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	/*public int getPrescriptioId() {
		return prescriptioId;
	}

	public void setPrescriptioId(int prescriptioId) {
		this.prescriptioId = prescriptioId;
	}
*/
	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public int getDoseInADay() {
		return doseInADay;
	}

	public void setDoseInADay(int doseInADay) {
		this.doseInADay = doseInADay;
	}

	public int getDoseDay() {
		return doseDay;
	}

	public void setDoseDay(int doseDay) {
		this.doseDay = doseDay;
	}

	public int getGapInMedicine() {
		return gapInMedicine;
	}

	public void setGapInMedicine(int gapInMedicine) {
		this.gapInMedicine = gapInMedicine;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToOne
	@JoinColumn(name="prescriptioId")
	private Prescription prescription;


	@Override
	public String toString() {
		return "{\"medicineName\":\"" + medicineName + "\", \"doseInADay\":\"" + doseInADay + "\", \"doseDay\":\"" + doseDay
				+ "\", \"gapInMedicine\":\"" + gapInMedicine + "\",\"startDate\":\"" + new SimpleDateFormat("MMM-dd-yyyy").format(startDate) +"\",\"finaldate\":\""+ new SimpleDateFormat("MMM-dd-yyyy").format(finaldate)+"\"}";
	}
	

}
