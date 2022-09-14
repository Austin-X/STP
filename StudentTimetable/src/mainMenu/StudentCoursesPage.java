package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class StudentCoursesPage extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private MyButton finishButton;
	
	// All of the courses in our school stored in a 29 x 7 array called "courses" of type "String"
	private final String[][] courses = 
	{{"", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 12", "Grade 12"},
	{"English", "<html>ENG1D1 - Grade 9 English<br>Prerequisite(s): none</html>", "<html>ENG2D1 - Grade 10 English<br>Prerequisite(s): ENG1D1 or ENG1D7</html>", "<html>ENG3U1 - Grade 11 English<br>Prerequisite(s): ENG2D1 or ENG2D7</html>", "<html>ENG4U1 - Grade 12 English<br>Prerequisite(s): ENG3U1 or ENG3U7</html>", "<html>ETS4U1 - Grade 12 Film Studies<br>Prerequisite(s): ENG3U1 or ENG3U7</html>", ""},
	{"Math", "<html>MTH1W1 - Grade 9 Mathematics<br>Prerequisite(s): none</html>", "<html>MPM2D1 - Grade 10 Principles of Mathematics<br>Prerequisite(s): MTH1W1 or MPM1D7</html>", "<html>MCR3U1 - Grade 11 Functions<br>Prerequisite(s): MPM2D1 or MPM2D7</html>", "<html>MHF4U1 - Grade 11 Advanced Functions<br>Prerequisite(s): MCR3U1 or MCR3U7</html>", "<html>MCV4U1 - Grade 12 Calculus and Vectors<br>Prerequisite(s): MHF4U1 or MHF4U7</html>", ""},
	{"Science", "<html>SNC1D1 - Grade 9 Science<br>Prerequisite(s): none</html>", "<html>SNC2D1 - Grade 10 Science<br>Prerequisite(s): SNC1D1 or SNC1D7</html>", "", "", "", ""},
	{"Computer Studies", "", "<html>ICS2O1 - Grade 10 Introduction to Computer Studies<br>Prerequisite(s): none</html>", "<html>ICS3U1 - Grade 11 Introduction to Computer Science<br>Prerequisite(s): none</html>", "<html>ICS4U1 - Grade 12 Computer Science<br>Prerequisite(s): ICS3U1 or ICS3U7</html>", "", ""},
	{"Chemistry", "", "", "<html>SCH3U1 - Grade 11 Chemistry<br>Prerequisite(s): SNC2D1 or SNC2D7</html>", "<html>SCH4U1 - Grade 12 Chemistry<br>Prerequisite(s): SCH3U1 or SCH3U7</html>", "", ""},
	{"Physics", "", "", "<html>SPH3U1 - Grade 11 Physics<br>Prerequisite(s): SNC2D1 or SNC2D7</html>", "<html>SPH4U1 - Grade 12 Physics<br>Prerequisite(s): SPH3U1 or SPH3U7</html>", "", ""},
	{"Biology", "", "", "<html>SBI3U1 - Grade 11 Biology<br>Prerequisite(s): SNC2D1 or SNC2D7</html>", "<html>SBI4U1 - Grade 12 Biology<br>Prerequisite(s): SBI3U1 or SBI3U7</html>", "", ""},
	{"French", "<html>FSF1D1 - Grade 9 Core French<br>Prerequisite(s): none</html>", "<html>FSF2D1 - Grade 10 Core French<br>Prerequisite(s): FSF1D1 or FSF1D7</html>", "<html>FSF3U1 - Grade 11 Core French<br>Prerequisite(s): FSF2D1 or FSF2D7</html>", "<html>FSF4U1 - Grade 12 Core French<br>Prerequisite(s): FSF3U1 or FSF3U7</html>", "", ""},
	{"Gym (Males)", "<html>PPL1OM - Grade 9 Healthy Active Living Education (M)<br>Prerequisite(s): none</html>", "<html>PPL2OM - Grade 10 Healthy Active Living Education (M)<br>Prerequisite(s): none</html>", "<html>PPL3OM - Grade 11 Healthy Active Living Education (M)<br>Prerequisite(s): none</html>", "<html>PPL4OM - Grade 12 Healthy Active Living Education (M)<br>Prerequisite(s): none</html>", "", ""},
	{"Gym (Females)", "<html>PPL1OF - Grade 9 Healthy Active Living Education (F)<br>Prerequisite(s): none</html>", "<html>PPL2OF - Grade 10 Healthy Active Living Education (F)<br>Prerequisite(s): none</html>", "<html>PPL3OF - Grade 11 Healthy Active Living Education (F)<br>Prerequisite(s): none</html>", "<html>PPL4OF - Grade 12 Healthy Active Living Education (F)<br>Prerequisite(s): none</html>", "", ""},
	{"World Studies", "<html>CGC1D1 - Grade 9 Issues in Canadian Geography<br>Prerequisite(s): none</html>", "<html>CHC2D1 - Grade 10 Canadian History since World War I<br>Prerequisite(s): none</html>", "<html>CLU3M1 - Grade 11 Understanding Canadian Law<br>Prerequisite(s): CHC2D1</html>", "<html>CLN4U1 - Grade 12 Canadian and International Law<br>Prerequisite(s): CLU3M1</html>", "", ""},
	{"Art", "<html>NAC101 - Grade 9 Expressing Aboriginal Cultures<br>Prerequisite(s): none</html>", "<html>AVI2O1 - Grade 10 Visual Arts<br>Prerequisite(s): none</html>", "<html>AVI3M1 - Grade 11 Visual Arts<br>Prerequisite(s): AVI2O1</html>", "<html>AVI4M1 - Grade 12 Visual Arts<br>Prerequisite(s): AVI3M1</html>", "", ""},
	{"Business Studies", "<html>BTT1O1 - Grade 9 Information and Communication Technology in Business<br>Prerequisite(s): none</html>", "<html>BBI2O1 - Grade 10 Introduction to Business<br>Prerequisite(s): none</html>", "<html>BAF3M1 - Grade 11 Financial Accounting Fundamentals<br>Prerequisite(s): none</html>", "<html>BAT4M1 - Grade 12 Financial Accounting Principles<br>Prerequisite(s): BAF3M1</html>", "<html>BBB4M1 - Grade 12 International Business Fundamentals<br>Prerequisite(s): BAF3M1</html>", ""},
	{"Technological Education", "<html>TIJ1O1 - Grade 9 Exploring Technologies<br>Prerequisite(s): none</html>", "<html>TEJ2O1 - Grade 10 Computer Technology<br>Prerequisite(s): none</html>", "<html>TEJ3M1 - Grade 11 Computer Engineering Technology<br>Prerequisite(s): none</html>", "<html>TEJ4M1 - Grade 12 Computer Engineering Technology<br>Prerequisite(s): TEJ3M1</html>", "", ""},
	{"", "", "", "", "", "", ""},
	{"English (IB)", "<html>ENG1D7 - Grade 9 English (IB)<br>Prerequisite(s): none</html>", "<html>ENG2D7 - Grade 10 English (IB)<br>Prerequisite(s): ENG1D7</html>", "<html>ENG3U7 - Grade 11 English (IB)<br>Prerequisite(s): ENG2D7</html>", "<html>ENG4U7 - Grade 12 English (IB)<br>Prerequisite(s): ENG3U7</html>", "<html>ETS4U7 - Grade 12 Studies in Literature (IB)<br>Prerequisite(s): ENG3U7</html>", ""},
	{"Math (IB)", "<html>MPM1D7 - Grade 9 Principles of Mathematics (IB)<br>Prerequisite(s): none</html>", "<html>MPM2D7 - Grade 10 Principles of Mathematics (IB)<br>Prerequisite(s): MPM1D7</html>", "<html>MCR3U7 - Grade 11 Functions (IB)<br>Prerequisite(s): MPM2D7</html>", "<html>MDM4U7 - Grade 12 Mathematics of Data Management (IB)<br>Prerequisite(s): MCR3U7</html>", "<html>MHF4U7 - Grade 12 Advanced Functions (IB)<br>Prerequisite(s): MCR3U7</html>", "<html>MCV4U7 - Grade 12 Calculus and Vectors (IB)<br>Prerequisite(s): MCR3U7</html>"},
	{"Science (IB)", "<html>SNC1D7 - Grade 9 Science (IB)<br>Prerequisite(s): none</html>", "<html>SNC2D7 - Grade 10 Science (IB)<br>Prerequisite(s): SNC1D7</html>", "", "", "", ""},
	{"Computer Studies (IB)", "", "<html>ICS2O7 - Grade 10 Introduction to Computer Studies (IB)<br>Prerequisite(s): none</html>", "<html>ICS3U7 - Grade 11 Introduction to Computer Science (IB)<br>Prerequisite(s): ICS2O7</html>", "<html>ICS4U7 - Grade 12 Computer Science (IB)<br>Prerequisite(s): ICS3U7</html>", "", ""},
	{"Chemistry (IB)", "", "", "<html>SCH3U7 - Grade 11 Chemistry (IB)<br>Prerequisite(s): SNC2D7</html>", "<html>SCH4UP - Grade 12 Chemistry (IB)<br>Prerequisite(s): SCH3U7</html>", "", ""},
	{"Physics (IB)", "", "", "<html>SPH3U7 - Grade 11 Physics (IB)<br>Prerequisite(s): SNC2D7</html>", "<html>SPH4U7 - Grade 12 Physics (IB)<br>Prerequisite(s): SPH3U7</html>", "", ""},
	{"Biology (IB)", "", "", "<html>SBI3U7 - Grade 11 Biology (IB)<br>Prerequisite(s): SNC2D7</html>", "<html>SBI4U7 - Grade 12 Biology (IB)<br>Prerequisite(s): SBI3U7</html>", "", ""},
	{"French (IB)", "<html>FSF1D7 - Grade 9 Core French (IB)<br>Prerequisite(s): none</html>", "<html>FSF2D7 - Grade 10 Core French (IB)<br>Prerequisite(s): FSF1D7</html>", "<html>FSF3U7 - Grade 11 Core French (IB)<br>Prerequisite(s): FSF2D7</html>", "<html>FSF4U7 - Grade 12 Core French (IB)<br>Prerequisite(s): FSF3U7</html>", "", ""},
	{"Geography (IB)", "<html>CGC1D7 - Grade 9 Issues in Canadian Geography (IB)<br>Prerequisite(s): none</html>", "", "<html>CGF3M7 - Grade 11 Forces of Nature: Physical Processes and Disasters (IB)<br>Prerequisite(s): CGC1D7</html>", "<html>CGW4U7 - Grade 12 World Issues: A Geographic Analysis (IB)<br>Prerequisite(s): CGF3M7</html>", "", ""},
	{"History & Economics (IB)", "", "<html>CHC2D7 - Grade 10 Canadian History since World War I (IB)<br>Prerequisite(s): none</html>", "<html>CIE3M7 - Grade 11 The Individual & the Economy (IB)<br>Prerequisite(s): CHC2D7</html>", "<html>CIA4U7 - Grade 12 Analysing Current Economic Issues (IB)<br>Prerequisite(s): CIE3M7</html>", "<html>BBB4M7 - Grade 12 Economics (IB HL)<br>Prerequisite(s): CIA4U7</html>", ""},
	{"Theory Of Knowledge (IB)", "", "", "", "<html>HZT4U7 - Grade 12 Philosophy: Questions and Theories (IB)<br>Prerequisite(s): ENG2D7</html>", "", ""},
	{"", "", "", "", "", "", ""},
	{"Spares", "", "", "", "Grade 12 Spare #1", "Grade 12 Spare #2", ""}};
	
	private Button[][] buttonArr;   // Array of buttons that will be used to represent the course codes of each course
	private boolean[][] completedCourses, currentCourses, possibleCourses;   // Arrays to store the student's completed courses, current courses, and their possible courses to take

	private boolean userHasClickedCompletedCourses;   // This variable is "true" if the user has clicked the "Edit this student's completed courses" button. Otherwise, this variable is false.
	
	private MyTextLabel infoLabel;
	
	private LinkedListCourses completedCoursesList, currentCoursesList;
	
	private Student stu;   // The student associated with this page
	
	private JPanel canvas;
	
	/**
	 * Initializes the values for a "StudentCoursesPage" object
	 * @param str The title of this "StudentCoursesPage" object
	 * @param s The Student associated with this "StudentCoursesPage" object
	 */
	public StudentCoursesPage(String str, Student s) {
		super (str);
		
		setSize(1100, 800);
		setVisible(false);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		stu = s;

		canvas = new JPanel();
		canvas.setLayout(new GridLayout(0, 7));

		buttonArr = new Button[courses.length][courses[0].length];
		completedCourses = new boolean[courses.length][courses[0].length];
		currentCourses = new boolean[courses.length][courses[0].length];
		possibleCourses = new boolean[courses.length][courses[0].length];

		userHasClickedCompletedCourses = false;

		completedCoursesList = new LinkedListCourses();
		currentCoursesList = new LinkedListCourses();

		infoLabel = new MyTextLabel("<html>Orange button(s) represent the student's completed course(s). Green button(s) represent the student's current course(s).<br>Red button(s) represent the course(s) that the student cannot select because they do not have the prerequisites for it.<br>Left click on a button to select or deselect a course. Right click on a button to view its course description and prerequisites.</html>", SwingConstants.CENTER);
		infoLabel.setTextLabel(400, 40, 0, 0, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 14));

		finishButton = new MyButton("Finish");
		finishButton.addActionListener(this);

		add(canvas);

		add(infoLabel, BorderLayout.NORTH);
		add(finishButton, BorderLayout.EAST);
	}
		
	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }
	
	/**
	 * Setting whether or not the user has clicked "Edit this student's completed courses" button or the "Edit this student's current courses" button
	 * @param b "True" if the user has clicked the "Edit this student's completed courses" button, "false" if the user has clicked the "Edit this student's current courses" button
	 */
	public void setBool(boolean b) {
		userHasClickedCompletedCourses = b;
	}

	/**
	 * Set the 2D array of completed courses for this Student
	 * @param c The linked list of courses representing all of this Student's completed courses
	 */
	public void setCompletedCourses(LinkedListCourses c) {  // 'c' is a doubly linked list of courses
		completedCourses = new boolean[courses.length][courses[0].length];  // Each cell in this 2D array relates to a specific available or non-available course
		
		for (int a = 0; a < c.getSize(); a ++) {  // Looping through the elements of linked list 'c'
			String courseCode = c.get(0, c.getHead(), a).getCourseCode();  // Getting the course code from linked list 'c' at index 'a'
			
			// Nested 'for' loop that loops through all cells of the "completedCourses" 2D boolean array to find which cell is related to the "courseCode" String
			outerloop: 
			for (int i = 1; i < courses.length; i ++) {
				for (int j = 1; j < courses[0].length; j ++) {
					if (courses[i][j].length() == 0) continue;  // If there is no text in a particular cell, then this cell is empty and does not contain a course
					
					String check;
					// Finding the course code associated with a particular cell and assigning it to the "check" variable
					if (i == courses.length - 1) check = courses[i][j];  
					else check = courses[i][j].substring(6, 12);
					
					// If the course code of linked list 'c' matches the cell's course code with row 'i' and column 'j', then mark this cell as a completed course and exit "outerloop"
					if (courseCode.equals(check)) {  
						completedCourses[i][j] = true;
						break outerloop;
					}
				}
			}
		}
		
		completedCoursesList = c;  // Along with setting the "completedCourses" 2D boolean array, set the "completedCoursesList" as well
	}
	
	/**
	 * Set the 2D array of current courses for this Student
	 * @param c The linked list of courses representing all of this Student's current courses
	 */
	public void setCurrentCourses(LinkedListCourses c) {
		currentCourses = new boolean[courses.length][courses[0].length];

		for (int a = 0; a < c.getSize(); a ++) {
			String courseCode = c.get(0, c.getHead(), a).getCourseCode();
			
			outerloop: 
			for (int i = 1; i < courses.length; i ++) {
				for (int j = 1; j < courses[0].length; j ++) {
					if (courses[i][j].length() == 0) continue;

					String check;
					if (i == courses.length - 1) check = courses[i][j];
					else check = courses[i][j].substring(6, 12);
					if (courseCode.equals(check)) {
						currentCourses[i][j] = true;
						break outerloop;
					}
				}
			}
		}
		
		currentCoursesList = c;
	}
	
	/**
	 * Get the 2D array representing this Student's completed courses
	 * @return The 2D array representing this Student's completed courses
	 */
	public boolean[][] getCompletedCourses() {
		return completedCourses;
	}
	
	/**
	 * Get the 2D array representing this Student's current courses
	 * @return The 2D array representing this Student's current courses
	 */
	public boolean[][] getCurrentCourses() {
		return currentCourses;
	}
	
	/**
	 * Get this Student's completed courses list
	 * @return This Student's completed courses list
	 */
	public LinkedListCourses getCompletedCoursesList() {
		return completedCoursesList;
	}
	
	/**
	 * Get this Student's current courses list
	 * @return This Student's current courses list
	 */
	public LinkedListCourses getCurrentCoursesList() {
		return currentCoursesList;
	}
	
	/**
	 * Setting the page by: Highlighting the student's completed courses with orange buttons, highlighting the student's current courses with green buttons,
	 * highlighting the courses that the student is not allowed to take with red buttons, and highlighting the student's possible courses to select with white buttons.
	 * All other buttons are used for information and have no purpose when they are clicked (brown, black, and teal buttons)
	 */
	public void setPage() {	
		if (stu.getGrade() == 12) {   // If student is currently in grade 12, they are allowed to select spares as their courses
			possibleCourses[courses.length - 1][4] = true;
			possibleCourses[courses.length - 1][5] = true;
		} 
		
		// Nested "for" loop used to locate all of the buttons with course codes on them
		for (int i = 1; i < courses.length - 1; i ++) {
			for (int j = 1; j < courses[0].length; j ++) {
				
				// If the student is not currently in the pre-IB or IB program , they cannot select a pre-IB or IB course as one of their current courses. 
				if (i >= 16 && j <= 26) {
					if (!stu.isInIB()) {
						possibleCourses[i][j] = false;
						break;
					}
				}
				
				if (courses[i][j].length() != 0) {
					String[] str = courses[i][j].split(" ");
					
					boolean flag = false;
					
					// Locating the prerequisites of the current course to see if the student has the prerequisites to take this course
					for (int k = 2; k < str.length; k ++) {
						if (str[k].equals("none</html>")) {   // If there are no prerequisites for the course, then any student is allowed to take it
							flag = true;
							break;
						}
						
						if (str[k].length() >= 6 && Character.isDigit(str[k].charAt(3)) && Character.isDigit(str[k].charAt(5))) {   // If the current button is a valid course button
							if (str[k].length() > 6) str[k] = str[k].substring(0, 6);


							if (completedCoursesList.contains(str[k])) {   // If the student has one of the prerequisites for the current course, they are allowed to take it
								flag = true;
							}
						}
					}
					
					possibleCourses[i][j] = flag;
				}
			}
		}
				
		// Displaying the top row of the buttons
		buttonArr[0][0] = new Button(courses[0][0]);
		canvas.add(buttonArr[0][0]);
		for (int i = 1; i < courses[0].length; i ++) {
			buttonArr[0][i] = new Button(courses[0][i]);
			buttonArr[0][i].setBackground(Color.CYAN);
			canvas.add(buttonArr[0][i]);
		}

		// Displaying the colours of the buttons based on the student's course selection
		for (int i = 1; i < courses.length; i ++) {
			for (int j = 0; j < courses[0].length; j ++) {	
				if (courses[i][j].length() == 0) {
					buttonArr[i][j] = new Button("");
					buttonArr[i][j].setBackground(Color.BLACK);
					buttonArr[i][j].addActionListener(this);
					buttonArr[i][j].addMouseListener(this);
				} else if (j == 0) {
					buttonArr[i][j] = new Button(courses[i][j]);
					buttonArr[i][j].setBackground(new Color(222,184,135));
				} else {
					if (i == courses.length - 1) {
						buttonArr[i][j] = new Button(courses[i][j]);
						if (userHasClickedCompletedCourses || stu.getGrade() != 12) buttonArr[i][j].setBackground(Color.RED);
					}
					else buttonArr[i][j] = new Button(courses[i][j].substring(6, 12));

					if (completedCourses[i][j]) buttonArr[i][j].setBackground(Color.ORANGE);
					else if (currentCourses[i][j]) buttonArr[i][j].setBackground(Color.GREEN);
					else if (!userHasClickedCompletedCourses) {
						if (courses[i][j].charAt(11) == '7' && !stu.isInIB()) buttonArr[i][j].setBackground(Color.RED);
						else if (!possibleCourses[i][j]) buttonArr[i][j].setBackground(Color.RED);
					}
					buttonArr[i][j].addActionListener(this);
					buttonArr[i][j].addMouseListener(this);
				}
				canvas.add(buttonArr[i][j]);
			}
		}
		add(canvas);
	}
	
	// Find the course related to a certain button
	/**
	 * Find the Course associated with a certain button
	 * @param b The button that is used to find the Course associated with it
	 * @return the Course associated with button 'b'
	 */
	public String findButtonString(Button b) {
		for (int i = 1; i < courses.length; i ++) {
			for (int j = 1; j < courses[0].length; j ++) {
				if (buttonArr[i][j] == b) return courses[i][j];
			}
		}
		return null;
	}
	
	/**
	 * Remove all buttons from the canvas
	 */
	public void resetButtons() {
		for (int i = 0; i < courses.length; i ++) {
			for (int j = 0; j < courses[0].length; j ++) {
				canvas.remove(buttonArr[i][j]);
			}
		}
	}
	
	/**
	 * Setting the student's linked lists for their completed and current courses
	 */
	public void setLists() {
		completedCoursesList = new LinkedListCourses();
		currentCoursesList = new LinkedListCourses();
			
		for (int i = 1; i < courses.length; i ++) {
			for (int j = 1; j < courses[0].length; j ++) {
				if (completedCourses[i][j]) {	
					completedCoursesList.addLast(new Course(courses[i][j].substring(6, 12)));
				}
				if (currentCourses[i][j]) {
					if (i == courses.length - 1) currentCoursesList.addLast(new Course(courses[i][j]));
					else currentCoursesList.addLast(new Course(courses[i][j].substring(6, 12)));
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Component button = (Component) e.getSource();
		
		if (button.equals(finishButton)) {   // The user has finished updating the student's completed/current courses and wants to exit the page	
			setLists();
			resetButtons();
			setVisible(false);
			if (userHasClickedCompletedCourses) JOptionPane.showMessageDialog(this, "The student's completed courses has been updated successfully.");
			else JOptionPane.showMessageDialog(this, "The student's current courses has been updated successfully.");
			
			if (userHasClickedCompletedCourses) {   // User has initially chosen to update the student's completed courses
				
				// Adding the event of updating the student's completed courses to the history page.
				String str = stu.getName() + "'s completed courses has been updated to: {";
				for (int i = 0; i < completedCoursesList.getSize(); i ++) {
					str += completedCoursesList.get(0, completedCoursesList.getHead(), i).getCourseCode();
					if (i != completedCoursesList.getSize() - 1) str += ", ";
				}
				str += "}.";
				DriverClass.addEventToHistory(str, true);
				
				// Adding the event of updating the student's completed courses to the "StudentsInfo.txt" RandomAccessFile.
				try {
					RandomAccessFile studentsInfo = new RandomAccessFile("StudentsInfo.txt", "rw");
					studentsInfo.seek(studentsInfo.length());
					
					str = "Edit_Completed Courses_" + stu.getID() + "_";
					for (int i = 0; i < completedCoursesList.getSize(); i ++) {
						str += completedCoursesList.get(0, completedCoursesList.getHead(), i).getCourseCode() + "_";
					}
					studentsInfo.writeBytes(str + "\n");
					studentsInfo.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {   // User has initially chosen to update the student's current courses
				
				// Adding the event of updating the student's current courses to the history page.
				String str = stu.getName() + "'s current courses has been updated to: {";
				for (int i = 0; i < currentCoursesList.getSize(); i ++) {
					str += currentCoursesList.get(0, currentCoursesList.getHead(), i).getCourseCode();
					if (i != currentCoursesList.getSize() - 1) str += ", ";
				}
				str += "}.";
				DriverClass.addEventToHistory(str, true);
				
				// Adding the event of updating the student's current courses to the "StudentsInfo.txt" RandomAccessFile.
				try {
					RandomAccessFile studentsInfo = new RandomAccessFile("StudentsInfo.txt", "rw");
					studentsInfo.seek(studentsInfo.length());

					str = "Edit_Current Courses_" + stu.getID() + "_";
					for (int i = 0; i < currentCoursesList.getSize(); i ++) {
						str += currentCoursesList.get(0, currentCoursesList.getHead(), i).getCourseCode() + "_";
					}
					studentsInfo.writeBytes(str + "\n");
					studentsInfo.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {   // User has clicked on a button that was not the "finish" button
			
			boolean appropriateButton = false;   // Used to denote whether the user's clicked button is appropriate. (Black-coloured buttons are not appropriate buttons)
			
			if (userHasClickedCompletedCourses) {   // User has initially chosen to update the student's completed courses
				outerloop: for (int i = 1; i < courses.length; i ++) {
					for (int j = 1; j < courses[0].length; j ++) {
						if (courses[i][j].length() != 0 && button.equals(buttonArr[i][j])) {

							if (i == courses.length - 1) {
								JOptionPane.showMessageDialog(this, "It is impossible to have completed a spare in grades 9, 10, and 11.", "Warning", JOptionPane.WARNING_MESSAGE);
							}
							else if (currentCourses[i][j]) {
								JOptionPane.showMessageDialog(this, "This action cannot be completed because this student is currently taking this course.", "Warning", JOptionPane.WARNING_MESSAGE);
							}
							else if (completedCourses[i][j]) {
								button.setBackground(null);
								completedCourses[i][j] = false;
							} else {
								button.setBackground(Color.ORANGE);
								completedCourses[i][j] = true;
							}
							appropriateButton = true;
							break outerloop;
						}
					}
				}	
			} else {   // User has initially chosen to update the student's current courses
				outerloop: for (int i = 1; i < courses.length; i ++) {
					for (int j = 1; j < courses[0].length; j ++) {
						if (courses[i][j].length() != 0 && button.equals(buttonArr[i][j])) {
							if (completedCourses[i][j]) {
								JOptionPane.showMessageDialog(this, "Students cannot enroll in courses that they have completed.", "Warning", JOptionPane.WARNING_MESSAGE);
							} else if (!possibleCourses[i][j]) {
								if (i >= 16 && i <= 26 && !stu.isInIB()) JOptionPane.showMessageDialog(this, "This student cannot take this course because they are currently not in the pre-IB/IB program.", "Warning", JOptionPane.WARNING_MESSAGE);
								else {
									if (i == courses.length - 1 && stu.getGrade() != 12) {
										JOptionPane.showMessageDialog(this, "Only grade 12 students can select spares.", "Warning", JOptionPane.WARNING_MESSAGE);
									}
									else {
										JOptionPane.showMessageDialog(this, "This student does not have the prerequisite(s) for this course yet.", "Warning", JOptionPane.WARNING_MESSAGE);
									}
								}
							} else if (!currentCourses[i][j]){
								button.setBackground(Color.GREEN);
								currentCourses[i][j] = true;
							} else if (currentCourses[i][j]) {
								button.setBackground(null);
								currentCourses[i][j] = false;
								appropriateButton = true;
							}
							appropriateButton = true;
							break outerloop;
						}
					}
				}
			}

			if (!appropriateButton) JOptionPane.showMessageDialog(this, "That is not an appropriate course to choose.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * This method is so if the user right clicks on a button, they can view the button's Course description and prerequisites needed for the Course.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Button button = (Button) e.getSource();
		if(SwingUtilities.isRightMouseButton(e)) {
			String str = findButtonString(button);
			if (str.length() == 0) JOptionPane.showMessageDialog(this, "That is not an appropriate course.", "Warning", JOptionPane.WARNING_MESSAGE);
			else JOptionPane.showMessageDialog(this, str);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
