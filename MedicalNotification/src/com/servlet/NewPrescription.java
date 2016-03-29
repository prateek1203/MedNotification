package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewPrescription extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("servlet called for new Prescription");
		PrintWriter writer = response.getWriter();
		int i = PatientHistory.addNewPrescription(request.getSession().getAttribute("User").toString(),
				request.getParameter("prescription"), request.getParameter("userEmail"));
		System.out.println("Grnareated id-" + i);
		writer.write("" + i);
	}
}
