package com.students.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.hql.ast.SqlASTFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	@SuppressWarnings("deprecation")
	private final Date BIRTHDAY_1 = new Date(1,1,1988);

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
		int studentsCountBeforeAdding = studentManager.getAllStudents().size();

		// If there is a student with ID1 delete him/her
		for (Student student : retrievedStudents) {
			if (student.getId().equals(ID1)) {
				studentManager.removeStudent(student);
			}
		}

		Student student = new Student();
		student.setFirstName(NAME1);
		student.setId(ID1);
		student.setDateOfBirth(BIRTHDAY_1);

		studentManager.addStudent(student);

		int studentsCountAfterAdding = studentManager.getAllStudents().size();
		
		assertEquals(studentsCountAfterAdding - 1, studentsCountBeforeAdding);
		
	}

	@Test
	public void getAllStudentsTest() {
		studentManager.removeAllStudents();
		int numberOfStudents = studentManager.getAllStudents().size();
		assertThat(numberOfStudents, either(is(0)).or(is(1)));
	}

	@Test
	public void updateStudentTest() {
		Student s1 = new Student(ID1, NAME1, BIRTHDAY_1);
		studentManager.addStudent(s1);
		
		int studentListCount = studentManager.getAllStudents().size();
		Student retrievedStudent = studentManager.getAllStudents().get(studentListCount - 1);
//		retrievedStudent.setId(ID2);
		retrievedStudent.setFirstName(NAME_2);
		retrievedStudent.setDateOfBirth(BIRTHDAY_2);
		
		studentManager.updateStudent(retrievedStudent);
		Student updatedStudent = studentManager.getAllStudents().get(studentListCount - 1);
		
//		assertEquals(updatedStudent.getId(), ID2);
		assertEquals(updatedStudent.getFirstName(), NAME_2);
		assertEquals(updatedStudent.getDateOfBirth(), BIRTHDAY_2);
		
		studentManager.removeStudent(updatedStudent);
	}

	@Test
	public void removeStudentTest() {
		// clear out studens table
		studentManager.removeAllStudents();
		
		// add student
		Student student = new Student(ID1, NAME1, BIRTHDAY_1);
		studentManager.addStudent(student);

		int studentCounter = studentManager.getAllStudents().size(); // should be 1
		
		studentManager.removeStudent(student);
		int studentCounterAfterRemoval = studentManager.getAllStudents().size();
		
		assertEquals(studentCounterAfterRemoval, studentCounter - 1);
	}

	
	@Test
	public void addGradeTest() {
		
		studentManager.removeAllGrades();
		Grade grade = new Grade();
//		grade.setId(ID1);
		grade.setValue(GRADE1);
		grade.setApproved(APPROVED1);

		studentManager.addGrade(grade);
		int retrievedGrade = studentManager.getAllGrades().size();
		assertEquals(1, retrievedGrade);

	}
	
	



	




}