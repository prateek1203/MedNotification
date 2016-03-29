package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrescriptionDetails extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String detail = PatientHistory.getPrescriptionDetails(Integer.parseInt(request.getParameter("prescriptionId")));
		PrintWriter writer = response.getWriter();
		writer.write(detail);
	}
}
