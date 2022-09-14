package linkedList;

import objects.*;

public class LinkedListPerson extends LinkedList {
	
	/* The "originalUnsortedList" array contains the list of people in the order that they were added in.
	 * The "alphabeticallySortedList" array contains the list of people sorted in alphabetical order. */
	private Person[] originalUnsortedList, alphabeticallySortedList;
	
	private boolean sorted;   // Variable that keeps track if the current list is sorted alphabetically or not
	
	/**
	 * Initializes the values for a "LinkedListPerson" object
	 */
	public LinkedListPerson() {
		super();
		sorted = false;
		originalUnsortedList = new Person[super.getSize()];
		alphabeticallySortedList = new Person[super.getSize()];
	}
	
	/**
	 * Get the head of the linked list
	 * @return The head of the linked list
	 */
	public Person getHead() {
		return (Person) super.getHead();
	}
	
	/**
	 * Get the tail of the linked list
	 * @return The tail of the linked list
	 */
	public Person getTail() {
		return (Person) super.getTail();
	}
	
	/**
	 * Add a Person to the front of the linked list
	 * @param p A Person to be added to the front of the linked list
	 */
	public void addFirst(Person p) {
		super.addFirst(p);
	}
	
	/**
	 * Add a Person to the end of the linked list
	 * @param p A Person to be added to the end of the linked list
	 */
	public void addLast(Person p) {
		super.addLast(p);
	}

	/**
	 * Remove and return the first Person of the linked list
	 * @return The first Person of the linked list
	 */
	public Person removeFirst() {
		return (Person) super.removeFirst();
	}

	/**
	 * Remove and return the last Person of the linked list
	 * @return The last Person of the linked list
	 */
	public Person removeLast() {
		return (Person) super.removeLast();
	}

	/**
	 * Remove a particular Person from the linked list
	 * @param p A person to be removed from the linked list
	 * @return The removed Person from the linked list
	 */
	public Person remove(Person p) {
		return (Person) super.remove(p);
	}

	/**
	 * Retrieve the Person associated with a particular index in the linked list
	 * @return A particular Person from the linked list
	 */
	public Person get(int curIdx, Node curNode, int target) {
		return (Person) super.get(curIdx, curNode, target);
	}
	
	/**
	 * Checks if the linked list of people is currently sorted alphabetically or not
	 * @return True if the linked list of people is sorted alphabetically, false otherwise
	 */
	public boolean isSorted() {
		return sorted;
	}
	
