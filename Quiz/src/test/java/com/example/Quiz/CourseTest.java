package com.example.Quiz;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.example.Quiz.Course;
import com.example.Quiz.Quiz;

public class CourseTest {

	@Test
	public void shouldReturnCourseName() {
		Course course = new Course(); 
		course.setCourseName("Test");
		assertEquals("Course Name Should Equal Test", course.getCourseName(),"Test");
	}	
	@Test
	public void shouldReturnCourseCode() {
		Course course = new Course(); 
		course.setcourseCode("Test");
		assertEquals("Course Code Should Equal Test", course.getcourseCode(),"Test");
	}	
}
