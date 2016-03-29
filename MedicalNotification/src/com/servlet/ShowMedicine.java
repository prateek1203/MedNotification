package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PatientMedicine;

public class ShowMedicine extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("showMedicine servlet called:-");
		PrintWriter writer = response.getWriter();
		List<PatientMedicine> med = PatientHistory.getMedicineInfo(Integer.parseInt(request.getParameter("prescriptionId")));
		if(med== null){
			System.out.println("no medicine");
			writer.write("No Patient Id Selected");
		}
		else{
		System.out.println("After show Medicine servlet called:-"+med);
		writer.write("{ \"medicineList\" : " + med + "}");
		}
	}

}
