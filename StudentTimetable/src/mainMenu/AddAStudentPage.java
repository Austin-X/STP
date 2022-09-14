package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import linkedList.*;
import objects.*;

public class AddAStudentPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private MyTextLabel necessaryInfoTextLabel, nameTextLabel, gradeTextLabel, inIBTextLabel;
	private MyTextField nameTextField;
	private MyButton backButton, addStudentButton;
	private static MyButton gr9Button, gr10Button, gr11Button, gr12Button, inIB_Button, notInIB_Button;

	private int grade;   // Selected grade for the student
	
	/* "inIB" is true if student is in pre-IB or IB, false otherwise.
	 * "notInIB" returns true if the student is not in pre-IB or IB, false otherwise. */
	private boolean inIB, notInIB;   
		
	/**
	 * Initializes the values for a "AddAStudentPage" object
	 * @param canvas The canvas for the "Add A Student" page
	 */
	public AddAStudentPage(JPanel canvas) {
		super ("Add A Student");
		add(canvas);
		
		setSize(600, 830);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        grade = 0;
        
        inIB = false; notInIB = false;
        
        necessaryInfoTextLabel = new MyTextLabel("<html>Note: Fields marked with '*' means<br>that they are necessary to fill out.</html>", SwingConstants.CENTER);
        necessaryInfoTextLabel.setTextLabel(280, 80, 155, 40, Color.BLACK, Color.ORANGE, new Font("Arial", Font.BOLD, 16));
        
        nameTextLabel = new MyTextLabel("(*) Full name:", SwingConstants.CENTER);
        nameTextLabel.setTextLabel(125, 20, 170, 190, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        nameTextField = new MyTextField();
        nameTextField.setTextField(250, 30, 170, 210);
        
        gradeTextLabel = new MyTextLabel("(*) Grade:", SwingConstants.CENTER);
        gradeTextLabel.setTextLabel(100, 20, 162, 260, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        gr9Button = new MyButton("Grade 9");
        gr9Button.setButton(86, 30, 162, 280);
        gr9Button.addActionListener(this);
     
        gr10Button = new MyButton("Grade 10");
        gr10Button.setButton(86, 30, 248, 280);
        gr10Button.addActionListener(this);
        
        gr11Button = new MyButton("Grade 11");
        gr11Button.setButton(86, 30, 334, 280);
        gr11Button.addActionListener(this);
        
        gr12Button = new MyButton("Grade 12");
        gr12Button.setButton(86, 30, 248, 310);
        gr12Button.addActionListener(this);
        
        inIBTextLabel = new MyTextLabel("<html>(*) Is this student currently in<br>the pre-IB or IB program?</html>", SwingConstants.CENTER);
        inIBTextLabel.setTextLabel(210, 35, 190, 370, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 14));
        
        inIB_Button = new MyButton("Yes");
        inIB_Button.setButton(80, 40, 190, 415);
        inIB_Button.addActionListener(this);
        
        notInIB_Button = new MyButton("No");
        notInIB_Button.setButton(80, 40, 320, 415);
        notInIB_Button.addActionListener(this);

        addStudentButton = new MyButton("Add this student");
        addStudentButton.setButton(130, 40, 227, 500);
        addStudentButton.addActionListener(this);
        
        backButton = new MyButton("Back");
        backButton.setButton(160, 50, 210, 735);
        backButton.addActionListener(this);
        
        canvas.add(necessaryInfoTextLabel);
        
        canvas.add(nameTextLabel);
        canvas.add(nameTextField);
        
        canvas.add(gradeTextLabel);
        canvas.add(gr9Button);
        canvas.add(gr10Button);
        canvas.add(gr11Button);
        canvas.add(gr12Button);
         
        canvas.add(inIBTextLabel);
        canvas.add(inIB_Button);
        canvas.add(notInIB_Button);
        
        canvas.add(addStudentButton);
        
        canvas.add(backButton);
	}
	
	/**
	 * Resetting the "grade", "inIB", and "notInIB" fields, clearing colours of buttons, clearing text from text fields
	 */
	public void clearInfo() {
		grade = 0;
		inIB = false;
		notInIB = false;
		
		resetButtonColours();
		inIB_Button.setBackground(null);
		notInIB_Button.setBackground(null);
		nameTextField.setText("");
	}
	
	/**
	 * Clearing the colours of the grade buttons
	 */
	public static void resetButtonColours() {
		gr9Button.setBackground(null);
		gr10Button.setBackground(null);
		gr11Button.setBackground(null);
		gr12Button.setBackground(null);
	}
	
	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }

	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			clearInfo();

			setVisible(false);
			DriverClass.displayMainMenu();
		} else if (button.equals(addStudentButton)) {   // User has chosen to add a student to this database program
			String name = nameTextField.getText().trim();
			String[] nameSplit = name.split(" ");

			if (!name.equals("") && grade != 0 && (inIB || notInIB)) {   // If the user filled in all necessary fields
				if (nameSplit.length == 1) {   // If the user has not entered a last name for the student
					JOptionPane.showMessageDialog(this, "Please enter a last name for this student.", "Warning", JOptionPane.WARNING_MESSAGE);
				} 
				else {   // User has successfully filled in all necessary field correctly
		
					LinkedListStudent students = DriverClass.getStudents();
					int id;
					
					// Making sure that a unique ID is generated for the current student being added.
					while (true) {
						id = Person.generateID();   
						boolean flag = true;
						for (int i = 0; i < students.getSize(); i ++) {
							if (students.get(0, students.getHead(), i).getID() == id) {
								flag = false;
								break;
							}
						}
						if (flag) break;
					}						
					
					// Creating the student.
					Student temp = new Student();
					temp.setName(name); 
					temp.setID(id);
					temp.setGrade(grade);
					temp.setIB(inIB);
					temp.generateEmail();
					
					// Adding the student to the "students" linkedlist in the main menu.
					DriverClass.addStudent(temp);
					
					// This block of code updates the history page when a new student is added.
					String str = temp.getName() + " has been added to the school as a grade " + temp.getGrade() + " student ";
					if (temp.isInIB()) {
						if (temp.getGrade() <= 10) str += "currently in the pre-IB program.";
						else str += "currently in the IB program.";
					} else str += "currently not in the pre-IB or IB program.";	
					DriverClass.addEventToHistory(str, true);
					
					// Adding the student's info to the "StudentsInfo.txt" RandomAccessFile.
					try {
						RandomAccessFile studentsInfo = new RandomAccessFile("StudentsInfo.txt", "rw");
						studentsInfo.seek(studentsInfo.length());
						studentsInfo.writeBytes("Add_" + name + "_" + id + "_" + grade + "_" + inIB + "_" + temp.getEmail() + "\n");
						studentsInfo.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					// Clearing the private field's info, textfields, and button colours to allow for another student to be added.
					clearInfo();
	
					JOptionPane.showMessageDialog(this, "Student added successfully.");
				}
			} else {   // User has not filled in all necessary fields
				JOptionPane.showMessageDialog(this, "Please make sure to fill all the necessary fields.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		} else if (button.equals(inIB_Button)) {   // User has selected the student to be in the pre-IB or IB program
			notInIB_Button.setBackground(null);
			inIB_Button.setBackground(Color.GREEN);
			inIB = true;
		} else if (button.equals(notInIB_Button)) {   // User has selected the student to not be in the pre-IB or IB program
			inIB_Button.setBackground(null);
			notInIB_Button.setBackground(Color.RED);
			notInIB = true;
		} else if (button.equals(gr9Button)) {   // User has selected the student to be in grade 9
			grade = 9;
			resetButtonColours();
			gr9Button.setBackground(Color.GREEN);
		} else if (button.equals(gr10Button)) {   // User has selected the student to be in grade 10
			grade = 10;
			resetButtonColours();
			gr10Button.setBackground(Color.GREEN);
		} else if (button.equals(gr11Button)) {   // User has selected the student to be in grade 11
			grade = 11;
			resetButtonColours();
			gr11Button.setBackground(Color.GREEN);
		} else if (button.equals(gr12Button)) {   // User has selected the student to be in grade 12
			grade = 12;
			resetButtonColours();
			gr12Button.setBackground(Color.GREEN);
		}
	}
}