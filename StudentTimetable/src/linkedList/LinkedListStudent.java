package linkedList;

import objects.*;

public class LinkedListStudent extends LinkedListPerson {
	
	/**
	 * Initializes the values for a "LinkedListStudent" object
	 */
	public LinkedListStudent() {
		super();
	}
	
	/**
	 * Get the head of the linked list
	 * @return The head of the linked list
	 */
	public Student getHead() {
		return (Student) super.getHead();
	}
	
	/**
	 * Get the tail of the linked list
	 * @return The tail of the linked list
	 */
	public Student getTail() {
		return (Student) super.getTail();
	}
	
	/**
	 * Add a Student to the front of the linked list
	 * @param st A Student to be added to the front of the linked list
	 */
	public void addFirst(Student st) {
		super.addFirst(st);
	}
	
	/**
	 * Add a Student to the end of the linked list
	 * @param st A Student to be added to the end of the linked list
	 */
	public void addLast(Student st) {
		super.addLast(st);
	}

	/**
	 * Remove and return the first Student of the linked list
	 * @return The first Student of the linked list
	 */
	public Student removeFirst() {
		return (Student) super.removeFirst();
	}

	/**
	 * Remove and return the last Student of the linked list
	 * @return The last Student of the linked list
	 */
	public Student removeLast() {
		return (Student) super.removeLast();
	}

	/**
	 * Remove a particular Student from the linked list
	 * @param st A Student to be removed from the linked list
	 * @return The removed Student from the linked list
	 */
	public Student remove(Student st) {
		return (Student) super.remove(st);
	}

	/**
	 * Retrieve the Student associated with a particular index in the linked list
	 * @return A particular Student from the linked list
	 */
	public Student get(int curIdx, Node curNode, int target) {
		return (Student) super.get(curIdx, curNode, target);
	}
	
	/**
	 * Sort the linked list of students alphabetically
	 */
	public void sortAlphabetically(boolean flag) {
		super.sortAlphabetically(flag);
	}
}