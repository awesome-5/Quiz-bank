package com.example.Quiz;

import static org.junit.Assert.assertEquals;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.example.Quiz.DBConnection;
import com.example.Quiz.HomePage;
import com.example.Quiz.LoginView;
import com.example.Quiz.Question;
import com.example.Quiz.QuestionDifficulty;
import com.example.Quiz.QuestionType;
import com.example.Quiz.Quiz;
import com.example.Quiz.TestView;
import com.jcraft.jsch.JSchException;
import java.sql.Timestamp;



public class DBConnectionTest {

	DBConnection db;
	Question STDQ=new Question();
	@Before
	public void before() {
		db = new DBConnection(); 
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		STDQ.setQuestionText("Test");
		STDQ.setQuestionAnswer("Test");
		STDQ.setMarks("1");
		STDQ.setDifficulty(QuestionDifficulty.Easy);
		STDQ.setType(QuestionType.MCQ);
		STDQ.setLines(1);
		STDQ.setTime("1");
		STDQ.setId(1000L);
	}
	@After
	public void after() throws ClassNotFoundException, JSchException, SQLException {
		db.postDB("DELETE FROM Question WHERE question = 'Test' AND answer='Test'");
		db.postDB("DELETE FROM User WHERE username = 'TEST'");
		db.deleteQuiz("TEST");
		db.deleteID("DELETE FROM Reset WHERE email='natp4444@yahoo.com'");
	}


	@Test
	public void readDBUserTest() {
		try {
			String x = db.readDBUser("SELECT * FROM User WHERE username='nikola'");
			assertEquals("Username Should Equal nikola","nikola",x);
		} catch (Exception e1) {

		}
	}

	@Test
	public void readDBEmailTest() throws ClassNotFoundException, JSchException, SQLException {

		String x = db.readDBEmail("SELECT * FROM User WHERE username='nikola'");
		assertEquals("email Should Equal ","1268698@students.wit",x);
	}

	@Test
	public void readDBQuestionTest() throws ClassNotFoundException, JSchException, SQLException {
		db.readDBQuestion();
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void readDBCourseTest() throws ClassNotFoundException, JSchException, SQLException {
		db.readDBCourse("SELECT courseCode,courseName FROM Course WHERE username='"+LoginView.loggedInUser+"'");
		assertEquals(true, DBConnection.result);
	}

	@Test
	public void sendToDBQuestionTestModified() throws ClassNotFoundException, JSchException, SQLException {

		db.sendToDBQuestion(STDQ,true);
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void sendToDBQuestionTestNotModified() throws ClassNotFoundException, JSchException, SQLException {

		db.sendToDBQuestion(STDQ,false);
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void sendToDBQuestionTestDiff1() throws ClassNotFoundException, JSchException, SQLException {
		STDQ.setDifficulty(QuestionDifficulty.MediumEasy);
		db.sendToDBQuestion(STDQ,false);
		assertEquals(true,DBConnection.result);
	}
	
	@Test
	public void sendToDBQuestionTestDiff2() throws ClassNotFoundException, JSchException, SQLException {
		STDQ.setDifficulty(QuestionDifficulty.Medium);
		db.sendToDBQuestion(STDQ,false);
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void sendToDBQuestionTestDiff3() throws ClassNotFoundException, JSchException, SQLException {
		STDQ.setDifficulty(QuestionDifficulty.MediumHard);
		db.sendToDBQuestion(STDQ,false);
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void sendToDBQuestionTestDiff4() throws ClassNotFoundException, JSchException, SQLException {
		STDQ.setDifficulty(QuestionDifficulty.Hard);
		db.sendToDBQuestion(STDQ,false);
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void deleteQuestionFromDBTest() throws ClassNotFoundException, JSchException, SQLException 
	{
		db.sendToDBQuestion(STDQ,false);
		db.deleteQuestionFromDB(STDQ);
		assertEquals(true,DBConnection.result);
	}

	@Test
	public void postDBTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO User VALUES('"+"TEST" + "','" + "TEST" + "','" + "TEST" + "','" + "TEST" + "','" + "TEST" + "')" );
		assertEquals(true,DBConnection.result);

	}

	@Test
	public void readTestsTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.readTests("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0");
		assertEquals(true,DBConnection.result);
	}
	@Test
	public void deleteQuizTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "1,2,3" + "'," + 0 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		db.deleteQuiz("TEST");
		assertEquals(true,DBConnection.result);
	}
	
	@Test
	public void moveFinalTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "1,2,3" + "'," + 0 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		db.moveFinal("TEST");
		assertEquals(true,DBConnection.result);

	}
	
	@Test	
	public void moveDraftTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "1,2,3" + "'," + 1 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		db.moveFinal("TEST");
		assertEquals(true,DBConnection.result);
	}
	
	@Test	
	public void getIDSTest() throws ClassNotFoundException, SQLException, JSchException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "7" + "'," + 0 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		String id=db.getIDS("SELECT questionIDS FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='TEST'");
		assertEquals("7",id);
		
	}
	
	@Test	
	public void readQuestionsTestTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "7" + "'," + 0 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		Question quest=db.readQuestionsTest("SELECT * FROM Question WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND questionID='7'");
		assertEquals("7",quest.getId().toString());
	}

	@Test
	public void readQuizNameTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "7" + "'," + 0 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		String name=db.readQuizName("SELECT quizName FROM Quiz WHERE quizName='TEST'");
		assertEquals("TEST",name);
	}
	
	@Test	
	public void readResetTest() throws ClassNotFoundException, JSchException, SQLException
	{
		Timestamp t=db.readReset("SELECT timestamp from Reset WHERE email='natp4444@yahoo.com' AND ID='123'");
		assertEquals(null,t);
	}
	
	@Test	
	public void deleteIDTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.addID("INSERT INTO Reset VALUES('natp4444@yahoo.com', NULL,'1234')");
		db.deleteID("DELETE FROM Reset WHERE email='natp4444@yahoo.com'");
		assertEquals(true,DBConnection.result);

	}
	
	@Test	
	public void addIDTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.addID("INSERT INTO Reset VALUES('natp4444@yahoo.com', NULL,'1234')");
		assertEquals(true,DBConnection.result);
	}
	
	@Test	
	public void setLastUsedTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.sendToDBQuestion(STDQ,true);
		db.setLastUsed(STDQ.getId().toString());
		assertEquals(true,DBConnection.result);
	}
	
	@Test	
	public void setQuizLastUsedTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + "7" + "'," + 0 + ","+"NULL"+","+ "0" +",'"+ "TEST" +"')" );
		db.setQuizLastUsed("TEST");
		assertEquals(true,DBConnection.result);
	}
}
