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
import com.jcraft.jsch.JSchException;



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
	}
	@After
	public void after() throws ClassNotFoundException, JSchException, SQLException {
		db.deleteQuestionFromDB(STDQ);
		db.postDB("DELETE FROM User WHERE username = 'TEST'");
	}
	
	@Test
	public void readDBUserTest() {
		try {
			String x = db.readDBUser("SELECT * FROM User WHERE username='nikola'");
			assertEquals("Username Should Equal nikola", x,"nikola");
		} catch (Exception e1) {

		}
	}

	@Test
	public void readDBEmailTest() throws ClassNotFoundException, JSchException, SQLException {

		String x = db.readDBEmail("SELECT * FROM User WHERE username='nikola'");
		assertEquals("email Should Equal ", x,"1268698@students.wit");
	}

	@Test
	public void readDBQuestionTest() throws ClassNotFoundException, JSchException, SQLException {
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		db.readDBQuestion();
		assertEquals( DBConnection.os.toString(), "success");
	}

	@Test
	public void readDBCourseTest() throws ClassNotFoundException, JSchException, SQLException {
		LoginView.loggedInUser="nikola";
		db.readDBCourse("SELECT courseCode,courseName FROM Course WHERE username='"+LoginView.loggedInUser+"'");
		assertEquals( DBConnection.os1.toString(), "success1");
	}

	@Test
	public void sendToDBQuestionTestModified() throws ClassNotFoundException, JSchException, SQLException {
		
		db.sendToDBQuestion(STDQ,true);
		assertEquals( DBConnection.os2.toString(), "success2");
	}

	@Test
	public void sendToDBQuestionTestNotModified() throws ClassNotFoundException, JSchException, SQLException {
		
		db.sendToDBQuestion(STDQ,false);
		assertEquals( DBConnection.os3.toString(), "success3");
	}

	
	public void deleteQuestionFromDBTest() throws ClassNotFoundException, JSchException, SQLException 
	{
		db.sendToDBQuestion(STDQ,false);
		db.deleteQuestionFromDB(STDQ);
		assertEquals( DBConnection.os4.toString(), "success4");
	}

	public void postDBTest() throws ClassNotFoundException, JSchException, SQLException
	{
		db.postDB("INSERT INTO User VALUES('"+"TEST" + "','" + "TEST" + "','" + "TEST" + "','" + "TEST" + "','" + "TEST" + "')" );
		assertEquals( DBConnection.os5.toString(), "success5");

	}

	public void readTestsTest()
	{

	}

	public void deleteQuizTest()
	{

	}
	public void moveFinalTest()
	{

	}
	public void moveDraftTest()
	{

	}
	public void getIDSTest()
	{

	}
	public void readQuestionsTestTest()
	{

	}
	public void readQuizNameTest()
	{

	}
	public void readResetTest()
	{

	}
	public void deleteIDTest()
	{

	}
	public void addIDTest()
	{

	}
	public void setLastUsedTest()
	{

	}
	public void setQuizLastUsedTest()
	{

	}
}
