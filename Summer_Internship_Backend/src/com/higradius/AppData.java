package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppData
 */
//@WebServlet("/AppData")
public class AppData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/hi_3";
	
	// Database credentials
	static final String USER = "root";
	static final String PASS = "7002";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	
	

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		

		PrintWriter out=response.getWriter();	
		//
		
		
		String name_customer=request.getParameter("cName");
		String cust_number=request.getParameter("cno");
		String doc_id=request.getParameter("invno");
		String total_open_amount=request.getParameter("invamt");
		String due_in_date=request.getParameter("due");
		String notes=request.getParameter("note");
		
		
		
		
		
		Connection con=null;
		String query= null;

		try {
		 Class.forName(JDBC_DRIVER);
			
		 con = DriverManager.getConnection(DB_URL,USER,PASS);
		 query="insert into mytable(name_customer,cust_number,doc_id,total_open_amount,due_in_date,notes) values(?,?,?,?,?,?)";
		 
		 PreparedStatement st=con.prepareStatement(query);
		 
		 st.setString(1, name_customer);
		 st.setString(2, cust_number);
		 st.setString(3, doc_id);
		 st.setString(4, total_open_amount);
		 st.setString(5, due_in_date);
		 //
		 st.setString(6, notes);
		 
		 int count= st.executeUpdate();
		 

		   
		
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
