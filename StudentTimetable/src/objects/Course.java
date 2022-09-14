package objects;

// This class provides a template for creating "Course" objects
public class Course extends Node {
	private String courseCode;
	
	/**
	 * Initializes the values for a "Course" object
	 * @param courseCode The course code for this Course
	 */
	public Course(String courseCode) {
		super();
		this.courseCode = courseCode;
	}
	
	/**
	 * Get the course code for this Course
	 * @return The course code for this Course
	 */
	public String getCourseCode() {
		return courseCode;
	}
}