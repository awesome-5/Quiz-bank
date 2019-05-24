import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.Quiz.Quiz;

public class QuizTest {

	@Test
	public void shouldReturnQuizName() {
		Quiz quiz = new Quiz(); 
		quiz.setQuizName("Test");
		assertEquals("Quiz Name Should Equal Test", quiz.getQuizName(),"Test");
	}	
	
	
}
