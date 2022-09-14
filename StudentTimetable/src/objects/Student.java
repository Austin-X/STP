package objects;

import driverProgram.*;
import linkedList.*;
import mainMenu.*;

// This class provides a template for creating "Student" objects
public class Student extends Person {
	private int grade;   // Grade of student
	private boolean ib;   // Is "true" if student is in pre-IB or IB program, "false" otherwise
	private String email;   // The student's school email
		
	private StudentCoursesPage coursesPage;   // This object contains the completed & current courses of the student
	
	private String[] timetable;   // Timetable of the student
	
	/**
	 * Initializes the values for a "Student" object
	 */
	public Student() {
		super();
		grade = 0;
		ib = false;
		
		/* Timetable of student has length 8 because student must have 8 courses in a school year. 
		 * In this program, a spare period is counted as a course as well, for simplicity. */
		timetable = new String[8];   
		
		coursesPage = new StudentCoursesPage("Edit/View Student's Courses", this);	
	}
	
	/**
	 * Display this Student's completed and current courses
	 * @param showCompletedCourses "True" if the user wants to show the page of this Student's completed courses, "false" if the user wants to show the page of this Student's current courses 
	 */
	public void displayCoursesPage(boolean showCompletedCourses) {
		coursesPage.setBool(showCompletedCourses);
		coursesPage.setPage();
		coursesPage.setVisible(true);
	}
	
	/**
	 * Set the timetable for this Student
	 * @param timeTbl The timetable of this Student
	 */
	public void setTimetable(String[] timeTbl) {
		timetable = timeTbl;
	}
	
	/**
	 * Get the timetable of this Student
	 * @return The timetable of this Student
	 */
	public String[] getTimetable() {
		return timetable;
	}
	
	/**
	 * Set the grade for this Student
	 * @param grade The grade of this Student
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/**
	 * Get the grade of this Student
	 * @return The grade of this Student
	 */
	public int getGrade() {
		return grade;
	}
	
	/**
	 * Set whether or not this Student is in the pre-IB or IB program
	 * @param b "True" if this Student is in the pre-IB or IB program, "false" otherwise
	 */
	public void setIB(boolean b) {
		ib = b;
	}
	
	/**
	 * Check if this Student is in the pre-IB or IB program
	 * @return "True" if this Student is in the pre-IB or IB program, "false" otherwise
	 */
	public boolean isInIB() {
		return ib;
	}

	/**
	 * Set the linked lists for this Student's completed and current courses
	 */
	public void setLists() {
		coursesPage.setLists();
	}
	
	/**
	 * Get the 2D array representing this Student's completed courses
	 * @return The 2D array representing this Student's completed courses
	 */
	public boolean[][] getCompletedCourses() {
		return coursesPage.getCompletedCourses();
	}
	
	/**
	 * Set this Student's completed courses 2D array
	 * @param c A linked list of courses which is used to set this Student's completed courses 2D array
	 */
	public void setCompletedCourses(LinkedListCourses c) {
		coursesPage.setCompletedCourses(c);
	}
	
	/**
	 * Get the 2D array representing this Student's current courses
	 * @return The 2D array representing this Student's current courses
	 */
	public boolean[][] getCurrentCourses() {
		return coursesPage.getCurrentCourses();
	}

	/**
	 * Set this Student's current courses 2D array
	 * @param c A linked list of courses which is used to set this Student's current courses 2D array
	 */
	public void setCurrentCourses(LinkedListCourses c) {
		coursesPage.setCurrentCourses(c);
	}
	
	/**
	 * Get the list of this Student's completed courses
	 * @return The list of this Student's completed courses
	 */
	public LinkedListCourses getCompletedCoursesList() {
		return coursesPage.getCompletedCoursesList();
	}
	
	/**
	 * Get the list of this Student's current courses
	 * @return The list of this Student's current courses
	 */
	public LinkedListCourses getCurrentCoursesList() {
		return coursesPage.getCurrentCoursesList();
	}

	/**
	 * Generate an email for this Student.
	 * Emails for Students with the same full name as other Students are distinguished from each other by numbers.
	 */
	public void generateEmail() {
		LinkedListStudent list = DriverClass.getStudents();
		
		int cnt = 1;   // "cnt" represents the number of unique current students having the name
		for (int i = 0; i < list.getSize(); i ++) {
			if (list.get(0, list.getHead(), i).getName().equalsIgnoreCase(super.getName())) {
				cnt ++;
			}
		}
		
		/* fullName[0] represents student's first name, fullName[fullName.length - 1] represents student's last name.
		 * Anything between fullName[0] and fullName[fullName.length - 1] is the middle name of the student. */
		String[] fullName = super.getName().split(" ");
		
		if (cnt == 1) email = fullName[0].toLowerCase() + "." + fullName[fullName.length - 1].toLowerCase() + "@student.tdsb.on.ca";
		else email = fullName[0].toLowerCase() + "." + fullName[fullName.length - 1].toLowerCase() + cnt + "@student.tdsb.on.ca";
	}
	
	/**
	 * Set this Student's email
	 * @param email The email of this Student
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get this Student's email
	 * @return This Student's email
	 */
	public String getEmail() {
		return email;
	}
}