package mainMenu;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

// Canvas for the "Add a student" page and "Add a teacher" page
public class AddAPersonCanvas extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage background;
	
	/**
	 * Initializes the values for a "AddAPersonCanvas" object
	 */
	public AddAPersonCanvas() {
		try {
			background = ImageIO.read(this.getClass().getResource("/addAPersonBackground.jpg"));
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