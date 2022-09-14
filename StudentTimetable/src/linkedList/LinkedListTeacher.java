package linkedList;

import objects.*;

public class LinkedListTeacher extends LinkedListPerson {
	
	/**
	 * Initializes the values for a "LinkedListStudent" object
	 */
	public LinkedListTeacher() {
		super();
	}
	
	/**
	 * Get the head of the linked list
	 * @return The head of the linked list
	 */
	public Teacher getHead() {
		return (Teacher) super.getHead();
	}
	
	/**
	 * Get the tail of the linked list
	 * @return The tail of the linked list
	 */
	public Teacher getTail() {
		return (Teacher) super.getTail();
	}
	
	/**
	 * Add a Teacher to the front of the linked list
	 * @param tch A Teacher to be added to the front of the linked list
	 */
	public void addFirst(Teacher tch) {
		super.addFirst(tch);
	}
	
	/**
	 * Add a Teacher to the end of the linked list
	 * @param tch A Teacher to be added to the end of the linked list
	 */
	public void addLast(Teacher tch) {
		super.addLast(tch);
	}

	/**
	 * Remove and return the first Teacher of the linked list
	 * @return The first Teacher of the linked list
	 */
	public Teacher removeFirst() {
		return (Teacher) super.removeFirst();
	}
	
	/**
	 * Remove and return the last Teacher of the linked list
	 * @return The last Teacher of the linked list
	 */
	public Teacher removeLast() {
		return (Teacher) super.removeLast();
	}

	/**
	 * Remove a particular Teacher from the linked list
	 * @param tch A Teacher to be removed from the linked list
	 * @return The removed Teacher from the linked list
	 */
	public Teacher remove(Teacher tch) {
		return (Teacher) super.remove(tch);
	}

	/**
	 * Retrieve the Teacher associated with a particular index in the linked list
	 * @return A particular Teacher from the linked list
	 */
	public Teacher get(int curIdx, Node curNode, int target) {
		return (Teacher) super.get(curIdx, curNode, target);
	}
}