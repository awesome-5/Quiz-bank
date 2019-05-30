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
		System.out.println("Success");

	}	
	@Test
	public void shouldReturnCourseCode() {
		Course course = new Course(); 
		course.setcourseCode("Test");
		assertEquals("Course Code Should Equal Test", course.getcourseCode(),"Test");
		System.out.println("Success");
	}	
}

//- "sudo apt-get update"
//- "sudo apt-get install dbus-x11"
//- "export DISPLAY=:99.0"
//- "sh -e /etc/init.d/xvfb start"
//- "export CHROME_BIN=/usr/bin/google-chrome"
//- "sudo apt-get install -y libappindicator1 fonts-liberation"
//- "wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb"
//- "sudo dpkg -i google-chrome*.deb"
//- "wget https://chromedriver.storage.googleapis.com/2.38/chromedriver_linux64.zip"
//- "unzip chromedriver_linux64.zip"
//- "sudo cp chromedriver /usr/bin"
//- "wget https://github.com/mozilla/geckodriver/releases/download/v0.20.1/geckodriver-v0.20.1-linux64.tar.gz"
//- "tar -xzf geckodriver-v0.20.1-linux64.tar.gz"
//- "sudo cp geckodriver /usr/bin"
//dist: trusty