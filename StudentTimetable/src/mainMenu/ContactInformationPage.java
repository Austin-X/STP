package mainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class ContactInformationPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private LinkedListStudent studentsList;   // Linked list of Students who are currently in the database
	private LinkedListTeacher teachersList;   // Linked list of Teachers who are currently in the database
	
	private Choice studentsMenu, teachersMenu;
	
	private MyTextLabel infoLabel, studentsListLabel, teachersListLabel;
	
	private MyTextLabel studentNameLabel, studentNameLabelAns, studentIDLabel, studentIDLabelAns, studentGradeLabel, studentGradeLabelAns, studentIsInIBLabel, studentIsInIBLabelAns, studentEmailLabel, studentEmailLabelAns;
	private MyTextLabel teacherNameLabel, teacherNameLabelAns, teacherIDLabel, teacherIDLabelAns, teacherEmailLabel, teacherEmailLabelAns;

	private MyButton viewStudentInfoButton, viewTeacherInfoButton, backButton;
	
	/**
	 * Initializes the values for a "ContactInformationPage" object
	 */
	public ContactInformationPage() {
		super ("Contact Information Page");
		
		setLayout(null);
		setSize(800, 800);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		studentsList = new LinkedListStudent();

		infoLabel = new MyTextLabel("Contact Information Page", SwingConstants.CENTER);
		infoLabel.setTextLabel(400, 40, getWidth() / 2 - 200, 20, Color.BLACK, null, new Font("Arial", Font.BOLD, 30));

		studentsListLabel = new MyTextLabel("Students", SwingConstants.CENTER);
		studentsListLabel.setTextLabel(200, 60, 100, 75, Color.BLACK, null, new Font("Arial", Font.BOLD, 24));

		studentsMenu = new Choice();
		studentsMenu.setBounds(100, 135, 200, 40);

		viewStudentInfoButton = new MyButton("View this student's info");
		viewStudentInfoButton.setButton(200, 40, 100, 160);
		viewStudentInfoButton.addActionListener(this);

		teachersListLabel = new MyTextLabel("Teachers", SwingConstants.CENTER);
		teachersListLabel.setTextLabel(200, 60, 500, 75, Color.BLACK, null, new Font("Arial", Font.BOLD, 24));

		teachersMenu = new Choice();
		teachersMenu.setBounds(500, 135, 200, 40);

		viewTeacherInfoButton = new MyButton("View this teacher's info");
		viewTeacherInfoButton.setButton(200, 40, 500, 160);
		viewTeacherInfoButton.addActionListener(this);

		studentNameLabel = new MyTextLabel("Name", SwingConstants.CENTER);
		studentNameLabel.setTextLabel(200, 20, 100, 220, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));

		studentNameLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		studentNameLabelAns.setTextLabel(200, 20, 100, 240, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));

		studentIDLabel = new MyTextLabel("ID", SwingConstants.CENTER);
		studentIDLabel.setTextLabel(200, 20, 100, 300, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));

		studentIDLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		studentIDLabelAns.setTextLabel(200, 20, 100, 320, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));

		studentGradeLabel = new MyTextLabel("Grade", SwingConstants.CENTER);
		studentGradeLabel.setTextLabel(200, 20, 100, 380, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));

		studentGradeLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		studentGradeLabelAns.setTextLabel(200, 20, 100, 400, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));

		studentIsInIBLabel = new MyTextLabel("In pre-IB or IB?", SwingConstants.CENTER);
		studentIsInIBLabel.setTextLabel(200, 20, 100, 460, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));

		studentIsInIBLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		studentIsInIBLabelAns.setTextLabel(200, 20, 100, 480, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));

		studentEmailLabel = new MyTextLabel("Email", SwingConstants.CENTER);
		studentEmailLabel.setTextLabel(200, 20, 100, 540, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));

		studentEmailLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		studentEmailLabelAns.setTextLabel(300, 20, 50, 560, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));

		teacherNameLabel = new MyTextLabel("Name", SwingConstants.CENTER);
		teacherNameLabel.setTextLabel(200, 20, 500, 220, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));
		
		teacherNameLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		teacherNameLabelAns.setTextLabel(200, 20, 500, 240, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));
		
		teacherIDLabel = new MyTextLabel("ID", SwingConstants.CENTER);
		teacherIDLabel.setTextLabel(200, 20, 500, 300, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));
	    
		teacherIDLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		teacherIDLabelAns.setTextLabel(200, 20, 500, 320, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));
		
		teacherEmailLabel = new MyTextLabel("Email", SwingConstants.CENTER);
		teacherEmailLabel.setTextLabel(200, 20, 500, 380, Color.BLACK, null, new Font("Arial", Font.BOLD, 18));
		    
		teacherEmailLabelAns = new MyTextLabel("", SwingConstants.CENTER);
		teacherEmailLabelAns.setTextLabel(300, 20, 450, 400, Color.BLACK, null, new Font("Arial", Font.PLAIN, 16));

		backButton = new MyButton("Back");
		backButton.setButton(160, 50, 0, 713);
		backButton.addActionListener(this);

		add(infoLabel);

		add(studentsListLabel);
		add(studentsMenu);
		add(viewStudentInfoButton);

		add(teachersListLabel);
		add(teachersMenu);
		add(viewTeacherInfoButton);

		add(studentNameLabel);
		add(studentNameLabelAns);
		add(studentIDLabel);
		add(studentIDLabelAns);
		add(studentGradeLabel);
		add(studentGradeLabelAns);
		add(studentIsInIBLabel);
		add(studentIsInIBLabelAns);
		add(studentEmailLabel);
		add(studentEmailLabelAns);

		add(teacherNameLabel);
		add(teacherNameLabelAns);
		add(teacherIDLabel);
		add(teacherIDLabelAns);
		add(teacherEmailLabel);
		add(teacherEmailLabelAns);

		hideLabels();

		add(backButton);
	}
	
	/**
	 * Hide all labels responsible for displaying information about a Student and Teacher
	 */
	public void hideLabels() {
		studentNameLabel.setVisible(false);
		studentNameLabelAns.setVisible(false);
		studentIDLabel.setVisible(false);
		studentIDLabelAns.setVisible(false);
		studentGradeLabel.setVisible(false);
		studentGradeLabelAns.setVisible(false);
		studentIsInIBLabel.setVisible(false);
		studentIsInIBLabelAns.setVisible(false);
		studentEmailLabel.setVisible(false);
		studentEmailLabelAns.setVisible(false);

		teacherNameLabel.setVisible(false);
		teacherNameLabelAns.setVisible(false);
		teacherIDLabel.setVisible(false);
		teacherIDLabelAns.setVisible(false);
		teacherEmailLabel.setVisible(false);
		teacherEmailLabelAns.setVisible(false);
	}
	
	/**
	 * Show all labels responsible for displaying information about a Student
	 */
	public void showStudentLabels() {
		studentNameLabel.setVisible(true);
		studentNameLabelAns.setVisible(true);
		studentIDLabel.setVisible(true);
		studentIDLabelAns.setVisible(true);
		studentGradeLabel.setVisible(true);
		studentGradeLabelAns.setVisible(true);
		studentIsInIBLabel.setVisible(true);
		studentIsInIBLabelAns.setVisible(true);
		studentEmailLabel.setVisible(true);
		studentEmailLabelAns.setVisible(true);
	}
	
	/**
	 * Show all labels responsible for displaying information about a Teacher
	 */
	public void showTeacherLabels() {
		teacherNameLabel.setVisible(true);
		teacherNameLabelAns.setVisible(true);
		teacherIDLabel.setVisible(true);
		teacherIDLabelAns.setVisible(true);
		teacherEmailLabel.setVisible(true);
		teacherEmailLabelAns.setVisible(true);
	}
	
	/**
	 * Set the list of Students and Teachers and display them on the page. These lists are automatically sorted alphabetically
	 * @param sList The list of Students currently in the database
	 * @param tList The list of Teachers currently in the database
	 */
	public void setLists(LinkedListStudent sList, LinkedListTeacher tList) {
		remove(studentsMenu);
		remove(teachersMenu);

		studentsMenu = new Choice();
		studentsMenu.setBounds(100, 135, 200, 40);
	    
		teachersMenu = new Choice();
		teachersMenu.setBounds(500, 135, 200, 40);
	    
		studentsList = sList;
		teachersList = tList;

		// Sort the list of students alphabetically and display this list
		studentsList.sortAlphabetically(true); 
		for (int i = 0; i < studentsList.getSize(); i ++) {
			studentsMenu.add((i + 1) + ". " + studentsList.get(0, studentsList.getHead(), i).getName());
		}
		
		// Sort the list of teachers alphabetically and display this list
		teachersList.sortAlphabetically(true);
		for (int i = 0; i < teachersList.getSize(); i ++) {
			teachersMenu.add((i + 1) + ". " + teachersList.get(0, teachersList.getHead(), i).getName());
		}
		
		add(studentsMenu);
		add(teachersMenu);
	}
	
	/**
	 * Receives a string from the list of Students and returns the Student associated with that string
	 * @param str The selected string from the Student's pop-up menu of choices
	 * @return The Student associated with the selected string in the Student's pop-up menu of choices
	 */
	private Student findStudent(String str) {	
		int idx = Character.getNumericValue(str.charAt(0)) - 1;
		Student stu = studentsList.get(0, studentsList.getHead(), idx);
		return stu;
	}
	
	/**
	 * Receives a string from the list of teachers and return the teacher associated with that string
	 * @param str The selected string from the Teacher's pop-up menu of choices
	 * @return The Teacher associated with the selected string in the Teacher's pop-up menu of choices
	 */
	private Teacher findTeacher(String str) {
		int idx = Character.getNumericValue(str.charAt(0)) - 1;
		Teacher tch = teachersList.get(0, teachersList.getHead(), idx);
		return tch;
	}
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			studentsList.sortAlphabetically(false);
			teachersList.sortAlphabetically(false);
			
			hideLabels();
			
			setVisible(false);
			DriverClass.displayMainMenu();
		} else if (button.equals(viewStudentInfoButton)) {   // User wants to view the selected student's info
			if (studentsMenu.getSelectedItem() != null) {
				Student stu = findStudent(studentsMenu.getSelectedItem());
				
				studentNameLabelAns.setText(stu.getName());
				studentIDLabelAns.setText(String.valueOf(stu.getID()));
				studentGradeLabelAns.setText(String.valueOf(stu.getGrade()));
				studentIsInIBLabelAns.setText(stu.isInIB() ? "Yes" : "No");
				studentEmailLabelAns.setText(stu.getEmail());
				
				showStudentLabels();
			} else JOptionPane.showMessageDialog(this, "There is no student selected.", "Warning", JOptionPane.WARNING_MESSAGE);
		} else if (button.equals(viewTeacherInfoButton)) {   // User wants to view the selected teacher's info
			if (teachersMenu.getSelectedItem() != null) {
				Teacher tch = findTeacher(teachersMenu.getSelectedItem());
				
				teacherNameLabelAns.setText(tch.getName());
				teacherIDLabelAns.setText(String.valueOf(tch.getID()));
				teacherEmailLabelAns.setText(tch.getEmail());
				
				showTeacherLabels();
			} else JOptionPane.showMessageDialog(this, "There is no teacher selected.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
