package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class EditCurrentTeachersPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MyTextLabel choiceLabel;
	
	private static LinkedListTeacher teachersList;   // Linked list of teachers who are currently in the database
	
	private MyButton sortTeachersAlphabeticallyButton, removeThisTeacherButton;
	private MyButton backButton;

	private Choice teachersMenu;
	
	private static boolean sorted;   // Stores whether the list of teachers is currently sorted alphabetically or not
	
	/**
	 * Initializes the values for a "EditCurrentTeachersPage" object
	 */
	public EditCurrentTeachersPage() {
		super ("Edit current teachers");
		
		setLayout(null);
		setSize(800, 800);
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		sorted = false;

		teachersList = new LinkedListTeacher();

		choiceLabel = new MyTextLabel("Select a teacher below", SwingConstants.CENTER);
		choiceLabel.setTextLabel(400, 40, getWidth() / 2 - 200, 20, Color.BLACK, null, new Font("Arial", Font.BOLD, 30));

		teachersMenu = new Choice();
		teachersMenu.setBounds(getWidth() / 2 - 100, 75, 200, 40);

		for (int i = 0; i < teachersList.getSize(); i ++) {
			teachersMenu.add(teachersList.get(0, teachersList.getHead(), i).getName());
		}

		backButton = new MyButton("Back");
		backButton.setButton(160, 50, 0, 713);
		backButton.addActionListener(this);

		sortTeachersAlphabeticallyButton = new MyButton("<html>Sort list of teacher(s)<br>alphabetically</html>");
		sortTeachersAlphabeticallyButton.setButton(190, 40, 90, 65);
		sortTeachersAlphabeticallyButton.setBackground(Color.CYAN);
		sortTeachersAlphabeticallyButton.addActionListener(this);

		removeThisTeacherButton = new MyButton("Remove this teacher");
		removeThisTeacherButton.setButton(190, 30, getWidth() / 2 + 120, 70);
		removeThisTeacherButton.setBackground(Color.RED);
		removeThisTeacherButton.addActionListener(this);

		add(choiceLabel);
		add(teachersMenu);

		add(sortTeachersAlphabeticallyButton);
		add(removeThisTeacherButton);	 

		add(backButton);

		sortTeachersAlphabeticallyButton.setVisible(false);
		removeThisTeacherButton.setVisible(false);
	}
	
	/**
	 * Receiving the list of Teachers in the database from the main menu page and setting up the page layout from this list of Teachers
	 * @param list The list of Teachers currently in the database
	 */
	public void setTeachersList(LinkedListTeacher list) {
		teachersList = list;

		remove(teachersMenu);
		
		teachersMenu = new Choice();
		teachersMenu.setBounds(getWidth() / 2 - 100, 75, 200, 40);

		for (int i = 0; i < teachersList.getSize(); i ++) {
			teachersMenu.add((i + 1) + ". " + teachersList.get(0, teachersList.getHead(), i).getName());
		}

		add(teachersMenu);

		if (teachersList.getSize() > 0) {   // List of teachers is not empty, so show the "Sort alphabetically" and "Remove this teacher" button
			sortTeachersAlphabeticallyButton.setVisible(true);
			removeThisTeacherButton.setVisible(true);
		} else {   // List of teachers is empty, so hide the "Sort alphabetically" and "Remove this teacher" button
			sortTeachersAlphabeticallyButton.setVisible(false);
			removeThisTeacherButton.setVisible(false);
		}
	}

	/**
	 * Returns true if the list of Teachers on this page is currently sorted alphabetically
	 * @return "True" if the list of Teachers on this page is currently sorted alphabetically, "false" otherwise
	 */
	public static boolean wasSorted() {
		return sorted;
	}
	
	/**
	 * Receives a string from the list of Teachers and returns the Teacher associated with that string
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
			setVisible(false);
			
			teachersList.sortAlphabetically(false);
			DriverClass.displayMainMenu();
		} else if (button.equals(sortTeachersAlphabeticallyButton)) {   // User wants to either sort the list of teachers alphabetically or unsort it back into its original order
			if (!sorted) {   // Current list of teachers is not sorted alphabetically, so sort the list of teachers alphabetically
				teachersList.sortAlphabetically(true);
				setTeachersList(teachersList);
				sorted = true;
				
				sortTeachersAlphabeticallyButton.setBackground(Color.ORANGE);
				sortTeachersAlphabeticallyButton.setText("<html>Unsort the list back into<br>original order</html>");
			} else {   // Current list of teachers is sorted alphabetically, so unsort the list of teachers back into its original order
				teachersList.sortAlphabetically(false);
				setTeachersList(teachersList);
				sorted = false;
				
				sortTeachersAlphabeticallyButton.setBackground(Color.CYAN);
				sortTeachersAlphabeticallyButton.setText("<html>Sort list of teacher(s)<br>alphabetically</html>");
			}
		} else if (button.equals(removeThisTeacherButton)) {   // User wants to remove the currently selected teacher
			Teacher tch = findTeacher(teachersMenu.getSelectedItem());
		
			// Removing the teacher from the "teachers" linked list in the main menu.
			teachersList.remove(tch);
			setTeachersList(teachersList);
			teachersList.updateOriginalList(tch);
			
			// Adding an event to the history page about the teacher being removed.
			DriverClass.addEventToHistory(tch.getName() + " has been removed as a teacher in this school.", true);

			// Inform the "TeachersInfo.txt" RandomAccessFile about removing the selected teacher
			try {
				RandomAccessFile teachersInfo = new RandomAccessFile("TeachersInfo.txt", "rw");
				teachersInfo.seek(teachersInfo.length());
				teachersInfo.writeBytes("Remove_" + tch.getID() + "\n");
				teachersInfo.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}
}
