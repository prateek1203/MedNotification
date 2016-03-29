package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AllPatient;
import com.User;

public class PatientList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		List<User> list = AllPatient.getPatientList(request.getParameter("PatientName"));
		writer.write("{ \"patientList\" : " + list.toString() + " }");
	}

}
