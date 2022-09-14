package mainMenu;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

// Canvas for the "Main Menu" page
public class MainMenuCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	
	public MainMenuCanvas() {
		try {
			background = ImageIO.read(this.getClass().getResource("/mainMenuBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}