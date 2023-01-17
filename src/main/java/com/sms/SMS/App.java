package com.sms.SMS;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;
import jpa.util.HibernateUtil;

public class App {

static Session connectionSession = HibernateUtil.connection();
static Transaction transaction = connectionSession.beginTransaction();

    public static void main( String[] args )
    {
    	SMSRunner smsRunner = new SMSRunner();
    	// THIS IS THE METHOD TO START THE PROGRAM
    	
			try {
				smsRunner.loginSMSMenu();
			} catch (Exception e) {
				System.out.println("You did not select a valid option. Please run application again.");
				smsRunner.exitProgram();			
			}
    	
    }
}
