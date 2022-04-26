
//
///**
// * Servlet implementation class AddData
// */
//
//public class AddData extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public AddData() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		 String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//		 String DB_URL = "jdbc:mysql://localhost/hi_3";
//		
//		// Database credentials
//		 String USER = "root";
//		String PASS = "7002";
//		
//		
//	
//		
//		PrintWriter out=response.getWriter();	
//		//
//		
//		
//		String name_customer=request.getParameter("name");
//		
//		System.out.println(name_customer);
//		
//		String cust_number=request.getParameter("cno");
//		String doc_id=request.getParameter("invno");
//		String total_open_amount=request.getParameter("invamt");
//		String due_in_date=request.getParameter("due");
//		String notes=request.getParameter("note");
//		
//		
//		
//		
//		
//		Connection con=null;
//		String query= null;
//
//		try {
//		 Class.forName(JDBC_DRIVER);
//			
//		 con = DriverManager.getConnection(DB_URL,USER,PASS);
//		 query="insert into mytable(name_customer,cust_number,doc_id,total_open_amount,due_in_date,notes) values(?,?,?,?,?,?)";
//		 
//		 PreparedStatement st=con.prepareStatement(query);
//		 
//		 st.setString(1, name_customer);
//		 st.setString(2, cust_number);`
//		 st.setString(3, doc_id);
//		 st.setString(4, total_open_amount);
//		 st.setString(5, due_in_date);
//		 //
//		 st.setString(6, notes);
//		 
//		 st.executeUpdate();
//		 
//
//		   
//		
//	}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//
//		
//	}
//
//		
//		
//		
//		
//		
//		
//	
//
//}





















package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class add
 */

public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String url="jdbc:mysql://localhost/hi_4"; 
    private static String username= "root";
    private static String password= "7002";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String cust_name,cust_no;
		Double invoice_no,invoice_amt;
		int key=(int) Math.floor(Math.random()*(20000-10001+1)+10001);
		cust_name=request.getParameter("name");
		cust_no=request.getParameter("cno");
		invoice_no=Double.parseDouble(request.getParameter("invno"));
		invoice_amt=Double.parseDouble(request.getParameter("invamt"));
		java.sql.Date due_date=java.sql.Date.valueOf(request.getParameter("ddt"));
		//List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt=con.createStatement();
//			PreparedStatement ps=con.prepareStatement("insert into mytable cust_number,name_customer,invoice_id,total_open_amount,due_in_date values ('"+cust_name+"','"+cust_no+"','"+invoice_no+"','"+invoice_amt+"','"+due_date+"')");
			String query="insert into mytable (key_0,name_customer,cust_number,doc_id,total_open_amount,due_in_date) values ('"+key+"','"+cust_name+"','"+cust_no+"','"+invoice_no+"','"+invoice_amt+"','"+due_date+"')";
			stmt.executeUpdate(query);
			System.out.println("data inserted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
//		response.setContentType("application/json");
		
		doGet(request, response);
	}

}



