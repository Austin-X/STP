package mainMenu;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import driverProgram.*;
import objects.*;

// This page is just for displaying all image citations (in MLA format) that have been used as background images for this program
public class ImageCitationsPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private MyButton backButton;
	
	/**
	 * Initializes the values for a "ImageCitationsPage" object
	 */
	public ImageCitationsPage() {
		super("Image Citations Page");
		
		Canvas canvas = new Canvas();
		
		add(canvas);
        
		setSize(1000, 600);
        setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
		backButton = new MyButton("Back");
        backButton.setButton(100, 40, 0, 560);
        backButton.addActionListener(this);
        
        add(backButton, BorderLayout.WEST);
	}
	
	/**
	 * 
	 * @author Austin
	 * Canvas for the "Image Citations" page
	 */
	static class Canvas extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private BufferedImage background;
		
		/**
		 * Initializes the values for a "Canvas" object
		 */
		public Canvas() {
			try {
				background = ImageIO.read(this.getClass().getResource("/imageCitationsBackground.png"));
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

	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			setVisible(false);
			DriverClass.displayMainMenu();
		}
	}
}