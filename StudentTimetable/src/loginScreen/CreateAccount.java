package loginScreen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import driverProgram.*;
import objects.*;

public class CreateAccount extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MyTextLabel createAccountMessageLabel, usernameLabel, passwordLabel;
	private MyTextField usernameTextField, passwordTextField;
	private MyButton backToLoginScreenButton, createAccountButton;
	
	/**
	 * Initializes the values for a "CreateAccount" object
	 * @param canvas The canvas for the "Create an account" page
	 */
	public CreateAccount(JPanel canvas) {
		super ("Create an account page");
		add(canvas);
		
		setSize(800, 800);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createAccountMessageLabel = new MyTextLabel("Create your account here", SwingConstants.CENTER);
        createAccountMessageLabel.setTextLabel(400, 70, 200, 80, Color.BLACK, Color.GREEN, new Font("Arial", Font.BOLD, 20));
        
        usernameLabel = new MyTextLabel("Username:", SwingConstants.CENTER);
        usernameLabel.setTextLabel(260, 20, 150, 330, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        usernameTextField = new MyTextField();
        usernameTextField.setTextField(500, 40, 150, 350);
		
        passwordLabel = new MyTextLabel("Password:", SwingConstants.CENTER);
        passwordLabel.setTextLabel(260, 20, 150, 430, Color.BLACK, Color.YELLOW, new Font("Arial", Font.BOLD, 18));
        
        passwordTextField = new MyTextField();
        passwordTextField.setTextField(500, 40, 150, 450);
        
        backToLoginScreenButton = new MyButton("Back");
		backToLoginScreenButton.setButton(150, 50, 0, 715);
		backToLoginScreenButton.addActionListener(this);

		createAccountButton = new MyButton("Create account");
		createAccountButton.setButton(150, 50, 150, 550);
		createAccountButton.addActionListener(this);
        
		canvas.add(createAccountMessageLabel);
		
		canvas.add(usernameLabel);
		canvas.add(usernameTextField);
		
		canvas.add(passwordLabel);
		canvas.add(passwordTextField);
		
		canvas.add(backToLoginScreenButton);
		canvas.add(createAccountButton);
	}

	public void paint(Graphics g) {
    	super.paintComponents(g);
    	repaint();
    }
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backToLoginScreenButton)) {  // User has chosen to go back to the login screen
			setVisible(false);
			DriverClass.displayLoginPage();
		} else if (button.equals(createAccountButton)) {  // User has decided to create an account
			String username = usernameTextField.getText().toLowerCase().trim();
			String password = passwordTextField.getText().toLowerCase().trim();
			
			if (username.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(this, "You must enter a username and a password.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				usernameTextField.setText("");
				passwordTextField.setText("");
				
				DriverClass.createAccount(username, password);
				
				setVisible(false);
				DriverClass.displayLoginPage();
				
				JOptionPane.showMessageDialog(this, "An account has successfuly been created.");
			}
		}
	}
}