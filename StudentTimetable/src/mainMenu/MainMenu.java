package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class MainMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static MyTextLabel infoLabel, currentStudentsCntLabel, currentTeachersCntLabel, teachersNeededLabel;
	
	private MyButton backToLoginScreenButton, quitButton;
	private MyButton addStudentButton, editCurrentStudentsButton, addTeacherButton, editCurrentTeachersButton, coursesCodeButton, contactInformationButton, historyButton;
	private MyButton imageCitationsButton;
	
	private LinkedListStudent students;   // Linked list of current students in the database
	private LinkedListTeacher teachers;   // Linked list of current teachers in the database
	
	private JPanel canvas;
	
	/**
	 * Initializes the values for a "MainMenu" object
	 * @param c The canvas for this "Main Menu" page
	 */
	public MainMenu(JPanel c) {
		super("Main Menu");
		
		canvas = c;
		add(canvas);
		
		setSize(1200, 820);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        students = new LinkedListStudent();
        teachers = new LinkedListTeacher();
    
        /*
         *  Reloading the past information uploaded by the user into the database. 
         *  This information is saved inside the "StudentsInfo.txt", "TeachersInfo.txt" and "HistoryInfo.txt" RandomAccessFiles.
         *  This information consists of the student's name, completed courses, current courses, teacher's name, history of actions, etc.
         */
		try {	
			// Reloading the past information uploaded by the user about the students in the database
			RandomAccessFile studentsInfo = new RandomAccessFile("StudentsInfo.txt", "rw");
			studentsInfo.seek(0);
			String line = "x";
			while (true) {
				line = studentsInfo.readLine();
				if (line == null) break;
				
				String[] parts = line.split("_");
				
				if (parts[0].equals("Add")) {
					Student temp = new Student();
					temp.setName(parts[1]);
					temp.setID(Integer.parseInt(parts[2]));
					temp.setGrade(Integer.parseInt(parts[3]));
					if (parts[4].equals("true")) temp.setIB(true);
					else temp.setIB(false);
					temp.setEmail(parts[5]);
					
					students.addLast(temp);		
				} else if (parts[0].equals("Edit")) {
					Student stu = findStudent(Integer.parseInt(parts[2]));
					
					LinkedListCourses courses = new LinkedListCourses();
					for (int i = 3; i < parts.length; i ++) {
						courses.addLast(new Course(parts[i]));
					}
					
					if (parts[1].equals("Completed Courses")) {
						stu.setCompletedCourses(courses);
					} else {
						stu.setCurrentCourses(courses);
					}
				} else if (parts[0].equals("Remove")) {
					Student stu = findStudent(Integer.parseInt(parts[1]));
					students.remove(stu);
				} else if (parts[0].equals("Timetable")) {
					Student stu = findStudent(Integer.parseInt(parts[1]));
					
					String[] timetable = new String[8];
					for (int i = 2; i <= 9; i ++) timetable[i - 2] = parts[i];
					stu.setTimetable(timetable);
				}
			}
			studentsInfo.close();
			
			// Reloading the past information uploaded by the user about the teachers in the database
			RandomAccessFile teachersInfo = new RandomAccessFile("TeachersInfo.txt", "rw");
			teachersInfo.seek(0);
			line = "x";
			while (true) {
				line = teachersInfo.readLine();
				if (line == null) break;
				
				String[] parts = line.split("_");
				
				if (parts[0].equals("Add")) {
					Teacher temp = new Teacher(parts[1], Integer.parseInt(parts[2]));
					temp.setEmail(parts[3]);
					
					teachers.addLast(temp);		
				} else if (parts[0].equals("Remove")) {
					Teacher tch = findTeacher(Integer.parseInt(parts[1]));
					teachers.remove(tch);
				}
			}
			teachersInfo.close();
			
			// Reloading the history of events that have occurred in the database
			RandomAccessFile historyInfo = new RandomAccessFile("HistoryInfo.txt", "rw");
			historyInfo.seek(0);
			line = "x";
			while (true) {
				line = historyInfo.readLine();
				if (line == null) break;
				
				DriverClass.addEventToHistory(line, false);
			}
			historyInfo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
        infoLabel = new MyTextLabel("Main Menu", SwingConstants.CENTER);
        infoLabel.setTextLabel(300, 40, getWidth() / 2 - 150, 0, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        currentStudentsCntLabel = new MyTextLabel("Current Number of Students: " + students.getSize(), SwingConstants.CENTER);
        currentStudentsCntLabel.setTextLabel(500, 50, getWidth() / 2 - 250, 300, Color.BLUE, null, new Font("Arial", Font.BOLD, 30));
        currentStudentsCntLabel.setOpaque(false);
        
        currentTeachersCntLabel = new MyTextLabel("Current Number of Teachers: " + teachers.getSize(), SwingConstants.CENTER);
        currentTeachersCntLabel.setTextLabel(500, 50, getWidth() / 2 - 250, 360, Color.BLUE, null, new Font("Arial", Font.BOLD, 30));
        currentTeachersCntLabel.setOpaque(false);

        teachersNeededLabel = new MyTextLabel("<html>Number of teachers currently needed: " + 0 + "<br>(Note: 1 teacher can support 30 students)</html>", SwingConstants.CENTER);
        teachersNeededLabel.setTextLabel(500, 60, getWidth() / 2 - 250, 420, Color.RED, Color.YELLOW, new Font("Arial", Font.BOLD, 22));
        teachersNeededLabel.setOpaque(true);
        
        backToLoginScreenButton = new MyButton("Back");
		backToLoginScreenButton.setButton(150, 50, 0, 735);
		backToLoginScreenButton.addActionListener(this);
		
		quitButton = new MyButton("Quit");
		quitButton.setButton(150, 50, 515, 735);
		quitButton.addActionListener(this);
		
		addStudentButton = new MyButton("Add a student");
		addStudentButton.setButton(150, 50, 135, 70);
		addStudentButton.addActionListener(this);
		
		editCurrentStudentsButton = new MyButton("Edit current student(s)");
		editCurrentStudentsButton.setButton(165, 50, 314, 70);
		editCurrentStudentsButton.addActionListener(this);
		
		coursesCodeButton = new MyButton("<html>View all possible courses</html>");
		coursesCodeButton.setButton(165, 50, 494, 70);
		coursesCodeButton.setBackground(Color.ORANGE);
		coursesCodeButton.addActionListener(this);
		
		addTeacherButton = new MyButton("Add a teacher");
		addTeacherButton.setButton(150, 50, 690, 70);
		addTeacherButton.addActionListener(this);
		
		editCurrentTeachersButton = new MyButton("Edit current teacher(s)");
		editCurrentTeachersButton.setButton(165, 50, 873, 70);
		editCurrentTeachersButton.addActionListener(this);
		
		contactInformationButton = new MyButton("<html>Contact Information<br>of student(s) and teacher(s)</html>");
		contactInformationButton.setButton(150, 70, getWidth() / 2 - 75, 190);
		contactInformationButton.addActionListener(this);
		
		historyButton = new MyButton("History");
		historyButton.setButton(150, 50, getWidth() / 2 - 75, 550);
		historyButton.addActionListener(this);
		
		imageCitationsButton = new MyButton("Image Citations");
		imageCitationsButton.setButton(150, 50, 1035, 735);
		imageCitationsButton.addActionListener(this);
		
		canvas.add(infoLabel);
		canvas.add(currentStudentsCntLabel);
		canvas.add(currentTeachersCntLabel);
		canvas.add(teachersNeededLabel);
		
		canvas.add(backToLoginScreenButton);
		canvas.add(quitButton);
		
		canvas.add(addStudentButton);
		canvas.add(editCurrentStudentsButton);
		
		canvas.add(addTeacherButton);
		canvas.add(editCurrentTeachersButton);
		
		canvas.add(coursesCodeButton);
		
		canvas.add(contactInformationButton);
		
		canvas.add(historyButton);
		
		canvas.add(imageCitationsButton);
		
		// Updating the current info about the # of students, # of teachers, and # of teachers required.
		updateCnt();
	}
	
	/**
	 * Retrieving the Student associated with a given ID number
	 * @param id The ID number of a Student
	 * @return The Student associated with the given ID number
	 */
	public Student findStudent(int id) {
		for (int i = 0; i < students.getSize(); i ++) {
			Student stu = students.get(0, students.getHead(), i);
			if (stu.getID() == id) return stu;
		}
		return null;
	}

	/**
	 * Retrieving the Teacher associated with a given ID number
	 * @param id The ID number of a Teacher
	 * @return The Teacher associated with the given ID number
	 */
	public Teacher findTeacher(int id) {
		for (int i = 0; i < teachers.getSize(); i ++) {
			Teacher tch = teachers.get(0, teachers.getHead(), i);
			if (tch.getID() == id) return tch;
		}
		return null;
	}
	
	/**
	 * Updating the current info about the # of students, # of teachers, and # of teachers required.
	 */
	public void updateCnt() { 
		currentStudentsCntLabel.setText("Current Number of Students: " + students.getSize());
	    currentTeachersCntLabel.setText("Current Number of Teachers: " + teachers.getSize());
	    
	    int teachersNeeded = Math.max((int) Math.ceil(students.getSize() / (double)2) - teachers.getSize(), 0);    
        teachersNeededLabel.setText("<html>Number of teachers currently needed: " + teachersNeeded + "<br>(Note: 1 teacher can support 30 students)</html>");
	}
	
	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backToLoginScreenButton)) {   // User wants to go back to the login screen
			setVisible(false);
			DriverClass.displayLoginPage();
		} else if (button.equals(quitButton)) {   // User wants to quit or exit the program
			System.exit(0);
		} else if (button.equals(addStudentButton)) {   // User wants to add a student to the database
			setVisible(false);
			DriverClass.displayAddAStudentPage();
		} else if (button.equals(editCurrentStudentsButton)) {   // User wants to edit the added students		
			setVisible(false);
			
			students.setOriginalList();
			if (EditCurrentStudentsPage.wasSorted())
				students.sortAlphabetically(true);
			
			DriverClass.displayEditCurrentStudentsPage();
		} else if (button.equals(coursesCodeButton)) {   // User wants to view all possible courses offered at my high school
			setVisible(false);
			DriverClass.displayViewCoursesPage();
		} else if (button.equals(addTeacherButton)) {   // User wants to add a teacher to the database
			setVisible(false);
			DriverClass.displayAddATeacherPage();
		} else if (button.equals(editCurrentTeachersButton)) {   // User wants to edit the added teachers		
			setVisible(false);
			
			teachers.setOriginalList();
			if (EditCurrentTeachersPage.wasSorted())
				teachers.sortAlphabetically(true);
			
			DriverClass.displayEditCurrentTeachersPage();
		} else if (button.equals(contactInformationButton)) {   // User wants to view the contact information of the current students and teachers in the database.
			students.setOriginalList();
			teachers.setOriginalList();
			
			setVisible(false);
			DriverClass.displayContactInformationPage();
		} else if (button.equals(historyButton)) {   // User wants to view the history of events that have occurred while they used the program
			setVisible(false);
			DriverClass.displayHistoryPage();
		} else if (button.equals(imageCitationsButton)) {   // User wants to view the citations of the background images used in this program
			setVisible(false);
			DriverClass.displayImageCitationsPage();
		}
	}
	
	/**
	 * Add a Student to the end of the "students" linked list
	 * @param stu A Student
	 */
	public void addStudent(Student stu) {
		students.addLast(stu);
	}
	
	/**
	 * Add a Teacher to the end of the "teachers" linked list
	 * @param tch A Teacher
	 */
	public void addTeacher(Teacher tch) {
		teachers.addLast(tch);
	}
	
	/**
	 * Get the list of current Students in this database
	 * @return The list of current Students in this database
	 */
	public LinkedListStudent getStudentsList() {
		return students;
	}
	
	/**
	 * Get the list of current Teachers in this database
	 * @return The list of current Teachers in this database
	 */
	public LinkedListTeacher getTeachersList() {
		return teachers;
	}
}