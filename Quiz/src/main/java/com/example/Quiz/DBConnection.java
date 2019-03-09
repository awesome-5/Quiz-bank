package com.example.Quiz;
//
//import java.beans.Statement;
//import java.sql.Connection;
//import java.util.Scanner;
//import java.sql.*; 
//import java.util.*; 
//
////public class DBConnection {
////
////}
//
//class DBConnection 
//{ 
//    public static void DBConnection() 
//    { 
//        //Creating the connection 
//        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
//        String user = "system"; 
//        String pass = "12345"; 
//  
//        //Entering the data 
//        Scanner k = new Scanner(System.in); 
//        System.out.println("enter name"); 
//        String name = k.next(); 
//        System.out.println("enter roll no"); 
//        int roll = k.nextInt(); 
//        System.out.println("enter class"); 
//        String cls =  k.next(); 
//  
//        //Inserting data using SQL query 
//        String sql = "insert into student1 values('"+name+"',"+roll+",'"+cls+"')"; 
//        Connection con=null; 
//        try
//        { 
//            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
//  
//            //Reference to connection interface 
//            con = DriverManager.getConnection(url,user,pass); 
//  
//            Statement st = (Statement) con.createStatement(); 
//            int m = ((java.sql.Statement) st).executeUpdate(sql); 
//            if (m == 1) 
//                System.out.println("inserted successfully : "+sql); 
//            else
//                System.out.println("insertion failed"); 
//            con.close(); 
//        } 
//        catch(Exception ex) 
//        { 
//            System.err.println(ex); 
//        } 
//    } 
//}


import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {

	public String readDB(String sql) throws ClassNotFoundException, JSchException, SQLException {
		
		String output = "Failed to SQL";
		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session =null;
		ResultSet rs;
		
		try {
			
			  JSch jsch = new JSch();
	            session = jsch.getSession(uName, host, 22);
	            session.setPassword(uPass);
	            session.setConfig("StrictHostKeyChecking", "no");
	            System.out.println("Establishing Connection...");
	            session.connect();

	            int assigned_port = session.setPortForwardingL(lport, rhost, rport);
	            System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);
	    	
	    	
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

		    rs = statement.executeQuery(sql);
		
		    while (rs.next()) {
		    	output =rs.getString("username");
            }
		

		} catch (SQLException err) {
			output = err.getMessage();
		}
		session.disconnect();
		con.close();
		return(output);
	}
}