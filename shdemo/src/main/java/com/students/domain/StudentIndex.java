package com.students.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = "studentindex.all", query = "SELECT s FROM StudentIndex s"),
		@NamedQuery(name = "footballTeam.byName", query = "SELECT f FROM FootballTeam f WHERE f.name = :name") })


public class StudentIndex {
	private Long id;
	private Student student;
	private Grade grade;
	private Date dateOfGrade;

	public StudentIndex() {
		super();
	}


	public StudentIndex(Long id, Student student, Grade grade, Date dateOfGrade) {
		super();
		this.id = id;
		this.student = student;
		this.grade = grade;
		this.dateOfGrade = dateOfGrade;
	}





	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Date getDateOfGrade() {
		return dateOfGrade;
	}


	public void setDateOfGrade(Date dateOfGrade) {
		this.dateOfGrade = dateOfGrade;
	}



}