import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import com.example.Quiz.DBConnection;
import com.example.Quiz.Question;
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
	public void readEmail() throws ClassNotFoundException, JSchException, SQLException {
		ArrayList<Question> arr=new ArrayList<Question>();
		arr=db.readDBQuestion();
		
	}
}
