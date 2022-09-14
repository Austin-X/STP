package objects;

public class Node {
	private Node next, prev;
	
	/**
	 * Initializes the values for a "Node" object
	 */
	public Node() {
		next = prev = null;
	}
	
	/**
	 * Set the next Node of this current Node
	 * @param n The next Node of this current Node
	 */
	public void setNext(Node n) {
		next = n;
	}
	
	/**
	 * Get the next Node of this current Node
	 * @return The next node of this current Node
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * Set the previous Node of this current Node
	 * @param n The previous Node of this current Node
	 */
	public void setPrev(Node n) {
		prev = n;
	}
	
	/**
	 * Get the previous node of this current Node
	 * @return The previous Node of this current Node
	 */
	public Node getPrev() {
		return prev;
	}
}