import static org.junit.Assert.assertEquals;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import com.example.Quiz.DBConnection;
import com.example.Quiz.HomePage;
import com.example.Quiz.LoginView;
import com.example.Quiz.Question;
import com.example.Quiz.QuestionDifficulty;
import com.jcraft.jsch.JSchException;



public class DBConnectionTest {

	DBConnection db;
	@Before
	public void before() {
		db = new DBConnection(); 
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
	public void readDBEmailTest() {
		try {
			String x = db.readDBEmail("SELECT * FROM User WHERE username='nikola'");
			assertEquals("email Should Equal ", x,"1268698@students.wit");
		} catch (Exception e1) {

		}
	}
	@Test
	public void readDBQuestionTest() throws ClassNotFoundException, JSchException, SQLException {
		ArrayList<Question> arr=new ArrayList<Question>();
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		arr=db.readDBQuestion();
		assertEquals( DBConnection.os.toString(), "success");
	}
//	@Test
//	public void readDBCourseTest() throws ClassNotFoundException, JSchException, SQLException {
//		ArrayList<Question> arr=new ArrayList<Question>();
//		LoginView.loggedInUser="nikola";
//		HomePage.CurrentCourse="COMS3003";
//		arr=db.readDBQuestion();
//		assertEquals( DBConnection.os.toString(), "success");
//	}
	
	@Test
	public void sendToDBQuestionTestModified() throws ClassNotFoundException, JSchException, SQLException {
		Question q=new Question();
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		q.setQuestionText("Test");
		q.setQuestionAnswer("Test");
		q.setMarks("1");
		q.setDifficulty(QuestionDifficulty.Easy);
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		db.sendToDBQuestion(q,true);
		assertEquals( DBConnection.os.toString(), "success");
	}
	@Test
	public void sendToDBQuestionTestNotModified() throws ClassNotFoundException, JSchException, SQLException {
		Question q=new Question();
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		q.setQuestionText("Test");
		q.setQuestionAnswer("Test");
		q.setMarks("1");
		q.setDifficulty(QuestionDifficulty.Easy);
		LoginView.loggedInUser="nikola";
		HomePage.CurrentCourse="COMS3003";
		db.sendToDBQuestion(q,false);
		assertEquals( DBConnection.os.toString(), "success");
	}
}
