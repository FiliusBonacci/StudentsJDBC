package com.students.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.students.domain.StudentIndex;
import com.students.domain.Student;
import com.students.domain.Grade;
import com.students.service.StudentManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class StudentManagerTest {

	@Autowired
	StudentManager studentManager;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

	private final String NAME1 = "Student1";
	private final long ID1 = 100;
	String date1 = "January 2, 1980";
	private final LocalDate BIRTHDAY1 = LocalDate.parse(date1, formatter);
	private final Date BIRTHDAY_1 = new Date(1980, 1, 14);

	private final Long ID2 = Long.valueOf(200);
	private final String NAME_2 = "Student2";
	String date2 = "February 2, 1980";
	private final LocalDate BIRTHDAY2 = LocalDate.parse(date2, formatter);
	private final Date BIRTHDAY_2 = new Date(1988, 2, 6);

	private final float GRADE1 = (float) 4.5;
	private final boolean APPROVED1 = true;

	private final float GRADE2 = (float) 3.5;
	private final boolean APPROVED2 = true;

	@Test
	public void addStudentTest() {
		List<Student> retrievedStudents = studentManager.getAllStudents();

		// If there is a student with ID1 delete him/her
		for (Student student : retrievedStudents) {
			if (student.getId().equals(ID1)) {
				studentManager.removeStudent(student);
			}
		}

		Student student = new Student();
		student.setFirstName(NAME1);
		student.setId(ID1);
		student.setDateOfBirth((java.sql.Date) BIRTHDAY_1);

		// id is Unique
		studentManager.addStudent(student);

		Student retrievedStudent = studentManager.findStudentById(ID1);

		assertEquals(NAME1, retrievedStudent.getFirstName());
		assertEquals(BIRTHDAY1, retrievedStudent.getDateOfBirth());
	}

	@Test
	public void getAllStudentsTest() {
		int numberOfStudents = studentManager.getAllStudents().size();
		assertThat(numberOfStudents, either(is(0)).or(is(1)));
	}

	




}