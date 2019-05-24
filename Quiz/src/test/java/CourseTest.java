import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.example.Quiz.Course;
import com.example.Quiz.Quiz;

public class CourseTest {

	@Test
	public void shouldReturnQuizName() {
		Course course = new Course(); 
		course.setcourseCode("Test");
		assertEquals("Course Name Should Equal Test", course.getcourseCode(),"Test");
	}	

}
