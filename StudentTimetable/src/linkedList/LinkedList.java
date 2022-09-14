package linkedList;

import objects.*;

// My doubly linked list class
public class LinkedList {
	private Node head, tail;
	private int size;	// Size of linked list
	
	/**
	 * Initializes the "head", "tail", and "size" values of the linked list
	 */
	public LinkedList() {
		head = tail = null;
		size = 0;
	}

	/**
	 * Get the head of the linked list
	 * @return The head of the linked list
	 */
	public Node getHead() {
		return head;
	}
	
	/**
	 * Set the head of the linked list
	 * @param n A Node which the head is assigned to
	 */
	public void setHead(Node n) {
		head = n;
	}
	
	/**
	 * Get the tail of the linked list
	 * @return The tail of the linked list
	 */
	public Node getTail() {
		return tail;
	}
	
	/**
	 * Set the tail of the linked list
	 * @param n A Node which the tail is assigned to
	 */
	public void setTail(Node n) {
		tail = n;
	}
	
	/**
	 * Add a Node to the front of the linked list
	 * @param n A Node to be added to the front of the linked list
	 */
	public void addFirst(Node n) {
		if (head == null) {
			head = tail = n;
			n.setNext(null);
			n.setPrev(null);
		} else {
			n.setNext(head);
			n.setPrev(null);
			head.setPrev(n);
			head = n;
		}
		size ++;
	}
	
	/**
	 * Add a Node to the end of the linked list
	 * @param n A Node to be added to the end of the linked list
	 */
	public void addLast(Node n) {	
		if (tail == null) {
			head = tail = n;
			n.setNext(null);
			n.setPrev(null);
		} else {
			tail.setNext(n);
			n.setPrev(tail);
			n.setNext(null);
			tail = n;
		}
		size ++;
	}
	
	/**
	 * Remove and return the first Node of the linked list
	 * @return The first Node of the linked list
	 */
	public Node removeFirst() {
		Node n = head;
		head = head.getNext();
		n.setNext(null);
		head.setPrev(null);
		size --;
		return n;
	}
	
	/**
	 * Remove and return the last Node of the linked list
	 * @return The last Node of the linked list
	 */
	public Node removeLast() {
		Node n = tail;
		tail = tail.getPrev();
		n.setPrev(null);
		tail.setNext(null);
		size --;
		return n;
	}
	
	/**
	 * Remove a particular Node from the linked list
	 * @param n The node who is to be removed from the linked list
	 * @return A particular Node from the linked list
	 */
	public Node remove(Node n) {
		if (n.getPrev() == null && n.getNext() == null) {
			head = tail = null;
			size --;
			return n;
		}
		else if (n.getPrev() == null) return(removeFirst());
		else if (n.getNext() == null) return(removeLast());
		else {
			Node prev = n.getPrev(), next = n.getNext();
			n.setPrev(null);
			n.setNext(null);
			prev.setNext(next);
			next.setPrev(prev);
			size --;
			return n;
		}
	}
	
	/**
	 * Recursive algorithm for retrieving the Node associated with a current index in the linked list
	 * @param curIdx The current index of this recursive algorithm
	 * @param curNode The current node of this recursive algorithm
	 * @param target The index of the element in this linked which which we want to retrieve
	 * @return The element in this linked list which we want to retrieve
	 */
	public Node get(int curIdx, Node curNode, int target) {
		if (curIdx == target) return curNode;
		return get(curIdx + 1, curNode.getNext(), target);
	}
	
	/**
	 * Get the size of the linked list
	 * @return The size of the linked list
	 */
	public int getSize() {
		return size;
	}
}