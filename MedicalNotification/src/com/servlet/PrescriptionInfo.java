package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Prescription;

public class PrescriptionInfo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Prescription> id=PatientHistory.getAllpatientPrescription(request.getSession().getAttribute("User").toString());
		PrintWriter writer = response.getWriter();
		writer.write("{ \"prescriptionList\" : " + id+ " }");
	}

}
