package objects;

import java.awt.*;
import javax.swing.*;

// Custom class for allowing easier creation of labels
public class MyTextLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the values for a "MyTextLabel" object
	 * @param text The text of this MyTextLabel
	 * @param center The positioning of the text for this MyTextLabel
	 */
	public MyTextLabel(String text, int center) {
		super (text, null, center);
	}
	
	/**
	 * Set the width, height, position, foreground colour, background colour, and font for this MyTextLabel
	 * @param width The width of this MyTextLabel
	 * @param height The height of this MyTextLabel
	 * @param x The horizontal location of this MyTextLabel
	 * @param y The vertical location of this MyTextLabel
	 * @param foregroundColour The foreground colour of this MyTextLabel
	 * @param backgroundColour The background colour of this MyTextLabel
	 * @param f The font of this MyTextLabel
	 */
	public void setTextLabel(int width, int height, int x, int y, Color foregroundColour, Color backgroundColour, Font f) {
		setSize(width, height);
		setLocation(x, y);
		setForeground(foregroundColour);
		setBackground(backgroundColour);
		setFont(f);
		setOpaque(true);
	}
}