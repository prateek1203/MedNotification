package com;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String to;
	private String from;
	private String messageBody;
	
	
	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getMessageBody() {
		return messageBody;
	}


	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void sendMessage(PatientMedicine p){
		System.out.println(p.getPrescription().getUser().getName()
				+ " Today is your medicine date.Your Medicine Name is:- " + p.getMedicineName());
	}
}
