package com.higradius;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.ArrayList;
/**
 * Servlet implementation class FetchData
 */
@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/hi_3";
	
	// Database credentials
	static final String USER = "root";
	static final String PASS = "7002";
	//public static void main(String[] args) {}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 setAccessControlHeaders(response);
		 response.setStatus(HttpServletResponse.SC_OK);
		
		Connection conn = null;
		Statement stmt = null;
		String sql= null;
		ResultSet rs= null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			System.out.println(conn);
			
			stmt = conn.createStatement();
			
			sql="select name_customer,cust_number,doc_id,total_open_amount,due_in_date,clear_date,notes from mytable where due_in_date < clear_date AND cust_number!='CCU001' limit 300";
			
			rs=stmt.executeQuery(sql);
			int count=0;
			ArrayList<Response> responseList = new ArrayList<>();
			while(rs.next()) {
				
				Response pojoObj=new Response();
				
				
				String custName= rs.getString("name_customer");
				String custNum=rs.getString("cust_number");
				Long invId=rs.getLong("doc_id");
				float invAmt=rs.getFloat("total_open_amount");
				Date dueDt=rs.getDate("due_in_date");
				Date prdDt=rs.getDate("clear_date");
				String nots=rs.getString("notes");
				
				pojoObj.setCustName(custName);
				pojoObj.setCustId(custNum);
				pojoObj.setInvoiceId(invId);
				pojoObj.setInvoiceAmt(invAmt);
				pojoObj.setDueIn(dueDt);
				pojoObj.setPrdDate(prdDt);
				pojoObj.setNotes(nots);
				
				responseList.add(pojoObj);
				
				
				/*System.out.println("custName:"+ custName);
				System.out.println("custNum:"+ custNum);
				System.out.println("invId:"+ invId);
				System.out.println("due Date:"+ dueDt);
				System.out.println("Amount:" + invAmt);
				System.out.println("pred date"+ prdDt);
				System.out.println("notes"+ nots);
				count++;
				System.out.println(count);
				
				System.out.println(responseList);*/
				
				
			}
			
			/*for(Response obj : responseList) {
				System.out.println("custName: "+obj.getCustName()+", ");
				System.out.println("custId: "+obj.getCustId()+", ");
				System.out.println("InvoiceId: "+obj.getInvoiceId()+", ");
				System.out.println("DD: "+obj.getDueIn()+", ");
				System.out.println("pd: "+obj.getPrdDate()+", ");
				
				System.out.println();
			}*/
			
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(responseList);
			System.out.println(json);
			response.setContentType("application/json");
			response.getWriter().write(json);
			
			
			
			
			
			rs.close();
			stmt.close();
			conn.close();
		}
			
		catch(Exception se) {
			
			se.printStackTrace();
		}
		finally{
		//finally block used to close resources
			try{
			if(stmt!=null)
			stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
			if(conn!=null)
			conn.close();
			}catch(SQLException se){
			se.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 protected void doOptions(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
	      setAccessControlHeaders(response);
	      response.setStatus(HttpServletResponse.SC_OK);
	  }

	
	
	private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5501/");
	      resp.setHeader("Access-Control-Allow-Methods", "GET");
	  }

}
