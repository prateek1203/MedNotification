package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmail extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/*PrintWriter writer=response.getWriter();
		String reply= PatientHistory.sendPassword(request.getParameter("email"),request.getParameter("usertype"));
		writer.write(reply);*/
	}

}
