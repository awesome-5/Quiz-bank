package com.example.Quiz;

import org.junit.Test;

public class HomePageTest {
	@Test
	public void updateGrid() {
		HomePage hp = new HomePage(); 
		hp.updateGrid();	
		System.out.println("Success");
	}
	
		@Test
		public void updateGrid2() {
			HomePage hp = new HomePage(); 
			hp.updateGrid("");	
			System.out.println("Success");
		}
}
