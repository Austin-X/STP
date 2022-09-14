package objects;

import javax.swing.*;

// Custom class for allowing easier creation of buttons
public class MyButton extends JButton {
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the values for a "MyButton" object
	 * @param text The title of this MyButton
	 */
	public MyButton (String text) {
		super (text, null);
	}
	
	/**
	 * Set the width, height, and position of this MyButton
	 * @param width The width of this MyButton
	 * @param height The height of this MyButton
	 * @param x The horizontal location of this MyButton
	 * @param y The vertical location of this MyButton
	 */
	public void setButton(int width, int height, int x, int y) {
		setSize(width, height);
		setLocation(x, y);
		setOpaque(true);
	}
}