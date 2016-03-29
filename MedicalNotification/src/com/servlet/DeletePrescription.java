package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePrescription extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter writer=response.getWriter();
		PatientHistory.deleteMedicine(Integer.parseInt(request.getParameter("prescriptionId")));
		writer.write("Prescription removed");
	}

}
