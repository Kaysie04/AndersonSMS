package jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.sms.SMS.SMSRunner;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.util.HibernateUtil;

public class StudentService extends HibernateUtil implements StudentDAO {

	static Session connectionSession = HibernateUtil.connection();
	static Transaction transaction = connectionSession.beginTransaction();
	
	/** 
	 * return a list of all students that are in the database
	 */

	@Override
	public List<Student> getAllStudents() {

		String hql = "FROM Student";
		TypedQuery<Student> hqlQuery = connectionSession.createQuery(hql, Student.class);
		List<Student> allStudents = hqlQuery.getResultList();

		for (Student student : allStudents) {
			System.out.println("Student Email " + student.getsEmail() + " Student Name " + student.getsName());
		}
		return allStudents;
		
	}

	/**
	 * return a Student object based on their email
	 */
	@Override
	public Student getStudentByEmail(String email) {

		String hql = "FROM Student WHERE email = :email";
		TypedQuery<Student> hqlQuery = connectionSession.createQuery(hql, Student.class);
		hqlQuery.setParameter("email", email);
		Student student = (Student) hqlQuery.getSingleResult();

		System.out.println("Student Name " + student.getsName());
		return student;

	}
	
	/**
	 * ensure student credentials are in the database
	 */

	@Override
	public Boolean validateStudent(String email, String password) {
		
		String hql = "FROM Student WHERE email = :email";
		TypedQuery<Student> hqlQuery = connectionSession.createQuery(hql, Student.class);
		hqlQuery.setParameter("email", email);
		Student studentLoggingIn = hqlQuery.getSingleResult();

			if (studentLoggingIn.getsEmail().equals(email) && studentLoggingIn.getsPass().equals(password)) {
	
				return true;
	
			}
			else {
				System.out.println("Invalid Credentials");
				return false;
			}	
		

	}

	/**
	 * add a course to a students courseList based on student email and course Id
	 */
	@Override
	public void registerStudentToCourse(String email, int id) {

		String hqlCourse = "FROM Course WHERE id = :id";
		TypedQuery<Course> hqlQueryCourse = connectionSession.createQuery(hqlCourse, Course.class);
		hqlQueryCourse.setParameter("id", id);
		Course courseToRegisterFor = (Course) hqlQueryCourse.getSingleResult();

		String hqlStudent = "FROM Student WHERE email = :email";
		TypedQuery<Student> hqlQueryStudent = connectionSession.createQuery(hqlStudent, Student.class);
		hqlQueryStudent.setParameter("email", email);
		Student registeringStudent = (Student) hqlQueryStudent.getSingleResult();

		registeringStudent.getsCourses();

		List<Course> currentStudentCourses = registeringStudent.getsCourses();
		currentStudentCourses.add(courseToRegisterFor);
		connectionSession.save(registeringStudent);
		transaction.commit();

		System.out.println(
				"Student Name " + registeringStudent.getsName() + " Course Name " + courseToRegisterFor.getcName());

	}
	
	/**
	 * gets all courses that is a student is registered to based on their email
	 */

	@Override
	public void getStudentCourses(String email) {

		String hqlStudent = "FROM Student WHERE email = :email";
		TypedQuery<Student> hqlQueryStudent = connectionSession.createQuery(hqlStudent, Student.class);
		hqlQueryStudent.setParameter("email", email);
		Student registeredStudent = (Student) hqlQueryStudent.getSingleResult();
		
		for (Course courseInfo : registeredStudent.getsCourses()) {

			System.out.println("Course Id: " + courseInfo.getcId() + " Course Name: " + courseInfo.getcName()
					+ " Instructor Name: " + courseInfo.getcInstructorName());

		}

	}

}
