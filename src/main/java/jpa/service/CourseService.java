package jpa.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.util.HibernateUtil;

public class CourseService extends HibernateUtil implements CourseDAO {

	
	/** 
	 * return a list of all courses that are in the database
	 */
	@Override
	public List<Course> getAllCourses() {
		Session connectionSession = HibernateUtil.connection();
		
		String hql = "From Course";
		TypedQuery<Course> hqlQuery = connectionSession.createQuery(hql, Course.class);
		List<Course> allCourses = hqlQuery.getResultList();
		
		for(Course course : allCourses) {
			System.out.println("Course Id: " + course.getcId() + " Course Name " + course.getcName() + " Course Instructor " + course.getcInstructorName());
		}
		
		return allCourses;
		
	}

}
