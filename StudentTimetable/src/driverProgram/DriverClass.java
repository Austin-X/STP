package driverProgram;

/**
 * @author Austin Xu
 * High school student timetable program
 * IDE - Eclipse
 * Platform - PC
 */
import linkedList.*;
import loginScreen.*;
import mainMenu.*;
import objects.*;

public class DriverClass {
	private static LoginPage loginPage;
	private static LoginPageCanvas loginCanvas, createAccountCanvas;
	private static CreateAccount createAccountPage;
	
	private static MainMenu mainMenuPage;
	private static MainMenuCanvas mainMenuCanvas;
	
	private static AddAStudentPage addAStudent;
	private static AddAPersonCanvas addAStudentCanvas;

	private static EditCurrentStudentsPage editCurrentStudents;
	
	private static ViewCoursesPage viewCourses;
	
	private static AddATeacherPage addATeacher;
	private static AddAPersonCanvas addATeacherCanvas;
	
	private static EditCurrentTeachersPage editCurrentTeachers;

	private static ContactInformationPage contactInformationPage;
	
	private static HistoryPage historyPage;
	
	private static ImageCitationsPage imageCitationsPage;
	
	/**
	 * The main method used in running this program
	 * @param args Unused
	 */
	public static void main(String[] args) {
		loginCanvas = new LoginPageCanvas();
		loginPage = new LoginPage(loginCanvas);
		
		createAccountCanvas = new LoginPageCanvas();
		createAccountPage = new CreateAccount(createAccountCanvas);
		
		historyPage = new HistoryPage();
		
		mainMenuCanvas = new MainMenuCanvas();
		mainMenuPage = new MainMenu(mainMenuCanvas);
		
		addAStudentCanvas = new AddAPersonCanvas();
		addAStudent = new AddAStudentPage(addAStudentCanvas);
		
		editCurrentStudents = new EditCurrentStudentsPage();
		
		viewCourses = new ViewCoursesPage();
		
		addATeacherCanvas = new AddAPersonCanvas();
		addATeacher = new AddATeacherPage(addATeacherCanvas); 
		
		editCurrentTeachers = new EditCurrentTeachersPage();
		
		contactInformationPage = new ContactInformationPage();
		
		imageCitationsPage = new ImageCitationsPage();
	}
	
	/**
	 * Adds an event to the text area of the history page
	 * @param str The string of the event being displayed in history page
	 * @param addToRandomAccessFile "True" if the this event should be added to the "HistoryInfo" text file, "false" otherwise
	 */
	public static void addEventToHistory(String str, boolean addToRandomAccessFile) {
		historyPage.addText(str, addToRandomAccessFile);
	}
	
	/**
	 * Adds a student to the main menu page
	 * @param stu A student
	 */
	public static void addStudent(Student stu) {
		mainMenuPage.addStudent(stu);
	}
	
	/**
	 * Add a teacher to the main menu page
	 * @param tch A teacher
	 */
	public static void addTeacher(Teacher tch) {
		mainMenuPage.addTeacher(tch);
	}
	
	/**
	 * Clear the history from the history page
	 * @param addToRandomAccessFile "True" if the history should be cleared from the "HistoryInfo" text file, "false" otherwise
	 */
	public static void clearHistory(boolean addToRandomAccessFile) {
		historyPage.clearHistory(addToRandomAccessFile);
	}
	
	/**
	 * Set the username and password for the timetable program
	 * @param username The user's chosen username upon creating their account
	 * @param password The user's chosen password upon creating their account
	 */
	public static void createAccount(String username, String password) {
		loginPage.setUsernameAndPassword(username, password);
	}
	
	/**
	 * Display the "Add A Student" page
	 */
	public static void displayAddAStudentPage() {
		addAStudent.setVisible(true);
	}
	
	/**
	 * Display the "Add A Teacher" page
	 */
	public static void displayAddATeacherPage() {
		addATeacher.setVisible(true);
	}
	
	/**
	 * Display the "Contact Information" page
	 */
	public static void displayContactInformationPage() {
		contactInformationPage.setLists(mainMenuPage.getStudentsList(), mainMenuPage.getTeachersList());
		contactInformationPage.setVisible(true);
	}
	
	/**
	 * Display the "Create an account" page
	 */
	public static void displayCreateAccountPage() {
		createAccountPage.setVisible(true);
	}
	
	/**
	 * Display the "Edit current students" page and set the list of students in the "Edit current students" page
	 */
	public static void displayEditCurrentStudentsPage() {
		editCurrentStudents.setStudentsList(mainMenuPage.getStudentsList());
		editCurrentStudents.setVisible(true);
	}
	
	/**
	 * Display the "Edit current teachers" page and set the list of teachers in the "Edit current teachers" page
	 */
	public static void displayEditCurrentTeachersPage() {
		editCurrentTeachers.setTeachersList(mainMenuPage.getTeachersList());
		editCurrentTeachers.setVisible(true);
	}
	
	/**
	 * Display the "History" page
	 */
	public static void displayHistoryPage() {
		historyPage.setVisible(true);
	}
	
	/**
	 * Display the "Image Citations" page
	 */
	public static void displayImageCitationsPage() {
		imageCitationsPage.setVisible(true);
	}
	
	/**
	 * Display the "Login Screen" page
	 */
	public static void displayLoginPage() {
		loginPage.setVisible(true);
	}
	
	/**
	 * Display the "Main Menu" page
	 */
	public static void displayMainMenu() {
		mainMenuPage.setVisible(true);
		mainMenuPage.updateCnt();
	}
	
	/**
	 * Display the "View All Possible Courses Offered" page
	 */
	public static void displayViewCoursesPage() {
		viewCourses.setVisible(true);
	}
	
	/**
	 * Get the list of students from the main menu page
	 * @return A list of students
	 */
	public static LinkedListStudent getStudents() {
		return mainMenuPage.getStudentsList();
	}
	
	/**
	 * Get the list of teachers from the main menu page
	 * @return A list of teachers
	 */
	public static LinkedListTeacher getTeachers() {
		return mainMenuPage.getTeachersList();
	}
}