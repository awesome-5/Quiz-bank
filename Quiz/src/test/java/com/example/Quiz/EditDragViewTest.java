package com.example.Quiz;

import org.junit.Test;

public class EditDragViewTest {
	@Test
	public void checkMethod() {
		EditDragView edv = new EditDragView(); 
		edv.getIDS("1");	
		System.out.println("Success");
	}
	@Test
	public void getQuestions() {
		EditDragView edv = new EditDragView(); 
		edv.getQuestions("1");	
		System.out.println("Success");
	}
	
	@Test
	public void updateFromGrid() {
		EditDragView edv = new EditDragView(); 
		edv.updateFromGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateNewGrid() {
		EditDragView edv = new EditDragView(); 
		edv.updateNewGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void postToDB() {
		EditDragView edv = new EditDragView(); 
		Question q = new Question();
		q.setId(1L);
		edv.questionObj.add(q);
		edv.postToDB(false);
		System.out.println("Success");
		System.out.println("Success");
	}
	
	@Test
	public void saveTest() {
		EditDragView edv = new EditDragView(); 
		edv.saveTest.click();	
		System.out.println("Success");
	}
	
}
