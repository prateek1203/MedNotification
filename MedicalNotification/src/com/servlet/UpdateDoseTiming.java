package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateDoseTiming extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("UpdateDoesTiming Servlet Called.");
		PrintWriter writer = response.getWriter();
		String reply = PatientHistory.updatePatientDoesTiming(request.getParameter("breakFast"),
				request.getParameter("lunch"), request.getParameter("dinner"),
				request.getSession().getAttribute("User").toString());
		System.out.println(reply);
		writer.write(reply);
	}
}
