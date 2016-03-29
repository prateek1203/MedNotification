package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Doctor;
import com.User;
import com.VerifyUser;

public class UserLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		HttpSession session=request.getSession();
		if (request.getParameter("usertype").equals("Patient")) {
			User user = VerifyUser.VerifyPatientSignin(request.getParameter("email"),request.getParameter("password"));
			if (user == null) {
				writer.write("Invalid Patient.");
			} else {
				
				session.setAttribute("User",user.getUserEmail());
				writer.write("Valid Patient.");
			}
		} else if (request.getParameter("usertype").equals("Doctor")) {
			Doctor doctor = VerifyUser.VerifyDoctorSignin(request.getParameter("email"),request.getParameter("password"));
			if (doctor == null) {
				writer.write("Invalid Doctor.");
			} else {
				
				session.setAttribute("User",doctor.getDoctorEmail());
				writer.write("Valid Doctor.");
			}
		}
	}

}
