package com.example.Quiz;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class TestViewTest {
//	  @Before
//	    void mockVaadin() {
//	        MockVaadin.setup();
//	    }
	  
	@Test
	public void getIDS() {
		TestView tv = new TestView(); 
		tv.getIDS("");	
		System.out.println("Success");
	}
	
	@Test
	public void getQuestions() {
		TestView tv = new TestView(); 
		tv.getQuestions("");	
		System.out.println("Success");
	}
	
	@Test
	public void updateDraftGrid() {
		TestView tv = new TestView(); 
		tv.updateDraftGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateDraftGrid2() {
		TestView.draftTestFlag=false;
		TestView.draftExamFlag=true;
		TestView tv = new TestView(); 
		tv.updateDraftGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateDraftGrid3() {
		TestView.draftTestFlag=false;
		TestView.draftExamFlag=false;
		TestView tv = new TestView(); 
		tv.updateDraftGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateDraftGrid4() {
		TestView.draftTestFlag=true;
		TestView.draftExamFlag=false;
		TestView tv = new TestView(); 
		tv.updateDraftGrid();	
		System.out.println("Success");
	}
	
	
	@Test
	public void updateFinalGrid() {
		TestView.finalTestFlag=true;
		TestView.finalExamFlag=true;
		TestView tv = new TestView(); 
		tv.updateFinalGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateFinalGrid2() {
		TestView.finalTestFlag=true;
		TestView.finalExamFlag=false;
		TestView tv = new TestView(); 
		tv.updateFinalGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateFinalGrid3() {
		TestView.finalTestFlag=false;
		TestView.finalExamFlag=false;
		TestView tv = new TestView(); 
		tv.updateFinalGrid();	
		System.out.println("Success");
	}
	
	@Test
	public void updateFinalGrid4() {
		TestView.finalTestFlag=false;
		TestView.finalExamFlag=true;
		TestView tv = new TestView(); 
		tv.updateFinalGrid();	
		System.out.println("Success");
	}
	
	
	
	@Test
	public void updateDraftGridWithParams() {
		TestView tv = new TestView(); 
		tv.updateDraftGrid("");	
		System.out.println("Success");
	}
	
	@Test
	public void updateFinalGridWithParams() {
		TestView tv = new TestView(); 
		tv.updateFinalGrid("");	
		System.out.println("Success");
	}
	
	@Test
	public void changeQuestions() {
		TestView tv = new TestView(); 
		tv.changeQuestions("");	
		System.out.println("Success");
	}
	
//	@Test
//	public void enterTest() {
//		UI layout = UI.getCurrent();
//
//		TestView tv = new TestView(); 
//	    ViewChangeListener.ViewChangeEvent viewChangeEvent = Mockito.mock(ViewChangeListener.ViewChangeEvent.class);
//		tv.enter(viewChangeEvent);
//		Assert.assertTrue(TestView.flag);	
//}
	
	
}


//dv.backBtn.click();
		//assertEquals("Course Name Should Equal Test", course.getCourseName(),"Test");
		