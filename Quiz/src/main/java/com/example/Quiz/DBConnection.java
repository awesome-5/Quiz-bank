package com.example.Quiz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DBConnection {
	ArrayList<Course> courseObj = new ArrayList<Course>();
	ArrayList<Quiz> quizObj = new ArrayList<Quiz>();
	ArrayList<Question> output =new ArrayList<Question>();
	Question temp=new Question();

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
			output = "GET ERROR";
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
				if(type==0)
				{
					temp.setType(QuestionType.StandardQuestion);
				}
				else if (type==1)
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

				output.add(temp);

				System.out.println(output);
			}

		} catch (SQLException err) {
			System.out.println(err);
		}
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
				output =rs.getString("courseCode");
				courseObj.add(c);
			}


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
			int type = 0;
			if (q.getType()==QuestionType.MCQ)
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
				String sqlmofid= "UPDATE Question SET question='"+ question + "', answer='" + answer + "', mark='"  + mark + "',difficulty='" + difficulty + "',time='" + time+ "' WHERE questionID = '"+QuestionGridView.CurrentId+ "';";
				statement.executeUpdate(sqlmofid);
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
			String sql="INSERT INTO Question VALUES("+ "NULL" + ",'" + "nikola" + "','" + question + "','" + answer + "'," + type + ",'" + mark + "','" + difficulty + "','" + time+ "'," + "NULL" + "," + variantOf+ ",'" +courseCode+ "')" ;
			statement.executeUpdate(sql);

			String sqlID="SELECT * FROM Question WHERE username = '"+LoginView.loggedInUser+ "' AND question ='" + question +"'";
			rss=statement.executeQuery(sqlID);
			int id = 0;
			while (rss.next()) {
				id = rss.getInt("questionID");;
			}


			String sqlmcq="INSERT INTO MCQ VALUES('"+ id + "','" + options + "')" ;
			System.out.println(options);
			statement.executeUpdate(sqlmcq);

			//			else if (lines!=0)
			//			{
			//			String sqlstd="INSERT INTO Standard VALUES('"+ id + "','" + space + "','" + lines +"')" ;
			//			statement.executeUpdate(sqlstd);
			//			}
		
			
			

			System.out.println("Success");
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
			
			String deleteQuesion = "DELETE FROM Question WHERE questionID = '"+QuestionGridView.CurrentId+ "';";
			statement.executeUpdate(deleteQuesion);
			System.out.println("question deleted");
			
			String findVariants = "SELECT * FROM Question WHERE variantOf = '"+QuestionGridView.CurrentId+"'";
			rss=statement.executeQuery(findVariants);
			if (rss!=null) {
				String sqlmofid= "UPDATE Question SET variantOf=NULL WHERE variantOf = '"+QuestionGridView.CurrentId+"'";
				statement.executeUpdate(sqlmofid);
			}
			
			
		} catch (SQLException err) {
			System.out.println(err);
		}
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
				if(type==0)
				{
					temp.setType(QuestionType.StandardQuestion);
				}
				else if (type==1)
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
}