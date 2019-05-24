package com.example.Quiz;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class QuestionTest {

	private Long id;

	private String questionText = "";

	private String questionAnswer = "";

	private QuestionType type;

	private String marks = "";

	private QuestionDifficulty difficulty;

	private String time = "";

	private Date lastUsed = null;

	private String variantOf;

	private String courseCode="";

	private int lines=0;

	private String options="";

	@Test
	public void shouldReturnQuestionID() {
		Question q = new Question(); 
		q.setId(1L);
		//assertEquals("Question id Should Equal 1", q.getId(),1);
	}	
	@Test
	public void shouldReturnCourseCode() {
		Course course = new Course(); 
		course.setcourseCode("Test");
		assertEquals("Course Code Should Equal Test", course.getcourseCode(),"Test");
	}		
}