	/**
	 * Sort the linked list of people alphabetically
	 * @param flag "True" if the user has chosen to sort the linked list of people alphabetically, "false" if the user has chosen to unsort the list of people back into its original order (i.e. sort the list of people by date added)
	 */
	public void sortAlphabetically(boolean flag) {
		int size = super.getSize();  // Size of linked list
		
		// If the user has chosen to sort the list alphabetically
		if (flag) {
			alphabeticallySortedList = new Person[super.getSize()];   // Array used to store the alphabetically sorted list of people
			
			boolean[] vis = new boolean[size];  // Stores which indices of "originalUnsortedList" have been assigned to the "alphabeticallySortedList"
			
			// My sorting algorithm for sorting the list of people alphabetically.
			for (int i = 0; i < size; i ++) {
				int minIdx = -1;  // "minIdx" stores the index of an element in "originalUnsortedList" which has "!vis[index]" and is the smallest lexicographically
				
				for (int j = 0; j < size; j ++) {  // Looping from the indices of "originalUnsortedList"
					if (!vis[j]) {  // "vis[j]" is "false" if "originalUnsortedList[j]" is not in the "alphabeticallySortedList" yet
						if (minIdx == -1) {  // If the default value for "minIdx" is used, update it to 'j'
							minIdx = j;
						} else {  // Compare the alphabetic values of "originalUnsortedList[minIdx]" with "originalUnsortedList[j]"
							String minName = originalUnsortedList[minIdx].getName();
							String currentName = originalUnsortedList[j].getName();
						
							// If "originalUnsortedList[j]" is lexicographically smaller than "originalUnsortedList[minIdx]", update the value of "minIdx"
							if (currentName.compareTo(minName) < 0) minIdx = j;
						}
					}
				}
				
				alphabeticallySortedList[i] = originalUnsortedList[minIdx];  // Set the 'i-th' smallest element in "alphabeticallySortedList"
				vis[minIdx] = true;  // Mark "vis[minIdx]" as true, so "minIdx" can no longer be analyzed from the "originalUnsortedList" in future iterations
			}
			
			// Since this sorting algorithm deals with objects, it is necessary to reset the pointers of the "originalUnsortedList"
			for (int i = 0; i < size; i ++) {
				originalUnsortedList[i].setPrev(null);
				originalUnsortedList[i].setNext(null);
			}
			
			// Setting up the list based on the sorted "alphabeticallySortedList" array
			for (int i = 0; i < size; i ++) {
				if (size == 1) {
					super.setHead(alphabeticallySortedList[i]);
					super.setTail(alphabeticallySortedList[i]);
				} else if (i == 0) {
					super.setHead(alphabeticallySortedList[i]);
				} else if (i == size - 1) {
					super.setTail(alphabeticallySortedList[i]);
					alphabeticallySortedList[i - 1].setNext(alphabeticallySortedList[i]);
					alphabeticallySortedList[i].setPrev(alphabeticallySortedList[i - 1]);
				} else {
					alphabeticallySortedList[i].setPrev(alphabeticallySortedList[i - 1]);
					alphabeticallySortedList[i - 1].setNext(alphabeticallySortedList[i]);
				}	
			}
			
			sorted = true;   // Indicates that the list is currently sorted alphabetically
		} 
		
		// If the user has chosen to unsort the list back into its original order.
		else {
			if (alphabeticallySortedList.length == 0) return;
			
			for (int i = 0; i < size; i ++) {
				alphabeticallySortedList[i].setPrev(null);
				alphabeticallySortedList[i].setNext(null);
			}
			
			// Setting up the list based on the original ordering of the list (i.e. sorting the list in terms of date added)
			for (int i = 0; i < size; i ++) {
				if (size == 1) {
					super.setHead(originalUnsortedList[i]);
					super.setTail(originalUnsortedList[i]);
				} else if (i == 0) {
					super.setHead(originalUnsortedList[i]);
				} else if (i == size - 1) {
					super.setTail(originalUnsortedList[i]);
					originalUnsortedList[i - 1].setNext(originalUnsortedList[i]);
					originalUnsortedList[i].setPrev(originalUnsortedList[i - 1]);
				} else {
					originalUnsortedList[i].setPrev(originalUnsortedList[i - 1]);
					originalUnsortedList[i - 1].setNext(originalUnsortedList[i]);
				}	
			}	
			
			sorted = false;   // Indicates that the list is currently not sorted alphabetically
		}
	}
	
	/**
	 * Setting up the original unsorted list of people, or, in other words, setting up the list of people by date added
	 */
	public void setOriginalList() {
		originalUnsortedList = new Person[super.getSize()];

		// Copy elements of list into the "originalUnsortedList" array.
		int idx = 0;
		Person cur = (Person) super.getHead();
		while (cur != null) {
			originalUnsortedList[idx] = cur;
			cur = (Person) cur.getNext();
			idx ++;
		}
	}
	
	/**
	 * Update the original unsorted list to accommodate for removing a person from the list
	 * @param removed A person which was removed from the list
	 */
	public void updateOriginalList(Person removed) {
		Person[] temp = new Person[super.getSize()];
		
		// Copying all elements from "originalUnsortedList" array to "temp" array excluding the person that has been removed
		for (int i = 0, j = 0; i < super.getSize(); i ++, j ++) {
			if (originalUnsortedList[j] != removed) {
				temp[i] = originalUnsortedList[j];
			} else {
				j ++;
				temp[i] = originalUnsortedList[j];
			}
		}
		
		originalUnsortedList = temp;   // The original list of people have been updated
	}
}