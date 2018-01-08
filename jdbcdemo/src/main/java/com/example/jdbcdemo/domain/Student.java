package com.example.jdbcdemo.domain;

import java.sql.Date;

public class Student {
	
	private int studentNo;
	private String firstname;
	private String lastname;
	private Date dob;
	
	
	
	public Student(){
	}

	public Student(String firstname, String lastname, Date dob) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
	}
	
	public Student(int studentNo, String firstname, String lastname, Date dob) {
		super();
		this.studentNo = studentNo;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
	}


	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	

	
}
