package com.example.Quiz;

public class Course {

	private String courseCode = "";
	private String courseName ="";


	/**
	 * Set the value of username
	 *
	 * @param username
	 *            new value of username
	 */
	
	public String getcourseCode() {
		return courseCode;
	}

	/**
	 * Set the value of courseCode
	 *
	 * @param courseCode
	 *            new value of courseCode
	 */
	public void setcourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
