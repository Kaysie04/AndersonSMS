package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO {

	List<Student> getAllStudents();
	
	Student getStudentByEmail(String email);
	
	Boolean validateStudent(String email, String password);
	
	void registerStudentToCourse(String email, int id);
	
	void getStudentCourses(String email);

}
