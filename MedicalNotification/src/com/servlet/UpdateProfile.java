package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProfile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		System.out.println("Update Servlet called..");

		String message = PatientHistory.updatePatientProfile(request.getSession().getAttribute("User").toString(),
				request.getParameter("userName"), request.getParameter("userPhNo"));
		System.out.println(message);
		writer.write(message);
	}

}
