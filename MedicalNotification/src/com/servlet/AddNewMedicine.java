package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewMedicine extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String reply;
		System.out.println("AddNewMedicine Servlet called.");
		/*System.out.println(Integer.parseInt(request.getParameter("prescriptionId"))+" "+
				request.getParameter("medName")+" "+Integer.parseInt(request.getParameter("dayCounts"))+" "+
				Integer.parseInt(request.getParameter("days"))+" "+ Integer.parseInt(request.getParameter("gap"))+" "+
				request.getParameter("startDate"));*/

		
			try {
				 reply=PatientHistory.setMedicine(Integer.parseInt(request.getParameter("prescriptionId")),
						request.getParameter("medName"), Integer.parseInt(request.getParameter("dayCounts")),
						Integer.parseInt(request.getParameter("days")), Integer.parseInt(request.getParameter("gap")),
						request.getParameter("startDate"),Integer.parseInt(request.getParameter("CheckUpTime")));
				writer.write(reply);
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("AddNewMedicine servlet");
				e.printStackTrace();
			}
			
		
	}

}
