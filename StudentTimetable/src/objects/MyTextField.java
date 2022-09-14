package objects;

import javax.swing.*;

// Custom class for allowing easier creation of text fields
public class MyTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	/**
	 * Set the width, height, and position of this MyTextField
	 * @param width The width of this MyTextField
	 * @param height The height of this MyTextField
	 * @param x The horizontal location of this MyTextField
	 * @param y The vertical location of this MyTextField
	 */
	public void setTextField(int width, int height, int x, int y) {
		setSize(width, height);
		setLocation(x, y);
	}
}