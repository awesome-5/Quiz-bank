package com.example.Quiz;

import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import org.apache.commons.io.output.ByteArrayOutputStream;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DBConnection {
	ArrayList<Course> courseObj = new ArrayList<Course>();
	ArrayList<Quiz> quizObj = new ArrayList<Quiz>();
	ArrayList<Question> output =new ArrayList<Question>();
	Question temp=new Question();
	public static OutputStream os = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(os);
	public static OutputStream os1 = new ByteArrayOutputStream();
	PrintStream ps1 = new PrintStream(os1);
	public static OutputStream os2 = new ByteArrayOutputStream();
	PrintStream ps2 = new PrintStream(os2);
	public static OutputStream os3 = new ByteArrayOutputStream();
	PrintStream ps3 = new PrintStream(os3);
	public static OutputStream os4 = new ByteArrayOutputStream();
	PrintStream ps4 = new PrintStream(os4);
	public static OutputStream os5 = new ByteArrayOutputStream();
	PrintStream ps5 = new PrintStream(os5);
	public static OutputStream os6 = new ByteArrayOutputStream();
	PrintStream ps6 = new PrintStream(os6);
	
	public String readDBUser(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBUser Connection...");
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
		}
		session.disconnect();
		con.close();
		return(output);
	}

	public String readDBEmail(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBUser Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				output =rs.getString("email");
			}


		} catch (SQLException err) {
		}
		session.disconnect();
		con.close();
		return(output);
	}

	public ArrayList<Question> readDBQuestion() throws ClassNotFoundException, JSchException, SQLException {

		System.out.println(HomePage.CurrentCourse);
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
			System.out.println("Establishing readDBQuestion Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();
			String sql="SELECT * FROM Question WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Question temp=new Question();
				long questionID = rs.getInt("questionID");
				String question = rs.getString("question");
				String answer = rs.getString("answer");
				int type = rs.getInt("type");
				String mark = rs.getString("mark");
				int difficulty = rs.getInt("difficulty");
				String time = rs.getString("time");
				Date lastUsed = rs.getDate("lastUsed");
				String variantOf = rs.getString("variantOf");
				String courseCode = rs.getString("courseCode");

				temp.setId(questionID);
				temp.setQuestionText(question);
				temp.setQuestionAnswer(answer);
				if(type==1)
				{
					temp.setType(QuestionType.StandardQuestion);
				}
				else if (type==0)
				{
					temp.setType(QuestionType.MCQ);
				}
				temp.setMarks(mark);
				if(difficulty==0)
				{
					temp.setDifficulty(QuestionDifficulty.Easy);
				}
				else if(difficulty==1)
				{
					temp.setDifficulty(QuestionDifficulty.MediumEasy);
				}
				else if(difficulty==2)
				{
					temp.setDifficulty(QuestionDifficulty.Medium);
				}
				else if(difficulty==3)
				{
					temp.setDifficulty(QuestionDifficulty.MediumHard);
				}
				else if(difficulty==4)
				{
					temp.setDifficulty(QuestionDifficulty.Hard);
				}

				temp.setTime(time);
				temp.setLastUsed(lastUsed);
				temp.setVariantOf(variantOf);
				temp.setCourseCode(courseCode);

				output.add(temp);

				System.out.println(output);
			}
			

		} catch (SQLException err) {
			System.out.println(err);
		}
		System.setOut(ps);
		System.out.print("success");
		PrintStream originalOut = System.out;
		System.setOut(originalOut);
		session.disconnect();
		con.close();
		return output;
	}

	public String readDBCourse(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBCourse Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);



			while (rs.next()) {
				//adds each new course to an arraylist of courses
				Course c  = new Course();
				c.setcourseCode(rs.getString("courseCode"));
				c.setCourseName(rs.getString("courseName"));
				output =rs.getString("courseCode");
				courseObj.add(c);
			}
			System.setOut(ps1);
			System.out.print("success1");
			PrintStream originalOut = System.out;
			System.setOut(originalOut);

		} catch (SQLException err) {
			output = "GET ERROR";
		}
		session.disconnect();
		con.close();
		return(output);
	}

	public void sendToDBQuestion(Question q, Boolean isModified) throws ClassNotFoundException, JSchException, SQLException {

		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session = null;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(uName, host, 22);
			session.setPassword(uPass);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing sendToDBQuestionConnection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			ResultSet rss;
			Statement statement = con.createStatement();

			//get all information of the questions filled in the form
			String question = q.getQuestionText();
			String answer = q.getQuestionAnswer();
			if (answer.isEmpty())
				answer="NULL";
			int type = 0; //mcq
			if (q.getType()==QuestionType.StandardQuestion)
				type = 1;
			String mark = q.getMarks();
			int difficulty=0;
			if(q.getDifficulty()==QuestionDifficulty.Easy)
				difficulty = 0;
			else if(q.getDifficulty()==QuestionDifficulty.MediumEasy)
				difficulty = 1;
			else if (q.getDifficulty()==QuestionDifficulty.Medium)
				difficulty = 2;
			else if(q.getDifficulty()==QuestionDifficulty.MediumHard)
				difficulty =3 ;
			else if(q.getDifficulty()==QuestionDifficulty.Hard)
				difficulty = 4;
			int lines=q.getLines();
			String options=q.getOptions();
			if (options.isEmpty())
				options="NULL";
			int time = Integer.parseInt(q.getTime());
			String variantOf = String.valueOf(QuestionGridView.CurrentId);
			String courseCode = q.getCourseCode();


			//modify question
			if (isModified) {
				System.out.println("question is being modified");
				String sqlmofid= "UPDATE Question SET question='"+ question + "', answer='" + answer+ "', type='"+type+"', mark='" + mark + "',difficulty='" + difficulty + "',time='" + time+ "' WHERE questionID = '"+QuestionGridView.CurrentId+ "';";
				statement.executeUpdate(sqlmofid);

				System.setOut(ps2);
				System.out.print("success2");
				PrintStream originalOut = System.out;
				System.setOut(originalOut);

			}
			/*String questionInDB = "SELECT * FROM Question WHERE questionID = '"+QuestionGridView.CurrentId+ "';";
			rss=statement.executeQuery(questionInDB);
			System.out.println("result of the query"+rss);
			if(rss != null) {
				System.out.println("question is being modified");
				String sqlmofid= "UPDATE Question SET ('"+ QuestionGridView.CurrentId + "','" + "nikola" + "','" + question + "','" + answer + "','" + type + "','" + mark + "','" + difficulty + "','" + time+ "'," + "NULL" + "," + variantOf+ ",'" +courseCode+ ")"+" WHERE questionID = '"+QuestionGridView.CurrentId+ "';";
				statement.executeUpdate(sqlmofid);
			}*/

			//add new question in DB
			else {
				String sql="INSERT INTO Question VALUES("+ "NULL" + ",'" + LoginView.loggedInUser + "','" + question + "','" + answer + "','" + type + "','" + mark + "','" + difficulty + "','" + time+ "'," + "NULL" + "," + variantOf+ ",'" +courseCode+ "')" ;
				statement.executeUpdate(sql);

				String sqlID="SELECT * FROM Question WHERE username = '"+LoginView.loggedInUser+ "' AND question ='" + question +"'";
				rss=statement.executeQuery(sqlID);
				int id = 0;
				while (rss.next()) {
					id = rss.getInt("questionID");;
				}

				if (q.getType()==QuestionType.MCQ) //mcq
				{
					String sqlmcq="INSERT INTO MCQ VALUES('"+ id + "','" + options + "')" ;
					statement.executeUpdate(sqlmcq);
				}
				else
				{
					String sqlmcq="INSERT INTO Standard VALUES('"+ id + "','" + lines + "')" ;
					statement.executeUpdate(sqlmcq);
				}

				System.setOut(ps3);
				System.out.print("success3");
				PrintStream originalOut = System.out;
				System.setOut(originalOut);
			}
		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();


	}

	public void deleteQuestionFromDB (Question q) throws ClassNotFoundException, JSchException, SQLException {

		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session = null;
		ResultSet rss;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(uName, host, 22);
			session.setPassword(uPass);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing deleteQuestionFromDB Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			String deleteQuestion = "DELETE FROM Question WHERE questionID = '"+QuestionGridView.CurrentId+ "';";
			statement.executeUpdate(deleteQuestion);
			System.out.println("question deleted");

			
			String findVariants = "SELECT * FROM Question WHERE variantOf = '"+QuestionGridView.CurrentId+"'";
			rss=statement.executeQuery(findVariants);
			if (rss!=null) {
				String sqlmofid= "UPDATE Question SET variantOf=NULL WHERE variantOf = '"+QuestionGridView.CurrentId+"'";
				statement.executeUpdate(sqlmofid);
			}
			
			if (q.getType()==QuestionType.MCQ) //mcq
			{
				String sqlmcq="DELETE FROM MCQ WHERE questionID = '"+q.getId()+"'";
				statement.executeUpdate(sqlmcq);
			}
			else //std
			{
				String sqlmcq="DELETE FROM Standard WHERE questionID = '"+q.getId()+"'";
				statement.executeUpdate(sqlmcq);
			}
			
		} catch (SQLException err) {
		}
		System.setOut(ps4);
		System.out.print("success4");
		PrintStream originalOut = System.out;
		System.setOut(originalOut);
		session.disconnect();
		con.close();


	}

	public void postDB(String sql) throws ClassNotFoundException, JSchException, SQLException {


		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session =null;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(uName, host, 22);
			session.setPassword(uPass);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing postDB Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			statement.executeUpdate(sql);




		} catch (SQLException err) {
			System.setOut(ps5);
			System.out.print("success5");
			PrintStream originalOut = System.out;
			System.setOut(originalOut);
		}
		session.disconnect();
		con.close();

	}

	public String readTests(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBCourse Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);


			while (rs.next()) {
				//adds each new quiz to an arraylist of quizzes
				Quiz q  = new Quiz();
				q.setQuizName(rs.getString("quizName"));
				output =rs.getString("quizName");
				quizObj.add(q);

			}


		} catch (SQLException err) {
			output = "GET ERROR";
		}
		session.disconnect();
		con.close();
		return(output);
	}

	public void deleteQuiz (String q) throws ClassNotFoundException, JSchException, SQLException {



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
			System.out.println("Establishing deleteQuiz Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			String deleteQuiz = "DELETE FROM Quiz WHERE quizName = '"+q+ "'";
			statement.executeUpdate(deleteQuiz);
			System.out.println("quiz deleted");

		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();


	}

	public void moveFinal (String q) throws ClassNotFoundException, JSchException, SQLException {



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
			System.out.println("Establishing moveToFinal Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			String movetoF = "UPDATE Quiz SET draftOrFinal = 1 WHERE quizName ='"+q+"'";
			statement.executeUpdate(movetoF);
			System.out.println("moved to final");

		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();


	}

	public void moveDraft (String q) throws ClassNotFoundException, JSchException, SQLException {



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
			System.out.println("Establishing moveToFinal Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			String movetoD = "UPDATE Quiz SET draftOrFinal = 0 WHERE quizName ='"+q+"'";
			statement.executeUpdate(movetoD);
			System.out.println("moved to final");

		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();


	}

	public String getIDS(String sql) throws SQLException, JSchException, ClassNotFoundException
	{
		System.out.println(HomePage.CurrentCourse);
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
		String out = null;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(uName, host, 22);
			session.setPassword(uPass);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing readDBQuestion Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				out =rs.getString("questionIDS");
			}


		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();
		return out;
	}

	public Question readQuestionsTest(String s) throws ClassNotFoundException, JSchException, SQLException {

		System.out.println(HomePage.CurrentCourse);
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
			System.out.println("Establishing readDBQuestion Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();
			rs = statement.executeQuery(s);
			while (rs.next()) {

				long questionID = rs.getInt("questionID");
				String question = rs.getString("question");
				String answer = rs.getString("answer");
				int type = rs.getInt("type");
				String mark = rs.getString("mark");
				int difficulty = rs.getInt("difficulty");
				String time = rs.getString("time");
				Date lastUsed = rs.getDate("lastUsed");
				String variantOf = rs.getString("variantOf");
				String courseCode = rs.getString("courseCode");

				temp.setId(questionID);
				temp.setQuestionText(question);
				temp.setQuestionAnswer(answer);
				if(type==1)
				{
					temp.setType(QuestionType.StandardQuestion);
				}
				else if (type==0)
				{
					temp.setType(QuestionType.MCQ);
				}
				temp.setMarks(mark);
				if(difficulty==0)
				{
					temp.setDifficulty(QuestionDifficulty.Easy);
				}
				else if(difficulty==1)
				{
					temp.setDifficulty(QuestionDifficulty.MediumEasy);
				}
				else if(difficulty==2)
				{
					temp.setDifficulty(QuestionDifficulty.Medium);
				}
				else if(difficulty==3)
				{
					temp.setDifficulty(QuestionDifficulty.MediumHard);
				}
				else if(difficulty==4)
				{
					temp.setDifficulty(QuestionDifficulty.MediumHard);
				}

				temp.setTime(time);
				temp.setLastUsed(lastUsed);
				temp.setVariantOf(variantOf);
				temp.setCourseCode(courseCode);
			}

		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();
		return temp;
	}

	public String readQuizName(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBUser Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				output =rs.getString("quizName");
			}


		} catch (SQLException err) {
			output = "GET ERROR";
		}
		session.disconnect();
		con.close();
		return(output);
	}

	public Timestamp readReset(String sql) throws ClassNotFoundException, JSchException, SQLException {

		Timestamp out = null;
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
			System.out.println("Establishing readDBUser Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				out =rs.getTimestamp("timestamp");
			}


		} catch (SQLException err) {
		}
		session.disconnect();
		con.close();
		return(out);
	}

	public void deleteID (String sq) throws ClassNotFoundException, JSchException, SQLException {



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
			System.out.println("Establishing deleteID Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();


			statement.executeUpdate(sq);
			System.out.println("id deleted");

		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();


	}

	public void addID (String sq) throws ClassNotFoundException, JSchException, SQLException {



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
			System.out.println("Establishing addID Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();


			statement.executeUpdate(sq);
			System.out.println("id added");

		} catch (SQLException err) {
			System.out.println(err);
		}
		session.disconnect();
		con.close();


	}

	public void setLastUsed (String id) throws ClassNotFoundException, JSchException, SQLException {

		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session = null;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(uName, host, 22);
			session.setPassword(uPass);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing moveToFinal Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String change = "UPDATE Question SET lastUsed = '"+ now+ "' WHERE questionID ='"+id+"'";
			statement.executeUpdate(change);
			System.out.println("last used change");

		} catch (SQLException err) {
			System.out.println(err);
		}		
		session.disconnect();
		con.close();
	}

	public void setQuizLastUsed (String name) throws ClassNotFoundException, JSchException, SQLException {

		int lport=5656;
		String rhost="127.0.0.1";
		String host="lamp.ms.wits.ac.za";
		int rport=3306;
		String dbUrl = "jdbc:mysql://"+rhost+":"+lport+"/d1268698";
		String uName = "s1268698";
		String uPass = "s1268698";
		Connection con = null;
		Session session = null;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(uName, host, 22);
			session.setPassword(uPass);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing moveToFinal Connection...");
			session.connect();
			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String change = "UPDATE Quiz SET quizLastUsed = '"+ now+ "' WHERE quizName ='"+name+"'";
			statement.executeUpdate(change);
			System.out.println("last used quiz changed where id = "+name);

		} catch (SQLException err) {
			System.out.println(err);
		}

		session.disconnect();
		con.close();


	}
	public String readLines(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBUser Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				output =rs.getString("line");
			}


		} catch (SQLException err) {
		}
		session.disconnect();
		con.close();
		return(output);
	}
	public String readOptions(String sql) throws ClassNotFoundException, JSchException, SQLException {

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
			System.out.println("Establishing readDBUser Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);

			while (rs.next()) {
				output =rs.getString("options");
			}


		} catch (SQLException err) {
		}
		session.disconnect();
		con.close();
		return(output);
	}
	public int readTestOrExam(String sql) throws ClassNotFoundException, JSchException, SQLException {

		int output = 0;
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
			System.out.println("Establishing readDBCourse Connection...");
			session.connect();

			int assigned_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assigned_port + " -> " + rhost + ":" + rport);


			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, uName, uPass);
			System.out.println ("Database connection established");
			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);


			while (rs.next()) {
				//adds each new quiz to an arraylist of quizzes
				output =rs.getInt("testOrExam");

			}


		} catch (SQLException err) {
			output = 2;
		}
		session.disconnect();
		con.close();
		return(output);
	}

}


