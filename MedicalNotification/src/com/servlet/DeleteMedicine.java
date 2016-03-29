package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMedicine extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		//System.out.println(request.getParameter("prescriptionId")+" "+request.getParameter("medicineName"));
		String message = PatientHistory.deleteMedicineInfo(Integer.parseInt(request.getParameter("prescriptionId")),
				request.getParameter("medicineName"));
		writer.write(message);
	}

}
