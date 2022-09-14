package loginScreen;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

// Canvas for the "Login" page
public class LoginPageCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	
	/**
	 * Initializes the values for a "LoginPageCanvas" object
	 */
	public LoginPageCanvas() {
		try {
			background = ImageIO.read(this.getClass().getResource("/loginScreenBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
	}
	
	/**
	 * Setting up the background image for this canvas
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}