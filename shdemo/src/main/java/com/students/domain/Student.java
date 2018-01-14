package com.students.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "student.all", query = "Select s from Student s"),
	@NamedQuery(name = "student.byId", query = "Select s from Student s where s.id = :id"),
	@NamedQuery(name = "student.removeAll", query = "Delete from Student s")	
	})

public class Student {

	private Long id;

	private String firstName = "unknown";
	private Date dateOfBirth = new Date(1, 1, 1970);

	private List<Grade> grades = new ArrayList<Grade>();

	public Student() {
		super();
	}

	public Student(Long id, String firstName, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Student(Long id, String firstName, Date dateOfBirth, List<Grade> grades) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.grades = grades;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	

}
