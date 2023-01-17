package com.sms.SMS;

import java.util.Scanner;

import org.hibernate.Session;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;
import jpa.util.HibernateUtil;

public class SMSRunner {

	Student studentClass = new Student();
	private String email;
	private String password;
	
	static Session connectionSession = HibernateUtil.connection();

	static Scanner userInput = new Scanner(System.in);

	StudentDAO studentService = new StudentService();

	CourseDAO courseService = new CourseService();

	Student registeringStudent = new Student();

	/**
	 * This is the first method that the user sees It prompts the user to either
	 * sign in as a student or quit the program
	 * 
	 */
	public void loginSMSMenu() throws Exception {

		int selected = 0;
		System.out.println("Enter corresponding number to choose a selection");
		System.out.println("1. I am a student");
		System.out.println("2. Quit Program");

		selected = userInput.nextInt();
		userInput.nextLine();

		if (selected == 1) {
			SMSMenu();
		} else if (selected == 2) {
			exitProgram();

		} else
			System.out.println("You did not select a valid option. Try again\n");
		loginSMSMenu();
	}

	/**
	 * If the user chooses to login as a student this method validates the users if
	 * the credentials are validated the MenuOptions display for the user and run
	 * methods according to the users choices
	 */

	public void SMSMenu() {
		try {
			System.out.println("Enter your email");
			email = userInput.nextLine();
			System.out.println("Enter your password");
			password = userInput.nextLine();

			if (studentService.validateStudent(email, password)) {

				int selected = 0;

				studentService.getStudentCourses(email);
				System.out.println();
				System.out.println(
						"Listed above are your current registered courses, if there is nothing listed you are currently not registered for any courses.\n");
				SMSMenuOptions();
				selected = userInput.nextInt();
				userInput.nextLine();

				/**
				 * if user selects choice 1 from SMSMenuOptions()
				 */

				if (selected == 1) {
					courseService.getAllCourses();
					System.out.println();
					System.out.println("Select which course you want to register for using the corresponding number");

					selected = userInput.nextInt();
					userInput.nextLine();

					if (selected > 0 && selected < 11) {

						studentService.registerStudentToCourse(email, selected);
						studentService.getStudentCourses(email);
						System.out.println();
						System.out.println("You have successfully registered\n");
						System.out.println("Above is your updated course list\n");

						exitProgram();
					}

				// if user selects choice 2 from SMSMenuOptions()

				} else if (selected == 2) {
					exitProgram();
				}
			}

			// User inputs anything that is an invalid option they will be re-prompted to
			// re-login
		} catch (Exception e) {
			System.out.println("If you have not logged in, the credentials you entered were invalid."
					+ " \nIf you were logged in and attempted to register for a course, you were already registered for that course and cannot register again. "
					+ "\nOr you input an invalid option. \nPlease try again.\n");
			SMSMenu();
		}

	}

	/**
	 * List of options that are displayed to the user to choose from once they are
	 * logged in
	 */
	public void SMSMenuOptions() {

		System.out.println("You have entered Student Registration, please select from the following:\n");
		System.out.println("1. Register to a new class");
		System.out.println("2. Logout");
	}

	/**
	 * Quits program
	 */
	public void exitProgram() {
		System.out.println("You have exited the program");
		System.exit(0);
		connectionSession.close();

	}

}
