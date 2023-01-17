package com.sms.SMS;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jpa.entitymodels.Student;

public class StudentTest {
	
	@Test
	public void testGetsName() {
		
		// Given
		Student student = new Student();
		String expectedName = "Test Student";
		student.setsName(expectedName);
		
		//When
		String actualName = student.getsName();
		
		//Then
		assertEquals(expectedName, actualName);
		
		
	}

}
