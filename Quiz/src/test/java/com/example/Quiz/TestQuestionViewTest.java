package com.example.Quiz;

import org.junit.Test;

public class TestQuestionViewTest {

	@Test
	public void getQuestions() {
		TestQuestionView tqv = new TestQuestionView(); 
		tqv.getQuestions("");	
		System.out.println("Success");
	}
	
	@Test
	public void getIDS() {
		TestQuestionView tqv = new TestQuestionView(); 
		tqv.getIDS("");	
		System.out.println("Success");
	}
	
	
}
