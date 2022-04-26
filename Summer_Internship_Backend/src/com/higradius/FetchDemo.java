package com.higradius;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * Servlet implementation class servletOne
 */
@WebServlet("/FetchDemo")
public class FetchDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String url="jdbc:mysql://localhost/hi_3"; 
    private static String username= "root";
    private static String password= "7002";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		try
		{
			Integer start = Integer.parseInt(request.getParameter("start"));
			Integer limit = Integer.parseInt(request.getParameter("limit"));
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement ps=con.prepareStatement("select name_customer,cust_number,doc_id,total_open_amount,due_in_date,clear_date,notes from mytable LIMIT ? , ?");
			ps.setInt(1, start);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();
			//
			while(rs.next())
			{
				HashMap<String,Object> perData = new HashMap<String,Object>();
				perData.put("name_customer", rs.getString(1));
				perData.put("cust_number", rs.getString(2));
				perData.put("invoice_id", rs.getString(3));
				perData.put("total_open_amount", rs.getString(4));
				perData.put("due_in_date", rs.getString(5));
				perData.put("clear_date", rs.getString(6));
				perData.put("notes", rs.getString(7));
				result.add(perData);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();

		response.getWriter().print(gson.toJson(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

