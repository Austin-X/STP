package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class AddATeacherPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MyTextLabel necessaryInfoTextLabel, nameTextLabel;
	
	private MyTextField nameTextField;

	private MyButton backButton, addTeacherButton;
	
	/**
	 * Initializes the values for a "AddATeacherPage" object
	 * @param canvas The canvas for the "Add A Teacher" page
	 */
	public AddATeacherPage(JPanel canvas) {
		super ("Add A Teacher");
		add(canvas);
		
		setSize(600, 830);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        necessaryInfoTextLabel = new MyTextLabel("<html>Note: Fields marked with '*' means<br>that they are necessary to fill out.</html>", SwingConstants.CENTER);
        necessaryInfoTextLabel.setTextLabel(280, 80, 155, 40, Color.BLACK, Color.ORANGE, new Font("Arial", Font.BOLD, 16));
        
        nameTextLabel = new MyTextLabel("(*) Full name:", SwingConstants.CENTER);
        nameTextLabel.setTextLabel(125, 20, 170, 190, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        nameTextField = new MyTextField();
        nameTextField.setTextField(250, 30, 170, 210);
        
        addTeacherButton = new MyButton("Add this teacher");
        addTeacherButton.setButton(130, 40, 227, 260);
        addTeacherButton.addActionListener(this);
        
        backButton = new MyButton("Back");
        backButton.setButton(150, 50, 210, 735);
        backButton.addActionListener(this);
        
        canvas.add(necessaryInfoTextLabel);
        
        canvas.add(nameTextLabel);
        canvas.add(nameTextField);
        
        canvas.add(addTeacherButton);
        
        canvas.add(backButton);
	}

	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			nameTextField.setText("");
			
			setVisible(false);
			DriverClass.displayMainMenu();
		} else if (button.equals(addTeacherButton)) {   // User has chosen to add a teacher to this database program
			String name = nameTextField.getText().trim();
			String[] nameSplit = name.split(" ");
			
			if (name.equals("")) {   // If user did not enter anything in the "name" text field
				JOptionPane.showMessageDialog(this, "Please fill out all necessary fields.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else if (nameSplit.length == 1) {   // If the user did not enter a last name for the teacher			
				JOptionPane.showMessageDialog(this, "Please enter a last name for this teacher.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {   // User has successfully filled in all necessary fields correctly
				LinkedListTeacher teachers = DriverClass.getTeachers();
				int id;
				
				// Making sure that a unique ID is generated for the current teacher being added.
				while (true) { 
					id = Person.generateID();
					boolean flag = true;
					for (int i = 0; i < teachers.getSize(); i ++) {
						if (teachers.get(0, teachers.getHead(), i).getID() == id) {
							flag = false;
							break;
						}
					}
					if (flag) break;
				}		
				
				// Setting the name, id, and email of the added teacher.
				Teacher temp = new Teacher(name, id);
				temp.generateEmail();
				
				// Adding the teacher to the "teachers" linkedlist in the main menu.
				DriverClass.addTeacher(temp);

				// Updating the history page when a new teacher is added.
				DriverClass.addEventToHistory(name + " has been added as a teacher in this school.", true);
				
				// Adding the teacher's info to the "TeachersInfo.txt" RandomAccessFile.
				try {
					RandomAccessFile teachersInfo = new RandomAccessFile("TeachersInfo.txt", "rw");
					teachersInfo.seek(teachersInfo.length());
					teachersInfo.writeBytes("Add_" + name + "_" + id + "_" + temp.getEmail() + "\n");
					teachersInfo.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// Clearing the screen and informing the user that the teacher has been successfully added.
				nameTextField.setText("");
				JOptionPane.showMessageDialog(this, "Teacher added successfully.");
			}
		}
	}
}