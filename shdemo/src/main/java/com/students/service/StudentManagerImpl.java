package com.students.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.students.domain.Grade;
import com.students.domain.Student;

@Component
@Transactional
public class StudentManagerImpl implements StudentManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ----------------------------------------------------------------------------------------------
	@Override
	public void addStudent(Student student) {
		student.setId(null);
		sessionFactory.getCurrentSession().persist(student);
	}

	@Override
	public void updateStudent(Student student) {
		sessionFactory.getCurrentSession().saveOrUpdate(student);
	}

	@Override
	public void removeStudent(Student student) {
		sessionFactory.getCurrentSession().delete(student);

	}
	
	@Override
	public void removeAllStudents() {
		sessionFactory.getCurrentSession().getNamedQuery("student.removeAll").executeUpdate();
	}

	@Override
	public Student findStudentById(Long id) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() {
		return sessionFactory.getCurrentSession().getNamedQuery("student.all").list();
	}

	@Override
	public void addGrade(Grade grade) {
		grade.setId(null);
		sessionFactory.getCurrentSession().save(grade);
	}

	@Override
	public void updateGrade(Grade grade) {
		sessionFactory.getCurrentSession().saveOrUpdate(grade);

	}

	@Override
	public void removeGrade(Grade grade) {
		sessionFactory.getCurrentSession().delete(grade);

	}

	@Override
	public Grade findGradeById(Long id) {
		return (Grade) sessionFactory.getCurrentSession().get(Grade.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getAllGrades() {
		return sessionFactory.getCurrentSession().getNamedQuery("grade.all").list();
	}

	@Override
	public Grade findGradeByValue(float value) {
		return (Grade) sessionFactory.getCurrentSession().get(Grade.class, value);
	}

	@Override
	public void removeAllGrades() {
		sessionFactory.getCurrentSession().getNamedQuery("grade.removeAll").executeUpdate();	
	}

	

	



}
