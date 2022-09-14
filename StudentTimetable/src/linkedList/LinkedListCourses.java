package linkedList;

import objects.*;

public class LinkedListCourses extends LinkedList {
	
	/**
	 * Initializes the values for a "LinkedListCourses" object
	 */
	public LinkedListCourses() {
		super();
	}
	
	/**
	 * Get the head of the linked list
	 * @return The head of the linked list
	 */
	public Course getHead() {
		return (Course) super.getHead();
	}
	
	/**
	 * Get the tail of the linked list
	 * @return The tail of the linked list
	 */
	public Course getTail() {
		return (Course) super.getTail();
	}
	
	/**
	 * Add a Course to the front of the linked list
	 * @param c A Course to be added to the front of the linked list
	 */
	public void addFirst(Course c) {
		super.addFirst(c);
	}
	
	/**
	 * Add a Course to the end of the linked list
	 * @param c A Course to be added to the end of the linked list
	 */
	public void addLast(Course c) {
		super.addLast(c);
	}

	/**
	 * Remove and return the first Course of the linked list
	 * @return The first Course of the linked list
	 */
	public Course removeFirst() {
		return (Course) super.removeFirst();
	}

	/**
	 * Remove and return the last Course of the linked list
	 * @return The last Course of the linked list
	 */
	public Course removeLast() {
		return (Course) super.removeLast();
	}

	/**
	 * Remove a particular Course from the linked list
	 * @param c A Course to be removed from the linked list
	 * @return The removed Course from the linked list
	 */
	public Course remove(Course c) {
		return (Course) super.remove(c);
	}

	/**
	 * Retrieve the Course associated with a particular index in the linked list
	 * @return A particular Course from the linked list
	 */
	public Course get(int curIdx, Node curNode, int target) {
		return (Course) super.get(curIdx, curNode, target);
	}
	
	/**
	 * Check if the linked list contains a particular Course
	 * @param s A course code which is to be checked if it is contained in the list
	 * @return True if 's' is in the linked list, false otherwise
	 */
	public boolean contains (String s) {  // Check if the list contains a certain course
		Course cur = (Course) super.getHead();
		
		for (int i = 0; i < super.getSize(); i ++) {
			if (cur.getCourseCode().equals(s)) return true;
			cur = (Course) cur.getNext();
		}
		
		return false;
	}
}