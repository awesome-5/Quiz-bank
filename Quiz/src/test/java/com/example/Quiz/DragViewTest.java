package com.example.Quiz;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public class DragViewTest {

	@Test
	public void checkNameMethod() {
		DragView dv = new DragView(); 
		dv.checkName("");	
		System.out.println("Success");
	}
	@Test
	public void updateGrid() {
		DragView dv = new DragView(); 
		dv.updateGrid();
		System.out.println("Success");

	}
	
	@Test
	public void updateNewGrid() {
		DragView dv = new DragView(); 
		dv.updateNewGrid();
		System.out.println("Success");

	}
	@Test
	public void postToDB() {
		DragView dv = new DragView(); 
		Question q = new Question();
		q.setId(1L);
		dv.questionObj.add(q);
		dv.postToDB(false);
		System.out.println("Success");

	}
	@Test
	public void enter() {
		DragView dv = new DragView(); 
		System.out.println("Success");

	}
	
}