package com.example.Quiz;


import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnectionGet {

	public String readDB(String sql) throws ClassNotFoundException, JSchException, SQLException {
		
		String output = null;
		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session = null;
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
			output = "GET ERROR";
		}
		session.disconnect();
		con.close();
		return(output);
	}
}