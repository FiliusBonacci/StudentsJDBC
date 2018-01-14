package com.students.service;

import java.util.List;
import com.students.domain.Student;
import com.students.domain.Grade;

public interface StudentManager {

	// student
	void addStudent(Student student);
	void updateStudent(Student student);
	void removeStudent(Student student);
	void removeAllStudents();
	
	Student findStudentById(Long id);
	List<Student> getAllStudents();

	// grade
	void addGrade(Grade grade);
	void updateGrade(Grade grade);
	void removeGrade(Grade grade);
	void removeAllGrades();

	Grade findGradeById(Long id);
	List<Grade> getAllGrades();
	Grade findGradeByValue(float value);
	
	

}
