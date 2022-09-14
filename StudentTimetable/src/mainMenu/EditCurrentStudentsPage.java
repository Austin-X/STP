package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class EditCurrentStudentsPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static LinkedListStudent studentsList;   // Linked list of students who are currently in the database
		
	private MyButton sortStudentsAlphabeticallyButton, removeThisStudentButton;
	private MyButton editCompletedCoursesButton, editCurrentCoursesButton, generateTimetableButton, displayTimetableButton;
	private MyButton backButton;
	
	private MyTextLabel choiceLabel;
	private MyTextLabel timetableSemester1Label, timetableSemester2Label;
	private MyTextLabel[] timetableDayLabels;
	private MyTextLabel[] timetableTimePeriods;
	private MyButton[][] timetableSemester1, timetableSemester2;
	
	private Choice studentsMenu;
	
	private Student curTimetableShownStudent;   // Stores the info of the student whose timetable is currently shown
	
	private static boolean sorted;   // Stores whether the list of students is currently sorted alphabetically or not
	
	/**
	 * Initializes the values for a "EditCurrentStudentsPage" object
	 */
	public EditCurrentStudentsPage() {
		super ("Edit current students");
		
		setLayout(null);
		setSize(800, 800);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		sorted = false;

		curTimetableShownStudent = new Student();

		studentsList = new LinkedListStudent();

		choiceLabel = new MyTextLabel("Select a student below", SwingConstants.CENTER);
		choiceLabel.setTextLabel(400, 40, getWidth() / 2 - 200, 20, Color.BLACK, null, new Font("Arial", Font.BOLD, 30));

		studentsMenu = new Choice();
		studentsMenu.setBounds(getWidth() / 2 - 100, 75, 200, 40);

		for (int i = 0; i < studentsList.getSize(); i ++) {
			studentsMenu.add(studentsList.get(0, studentsList.getHead(), i).getName());
		}

		timetableSemester1Label = new MyTextLabel("Semester 1 timetable", SwingConstants.CENTER);
		timetableSemester1Label.setTextLabel(370, 25, getWidth() / 2 - 150, 180, Color.BLACK, null, new Font("Arial", Font.BOLD, 20));
		timetableSemester2Label = new MyTextLabel("Semester 2 timetable", SwingConstants.CENTER);
		timetableSemester2Label.setTextLabel(370, 25, getWidth() / 2 - 150, 470, Color.BLACK, null, new Font("Arial", Font.BOLD, 20));

		timetableSemester1 = new MyButton[4][4];
		timetableSemester2 = new MyButton[4][4];

		int timetable1curX = getWidth() / 2 - 140, timetable1curY = 230;
		int timetable2curX = getWidth() / 2 - 140, timetable2curY = 520;

		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				timetableSemester1[i][j] = new MyButton("");
				timetableSemester1[i][j].setButton(90, 50, timetable1curX, timetable1curY);

				timetable1curX += 90;

				timetableSemester2[i][j] = new MyButton("");
				timetableSemester2[i][j].setButton(90, 50, timetable2curX, timetable2curY);

				timetable2curX += 90;
			}
			timetable1curX = getWidth() / 2 - 140;
			timetable1curY += 50;

			timetable2curX = getWidth() / 2 - 140;
			timetable2curY += 50;
		}

		timetableDayLabels = new MyTextLabel[8];
		int curX = getWidth() / 2 - 140, curY = 210;
		for (int i = 0; i < 8; i ++) {
			timetableDayLabels[i] = new MyTextLabel("Day " + (i + 1), SwingConstants.CENTER);
			timetableDayLabels[i].setTextLabel(90, 20, curX, curY, Color.BLACK, Color.YELLOW, new Font("Arial", Font.PLAIN, 14));
			curX += 90;
		}
		curX = getWidth() / 2 - 140; curY = 500;
		for (int i = 4; i < 8; i ++) {
			timetableDayLabels[i] = new MyTextLabel("Day " + (i - 4 + 1), SwingConstants.CENTER);
			timetableDayLabels[i].setTextLabel(90, 20, curX, curY, Color.BLACK, Color.YELLOW, new Font("Arial", Font.PLAIN, 14));
			curX += 90;
		}

		timetableTimePeriods = new MyTextLabel[8];
		curX = getWidth() / 2 - 220; curY = 230;
		for (int i = 0; i < 4; i ++) {
			if (i == 0) timetableTimePeriods[i] = new MyTextLabel("08:45-10:05", SwingConstants.CENTER);
			else if (i == 1) timetableTimePeriods[i] = new MyTextLabel("10:10-11:25", SwingConstants.CENTER);
			else if (i == 2) timetableTimePeriods[i] = new MyTextLabel("12:25-13:45", SwingConstants.CENTER);
			else timetableTimePeriods[i] = new MyTextLabel("13:50-15:00", SwingConstants.CENTER);
			timetableTimePeriods[i].setTextLabel(80, 50, curX, curY, Color.BLACK, Color.YELLOW, new Font("Arial", Font.PLAIN, 14));
			curY += 50;
		}
		curX = getWidth() / 2 - 220; curY = 520;
		for (int i = 4; i < 8; i ++) {
			if (i - 4 == 0) timetableTimePeriods[i] = new MyTextLabel("08:45-10:05", SwingConstants.CENTER);
			else if (i - 4 == 1) timetableTimePeriods[i] = new MyTextLabel("10:10-11:25", SwingConstants.CENTER);
			else if (i - 4 == 2) timetableTimePeriods[i] = new MyTextLabel("12:25-13:45", SwingConstants.CENTER);
			else timetableTimePeriods[i] = new MyTextLabel("13:50-15:00", SwingConstants.CENTER);
			timetableTimePeriods[i].setTextLabel(80, 50, curX, curY, Color.BLACK, Color.YELLOW, new Font("Arial", Font.PLAIN, 14));
			curY += 50;
		}

		backButton = new MyButton("Back");
		backButton.setButton(160, 50, 0, 713);
		backButton.addActionListener(this);

		sortStudentsAlphabeticallyButton = new MyButton("<html>Sort list of student(s)<br>alphabetically</html>");
		sortStudentsAlphabeticallyButton.setButton(190, 40, 90, 65);
		sortStudentsAlphabeticallyButton.setBackground(Color.CYAN);
		sortStudentsAlphabeticallyButton.addActionListener(this);

		removeThisStudentButton = new MyButton("Remove this student");
		removeThisStudentButton.setButton(190, 30, getWidth() / 2 + 120, 70);
		removeThisStudentButton.setBackground(Color.RED);
		removeThisStudentButton.addActionListener(this);

		editCompletedCoursesButton = new MyButton("<html>Edit this student's<br>completed courses</html>");
		editCompletedCoursesButton.setButton(190, 50, 90, 110);
		editCompletedCoursesButton.addActionListener(this);

		editCurrentCoursesButton = new MyButton("<html>Edit this student's<br>current courses</html>");
		editCurrentCoursesButton.setButton(190, 50, getWidth() / 2 - 95, 110);
		editCurrentCoursesButton.addActionListener(this);

		generateTimetableButton = new MyButton("<html>Randomly generate this<br>student's current timetable</html>");
		generateTimetableButton.setButton(190, 50, getWidth() / 2 + 120, 110);
		generateTimetableButton.addActionListener(this);

		displayTimetableButton = new MyButton("<html>Display this student's<br>current timetable</html>");
		displayTimetableButton.setButton(110, 110, 50, 400);
		displayTimetableButton.setBackground(Color.GREEN);
		displayTimetableButton.addActionListener(this);

		add(choiceLabel);

		add(studentsMenu);

		add(removeThisStudentButton);

		add(sortStudentsAlphabeticallyButton);
		add(editCompletedCoursesButton);
		add(editCurrentCoursesButton);
		add(generateTimetableButton);
		add(displayTimetableButton);

		add(timetableSemester1Label);
		add(timetableSemester2Label);
		timetableSemester1Label.setVisible(false);
		timetableSemester2Label.setVisible(false);

		for (int i = 0; i < 8; i ++) {
		add(timetableDayLabels[i]);
		add(timetableTimePeriods[i]);
		timetableDayLabels[i].setVisible(false);
		timetableTimePeriods[i].setVisible(false);
		}

		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				add(timetableSemester1[i][j]);
				add(timetableSemester2[i][j]);
				timetableSemester1[i][j].setVisible(false);
				timetableSemester2[i][j].setVisible(false);
			}
		}

		removeThisStudentButton.setVisible(false);

		editCompletedCoursesButton.setVisible(false);
		editCurrentCoursesButton.setVisible(false);
		generateTimetableButton.setVisible(false);
		displayTimetableButton.setVisible(false);

		add(backButton);
	}
	
	/**
	 * Receiving the list of Students in the database from the main menu page and setting up the page layout from this list of Students
	 * @param list The list of Students currently in the database
	 */
	public void setStudentsList(LinkedListStudent list) {
		studentsList = list;

		remove(studentsMenu);
		
		studentsMenu = new Choice();
		studentsMenu.setBounds(getWidth() / 2 - 100, 75, 200, 40);

		for (int i = 0; i < studentsList.getSize(); i ++) {
			studentsMenu.add((i + 1) + ". " + studentsList.get(0, studentsList.getHead(), i).getName());
		}

		add(studentsMenu);

		if (studentsList.getSize() > 0) {   // List of students is empty, so hide all of the buttons and labels which are responsible for generating a student's timetable
			sortStudentsAlphabeticallyButton.setVisible(true);
			removeThisStudentButton.setVisible(true);

			timetableSemester1Label.setVisible(true);
			timetableSemester2Label.setVisible(true);

			for (int i = 0; i < 8; i ++) {
			timetableDayLabels[i].setVisible(true);
			timetableTimePeriods[i].setVisible(true);
			}

			for (int i = 0; i < 4; i ++) {
				for (int j = 0; j < 4; j ++) {
					timetableSemester1[i][j].setVisible(true);
					timetableSemester2[i][j].setVisible(true);
				}
			}

			editCompletedCoursesButton.setVisible(true);
			editCurrentCoursesButton.setVisible(true);
			generateTimetableButton.setVisible(true);
			displayTimetableButton.setVisible(true);
		} else {   // List of students is not empty, so display all of the buttons and labels necessary in generating a student's timetable
			sortStudentsAlphabeticallyButton.setVisible(false);
			removeThisStudentButton.setVisible(false);

			timetableSemester1Label.setVisible(false);
			    timetableSemester2Label.setVisible(false);

			    for (int i = 0; i < 8; i ++) {
				timetableDayLabels[i].setVisible(false);
				timetableTimePeriods[i].setVisible(false);
			    }

			    for (int i = 0; i < 4; i ++) {
				for (int j = 0; j < 4; j ++) {
					timetableSemester1[i][j].setVisible(false);
					timetableSemester2[i][j].setVisible(false);
				}
			    }

			editCompletedCoursesButton.setVisible(false);
			editCurrentCoursesButton.setVisible(false);
			generateTimetableButton.setVisible(false);
			displayTimetableButton.setVisible(false);
		}
	}

	/**
	 * Returns true if the list of Students on this page is currently sorted alphabetically
	 * @return "True" if the list of Students on this page is currently sorted alphabetically, "false" otherwise
	 */
	public static boolean wasSorted() {
		return sorted;
	}
	
	/**
	 * Receives a string from the list of Students and returns the Student associated with that string
	 * @param str The selected string from the Student's pop-up menu of choices
	 * @return The Student associated with the selected string in the Student's pop-up menu of choices
	 */
	private Student findStudent(String str) {	
		int idx = Character.getNumericValue(str.charAt(0)) - 1;   // The index of the student in the list
		Student stu = studentsList.get(0, studentsList.getHead(), idx);
		return stu;
	}
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			setVisible(false);
			
			studentsList.sortAlphabetically(false);
			DriverClass.displayMainMenu();
		} else if (button.equals(sortStudentsAlphabeticallyButton)) {   // User wants to either sort the list of students alphabetically or unsort it back into its original order
			if (!sorted) {   // Current list of students is not sorted alphabetically, so sort the list of students alphabetically
				studentsList.sortAlphabetically(true);
				setStudentsList(studentsList);
				sorted = true;
				
				sortStudentsAlphabeticallyButton.setBackground(Color.ORANGE);
				sortStudentsAlphabeticallyButton.setText("<html>Unsort the list back into<br>original order</html>");
			} else {   // Current list of students is sorted alphabetically, so unsort the list of students back into its original order
				studentsList.sortAlphabetically(false);
				setStudentsList(studentsList);
				sorted = false;
				
				sortStudentsAlphabeticallyButton.setBackground(Color.CYAN);
			    sortStudentsAlphabeticallyButton.setText("<html>Sort list of student(s)<br>alphabetically</html>");
			}
		} else if (button.equals(removeThisStudentButton)) {   // User wants to remove the currently selected student
			Student stu = findStudent(studentsMenu.getSelectedItem());
						
			// Removing the student from the "students" linked list in the main menu.
			studentsList.remove(stu);
			setStudentsList(studentsList);
			studentsList.updateOriginalList(stu);
			
			// Adding an event to the history page about the student being removed.
			DriverClass.addEventToHistory(stu.getName() + " has been removed as a student in this school.", true);

			// If the student being removed is the student whose timetable is currently shown, clear the timetable buttons and labels.
			if (stu == curTimetableShownStudent) {
				timetableSemester1Label.setText("Semester 1 timetable");
				timetableSemester2Label.setText("Semester 2 timetable");
				for (int i = 0; i < 4; i ++) {
				    for (int j = 0; j < 4; j ++) {
				    	timetableSemester1[i][j].setText("");
				    	timetableSemester2[i][j].setText("");
				    }
				}
			}
			
			// Inform the "StudentsInfo.txt" RandomAccessFile about removing the selected student
			try {
				RandomAccessFile studentsInfo = new RandomAccessFile("StudentsInfo.txt", "rw");
				studentsInfo.seek(studentsInfo.length());
				studentsInfo.writeBytes("Remove_" + stu.getID() + "\n");
				studentsInfo.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (button.equals(editCompletedCoursesButton)) {   // User wants to edit the selected student's completed courses
			Student stu = findStudent(studentsMenu.getSelectedItem());
			stu.displayCoursesPage(true);
		} else if (button.equals(editCurrentCoursesButton)) {   // User wants to edit the selected student's current courses
			Student stu = findStudent(studentsMenu.getSelectedItem());	
			stu.displayCoursesPage(false);
		} else if (button.equals(generateTimetableButton)) {   // User wants to generate the selected student's timetable
			Student stu = findStudent(studentsMenu.getSelectedItem());
			
			if (stu.getCurrentCoursesList().getSize() != 8) {
				JOptionPane.showMessageDialog(this, "You must select 8 current courses for student '" + studentsMenu.getSelectedItem().substring(3) + "' in order to generate their timetable.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				String[] parts = studentsMenu.getSelectedItem().split(" ");
				String firstName = parts[1];
				
				timetableSemester1Label.setText(firstName + "'s semester 1 timetable");	
				timetableSemester2Label.setText(firstName + "'s semester 2 timetable");	
				
				add(timetableSemester1Label);
				add(timetableSemester2Label);
				
				generateTimetable(stu);
				
				curTimetableShownStudent = stu;
			}
		} else if (button.equals(displayTimetableButton)) {   // User wants to display the selected student's timetable
			Student stu = findStudent(studentsMenu.getSelectedItem());
			
			String[] parts = studentsMenu.getSelectedItem().split(" ");
			String firstName = parts[1];	
			
			timetableSemester1Label.setText(firstName + "'s semester 1 timetable");	
			timetableSemester2Label.setText(firstName + "'s semester 2 timetable");	
			
			displayTimetable(stu.getTimetable());
			
			curTimetableShownStudent = stu;
		}
	}
	
	/**
	 * Generating a random timetable for the selected Student and displaying it
	 * @param stu The Student whose timetable is to be randomly generated and displayed
	 */
	public void generateTimetable(Student stu) {
		LinkedListCourses list = stu.getCurrentCoursesList();  // List of the student's current courses
		
		boolean[] visited = new boolean[8];  // Maximum of 8 courses in a timetable
		
		/* Each cell in this "timetable" array represents a unique course. 
		 * The ordering of these courses represent the ordering of the student's timetable. */
		String[] timetable = new String[8];  // Maximum of 8 courses in a timetable  
		
		// Algorithm used to randomly generate the student's timetable and store it in the "timetable" array
		for (int i = 0; i < 8; i ++) {  // 8 Courses to be randomly assigned to student's timetable
			while (true) {  // This "while" statement will continue to be "true" until an empty spot is found in "list"
				int x = (int)(Math.random() * 8);  // Generating a random number from 0 to 7 (representing all possible indices in "list")
				if (!visited[x]) {  // "visited[x]" is "false" when "list.get(x)" has not yet been assigned to "timetable" array
					visited[x] = true;  // Mark index 'x' of "list" as occupied in the "timetable" array
					
					// Getting the course code associated with index 'x' from "list" and assigning it to "timetable[i]"
					timetable[i] = list.get(0, list.getHead(), x).getCourseCode();  
					
					if (timetable[i].equals("Grade 12 Spare #1") || timetable[i].equals("Grade 12 Spare #2")) { // Special case
						timetable[i] = "Spare";
					}	
					break;
				}
			}
		}
		
		stu.setTimetable(timetable);  // Set this student's current timetable
		
		displayTimetable(timetable);  // Display the student's current timetable
		
		// Adding the info of the student's timetable into the "StudentsInfo.txt" RandomAccessFile.
		try {
			RandomAccessFile studentsInfo = new RandomAccessFile("StudentsInfo.txt", "rw");
			studentsInfo.seek(studentsInfo.length());
			
			String str = "Timetable_" + stu.getID() + "_";
			for (int i = 0; i < 8; i ++) str += timetable[i] + "_";
			
			studentsInfo.writeBytes(str + "\n");
			studentsInfo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Displayed the selected Student's current timetable
	 * @param arr The timetable array representing the ordering of the Student's current courses
	 */
	public void displayTimetable(String[] arr) {
		int curRow1 = 0, curRow2 = 1, curRow3 = 2, curRow4 = 3;
		for (int i = 0; i < 4; i ++) {
			timetableSemester1[curRow1][i].setText(arr[0]);
			curRow1 ++;
			
			timetableSemester1[curRow2][i].setText(arr[1]);
			curRow2 ++;
			if (curRow2 == 4) curRow2 = 0;
			
			timetableSemester1[curRow3][i].setText(arr[2]);
			curRow3 ++;
			if (curRow3 == 4) curRow3 = 0;
			
			timetableSemester1[curRow4][i].setText(arr[3]);
			curRow4 ++;
			if (curRow4 == 4) curRow4 = 0;
		}
		
		curRow1 = 0; curRow2 = 1; curRow3 = 2; curRow4 = 3;
		for (int i = 0; i < 4; i ++) {
			timetableSemester2[curRow1][i].setText(arr[4]);
			curRow1 ++;
			
			timetableSemester2[curRow2][i].setText(arr[5]);
			curRow2 ++;
			if (curRow2 == 4) curRow2 = 0;
			
			timetableSemester2[curRow3][i].setText(arr[6]);
			curRow3 ++;
			if (curRow3 == 4) curRow3 = 0;
			
			timetableSemester2[curRow4][i].setText(arr[7]);
			curRow4 ++;
			if (curRow4 == 4) curRow4 = 0;
		}
	}
}
