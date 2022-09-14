package objects;

import driverProgram.*;
import linkedList.*;

// This class provides a template for creating "Teacher" objects
public class Teacher extends Person {
	String email;   // Teacher's school email
	
	/**
	 * Initializes the values for a "Teacher" object
	 * @param name The name of this Teacher
	 * @param id The ID of this Teacher
	 */
	public Teacher(String name, int id) {
		super (name, id);
	}
	
	/**
	 * Generate an email for this Teacher.
	 * Emails for Teacher with the same full name as other Teacher are distinguished from each other by numbers.
	 */
	public void generateEmail() {
		LinkedListTeacher list = DriverClass.getTeachers();
		
		int cnt = 1;   // "cnt" represents the number of unique current teachers having the name
		for (int i = 0; i < list.getSize(); i ++) {
			if (list.get(0, list.getHead(), i).getName().equalsIgnoreCase(super.getName())) {
				cnt ++;
			}
		}
		
		/* fullName[0] represents teacher's first name, fullName[fullName.length - 1] represents teacher's last name.
		 * Anything between fullName[0] and fullName[fullName.length - 1] is the middle name of the teacher. */
		String[] fullName = super.getName().split(" ");
		
		if (cnt == 1) email = fullName[0].toLowerCase() + "." + fullName[fullName.length - 1].toLowerCase() + "@tdsb.on.ca";
		else email = fullName[0].toLowerCase() + "." + fullName[fullName.length - 1].toLowerCase() + cnt + "@tdsb.on.ca";
	}
	
	/**
	 * Set this Teacher's email
	 * @param email This Teacher's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get this Teacher's email
	 * @return This Teacher's email
	 */
	public String getEmail() {
		return email;
	}
}