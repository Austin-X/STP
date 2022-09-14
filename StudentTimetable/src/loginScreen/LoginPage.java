package loginScreen;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import driverProgram.*;
import objects.*;

public class LoginPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private MyTextLabel welcomeMessageLabel, usernameLabel, passwordLabel;
	private MyTextField usernameTextField, passwordTextField;
	private MyButton createAccountButton, loginButton, quitButton;
	
	private long usernameHashcode, passwordHashcode;  // Hashcode values for the username and password that user enters
	
	private RandomAccessFile loginInfo;
	
	/**
	 * Initializes the values for a "LoginPage" object
	 * @param canvas The canvas for the "Login" page
	 */
	public LoginPage(JPanel canvas) {
		super ("Login Screen");
		add(canvas);
		
		/* Recalling the username and password set by the user once they created an account.
		 * This information is recalled from the "LoginInfo.txt" RandomAccessFile. */
		try {
			loginInfo = new RandomAccessFile("LoginInfo.txt", "rw");
			try {
				String username = loginInfo.readLine();
				String password = loginInfo.readLine();
				
				if (username == null) {
					usernameHashcode = 0;
					passwordHashcode = 0;
				} else {
					usernameHashcode = Long.parseLong(username);
					passwordHashcode = Long.parseLong(password);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setSize(1200, 820);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        welcomeMessageLabel = new MyTextLabel("Welcome to the High School Student Timetable", SwingConstants.CENTER);
        welcomeMessageLabel.setTextLabel(800, 150, 200, 50, Color.BLACK, Color.GREEN, new Font("Arial", Font.BOLD, 35));
        
        usernameLabel = new MyTextLabel("Please enter your username:", SwingConstants.CENTER);
        usernameLabel.setTextLabel(260, 20, 450, 330, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        usernameTextField = new MyTextField();
        usernameTextField.setTextField(500, 40, 450, 350);
		
        passwordLabel = new MyTextLabel("Please enter your password:", SwingConstants.CENTER);
        passwordLabel.setTextLabel(260, 20, 450, 430, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        passwordTextField = new MyTextField();
        passwordTextField.setTextField(500, 40, 450, 450);
		
		createAccountButton = new MyButton("Create an account");
		createAccountButton.setButton(150, 50, 450, 600);
		createAccountButton.addActionListener(this);
		
		loginButton = new MyButton("Login");
		loginButton.setButton(150, 50, 450, 500);
		loginButton.addActionListener(this);

		quitButton = new MyButton("Quit");
		quitButton.setButton(150, 70, 0, 715);
		quitButton.addActionListener(this);
		
		canvas.add(loginButton);
		canvas.add(welcomeMessageLabel);
		
		canvas.add(usernameLabel);
		canvas.add(usernameTextField);
		
		canvas.add(passwordLabel);
		canvas.add(passwordTextField);
		
		canvas.add(createAccountButton);
		canvas.add(quitButton);
	}
	
	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(quitButton)) {  // User has chosen to exit the program
			System.exit(0);
		} else if (button.equals(createAccountButton)) {  // User has chosen to create an account
			if (usernameHashcode != 0 && passwordHashcode != 0) {
				JOptionPane.showMessageDialog(this, "You have already created an account. Another account cannot be created.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				setVisible(false);
				DriverClass.displayCreateAccountPage();
			}
		} else if (button.equals(loginButton)) {  // User has chosen to try logging in
			String tempUsername = usernameTextField.getText().toLowerCase().trim();
			String tempPassword = passwordTextField.getText().toLowerCase().trim();
			
			long tempUsernameHashcode = generateHashcode(tempUsername);
			long tempPasswordHashcode = generateHashcode(tempPassword);
			
			if (tempUsernameHashcode != 0 && tempPasswordHashcode != 0 && tempUsernameHashcode == usernameHashcode && tempPasswordHashcode == passwordHashcode) {
				setVisible(false);
				DriverClass.displayMainMenu();
			} else {
				JOptionPane.showMessageDialog(this, "The username or password that you entered is incorrect.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * Method for generating the hashcode for a string
	 * @param str The string whose hashcode is going to be generated
	 * @return The hashcode of the given string "str"
	 */
	public long generateHashcode(String str) {
		int n = str.length();
		long hshCode = 0;
		
		/*
		 * A prime number is chosen as the seed because a prime number is coprime to all other numbers.
		 * When overflow happens, since 131 is coprime to Long.MAX_VALUE, the generated hashcode will be secure by evenly distributing the numbers.
		 */
		int seed = 131;
		
		for (int i = 1; i <= n; i ++) {
			hshCode = hshCode * seed + str.charAt(i - 1);
		}
		
		return hshCode;
	}
	
	/**
	 * Sets the username and password of the user for this program
	 * @param username The username set by the user for this program
	 * @param password The password set by the user for this program
	 */
	public void setUsernameAndPassword(String username, String password) {
		usernameHashcode = generateHashcode(username);
		passwordHashcode = generateHashcode(password);
		
		// Saving the user's login info
		try {
			loginInfo.seek(0);
			loginInfo.writeBytes(usernameHashcode + "\n" + passwordHashcode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}