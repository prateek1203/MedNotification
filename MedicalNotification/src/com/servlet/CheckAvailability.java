package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Doctor;
import com.User;
import com.VerifyUser;

public class CheckAvailability extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("servlet called");
			PrintWriter writer=response.getWriter();
		if (request.getParameter("usertype").equals("Patient")) {
			User user = VerifyUser.VerifyPatientSignUp(request.getParameter("email"));
			if (user == null) {
				VerifyUser.createPatient(request.getParameter("name"),request.getParameter("email"),request.getParameter("password"),request.getParameter("PhoneNo"));
				/*JOptionPane.showMessageDialog(null, "Patient-id Created..!! ");
				response.sendRedirect("login.html");*/
				writer.write("Patient Id Created");
			} else {
				/*JOptionPane.showMessageDialog(null, "Patient Already Exists try with new Email id.");
				response.sendRedirect("signupform.html");*/
				writer.write("Patient Already Exists try with new Email id.");
			}

		}
		else

			if (request.getParameter("usertype").equals("Doctor")) {
				System.out.println(request.getParameter("name")+""+request.getParameter("email")+""+request.getParameter("password")+""+request.getParameter("PhoneNo"));
				Doctor doctor = VerifyUser.VerifyDoctorSignUp(request.getParameter("email"));
				if (doctor == null) {
					VerifyUser.createDoctor(request.getParameter("name"),request.getParameter("email"),request.getParameter("password"),request.getParameter("PhoneNo"));
					/*JOptionPane.showMessageDialog(null, "Doctor-id Created..!! ");
					response.sendRedirect("login.html");*/
					writer.write("Doctor Id Created");
				} else {
					/*JOptionPane.showMessageDialog(null, "Doctor Already Exists try with new Email id.");
					response.sendRedirect("signupform.html");*/
					writer.write("Doctor Already Exists try with new Email id.");
				}

			}
			

	}

}
