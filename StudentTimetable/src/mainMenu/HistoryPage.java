package mainMenu;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import driverProgram.*;
import objects.*;

public class HistoryPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTextArea display;   // This text area will display the history of actions
	
	private MyButton backButton, clearHistoryButton;
	
	/**
	 * Initializes the values for a "HistoryPage" object
	 */
	public HistoryPage() {
		super("History Page");
		
		JPanel canvas = new JPanel();
		canvas.setBorder(new TitledBorder (new EtchedBorder(), "                      History                    "));
		
		display = new JTextArea(16, 58);
		display.setEditable(false); 
		
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
		backButton = new MyButton("Back");
		backButton.addActionListener(this);

		clearHistoryButton = new MyButton("Clear History");
		clearHistoryButton.addActionListener(this);

		canvas.add(backButton);
		canvas.add(scroll);
		canvas.add(clearHistoryButton);  
		add(canvas);
		pack();

		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Adding an event to the history page
	 * @param str The string representing the event which will be added to the history page
	 * @param addToRandomAccessFile "True" if this event should be added to the "HistoryInfo.txt" RandomAccessFile, "false" otherwise
	 */
	public void addText(String str, boolean addToRandomAccessFile) {
		display.append(str + "\n");
		pack();
		
		if (addToRandomAccessFile) {
			// Adding the details of the current event to the "HistoryInfo.txt" RandomAccessFile.
			try {
				RandomAccessFile historyInfo = new RandomAccessFile("HistoryInfo.txt", "rw");
				historyInfo.seek(historyInfo.length());
				historyInfo.writeBytes(str + "\n");
				historyInfo.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Clearing the history of all events
	 * @param addToRandomAccessFile "True" if the "HistoryInfo.txt" RandomAccessFile should clear all events from its history, "false" otherwise
	 */
	public void clearHistory(boolean addToRandomAccessFile) {
		display.setText("");
		
		if (addToRandomAccessFile) {
			// Delete all of the content in the "HistoryInfo.txt" RandomAccessFile.
			try {
				RandomAccessFile historyInfo = new RandomAccessFile("HistoryInfo.txt", "rw");
				historyInfo.setLength(0);
				historyInfo.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		MyButton button = (MyButton) e.getSource();
		
		if (button.equals(backButton)) {   // User wants to go back to the main menu page
			setVisible(false);
			DriverClass.displayMainMenu();
		} else if (button.equals(clearHistoryButton)) {   // User wants to clear the details of all events that have occurred
			clearHistory(true);
		}
	}
}
