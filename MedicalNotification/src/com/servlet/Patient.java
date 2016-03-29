package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Prescription;

public class Patient extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Patient Servlet called");
		PrintWriter writer = response.getWriter();
		
		List<Prescription> prescriptioId = PatientHistory.getPatientId(request.getParameter("userEmail"),request.getSession().getAttribute("User").toString());
		System.out.println("after Patient Servlet called"+prescriptioId);
		writer.write("{ \"patientIdList\" : " + prescriptioId + "}");
	}

}
