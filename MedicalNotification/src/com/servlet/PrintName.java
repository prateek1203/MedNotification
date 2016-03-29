package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.User;

public class PrintName extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter writer=response.getWriter();
		User user=PatientHistory.getPatientInfo(request.getSession().getAttribute("User").toString());
		writer.write("{\"patientInfo\":"+user+"}");
	}

}
