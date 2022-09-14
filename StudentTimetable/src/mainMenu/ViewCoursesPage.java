package mainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import driverProgram.*;
import objects.*;

public class ViewCoursesPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MyButton backButton;
	
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
	
	private Button[][] buttonArr = new Button[courses.length][courses[0].length];   // The buttons necessary in displaying all of the courses
		
	private MyTextLabel infoLabel;
	
	/**
	 * Initializes the values for a "ViewCoursesPage" object
	 */
	public ViewCoursesPage() {
		super ("View All Possible Courses Offered");
		
		setSize(1100, 800);
		setVisible(false);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    JPanel canvas = new JPanel();
	    canvas.setLayout(new GridLayout(0, 7));

	    // This block of code sets up the top row of buttons for this page
	    buttonArr[0][0] = new Button(courses[0][0]);
	    canvas.add(buttonArr[0][0]);
	    for (int i = 1; i < courses[0].length; i ++) {
	    	buttonArr[0][i] = new Button(courses[0][i]);
	    	buttonArr[0][i].setBackground(Color.CYAN);
	    	canvas.add(buttonArr[0][i]);
	    }
	    
	    // Setting up all other buttons in the page
		for (int i = 1; i < courses.length; i ++) {
			for (int j = 0; j < courses[i].length; j ++) {
				if (j == 0) {
					if (courses[i][j].length() == 0) {
						buttonArr[i][j] = new Button("");
						buttonArr[i][j].setBackground(Color.BLACK);
					} else {
						buttonArr[i][j] = new Button(courses[i][j]);
						buttonArr[i][j].setBackground(new Color(222,184,135));
					}
				} else {
					if (courses[i][j].length() == 0) {
						buttonArr[i][j] = new Button("");
						buttonArr[i][j].setBackground(Color.BLACK);
					}
					else {
						if (i == courses.length - 1) buttonArr[i][j] = new Button(courses[i][j]);
						else buttonArr[i][j] = new Button(courses[i][j].substring(6, 12));
					}
					
					buttonArr[i][j].addActionListener(this);
				}
				canvas.add(buttonArr[i][j]);
			}
		}

	    infoLabel = new MyTextLabel("Click on a course code to view its course description.", SwingConstants.CENTER);
	    infoLabel.setTextLabel(400, 40, 0, 0, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 14));
	    
	    backButton = new MyButton("Back");
	    backButton.addActionListener(this);
	    
	    add(infoLabel, BorderLayout.NORTH);
	    add(backButton, BorderLayout.WEST);
	    add(canvas);
	}
	
	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }

	public void actionPerformed(ActionEvent e) {
		Component button = (Component) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			setVisible(false);
			DriverClass.displayMainMenu();
		} else {   // User has clicked on one of the other buttons to possibly view its course description and prerequisites
			for (int i = 0; i < courses.length; i ++) {
				for (int j = 0; j < courses[i].length; j ++) {
					if (button.equals(buttonArr[i][j])) {
						if (courses[i][j].length() == 0) JOptionPane.showMessageDialog(this, "That is not an appropriate course.", "Warning", JOptionPane.WARNING_MESSAGE);
						else JOptionPane.showMessageDialog(this, courses[i][j]);
					}
				}
			}
		}
	}
}