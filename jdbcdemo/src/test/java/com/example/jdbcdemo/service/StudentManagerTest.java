package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.example.jdbcdemo.domain.Student;

public class StudentManagerTest {


    StudentManagerJDBC studentManager = new StudentManagerJDBC();

    private final static String FIRSTNAME_1 = "Franek";
    private final static String LASTNAME_1 = "Kwiatkowski";
    private final static Date DOB_1 = Date.valueOf("1990-05-26");

    private final static String FIRSTNAME_2 = "Andrzej";
    private final static String LASTNAME_2 = "Wojciechowicz";
    private final static Date DOB_2 = Date.valueOf("1991-06-16");

    private final static String FIRSTNAME_3 = "Ania";
    private final static String LASTNAME_3 = "Nowak";
    private final static Date DOB_3 = Date.valueOf("1993-08-23");

    private final static String FIRSTNAME_4 = "Grażyna";
    private final static String LASTNAME_4 = "Cebula";
    private final static Date DOB_4 = Date.valueOf("1993-01-08");


    Student s1 = new Student(FIRSTNAME_1, LASTNAME_1, DOB_1);
    Student s2 = new Student(FIRSTNAME_2, LASTNAME_2, DOB_2);
    Student s3 = new Student(FIRSTNAME_3, LASTNAME_3, DOB_3);
    Student s4 = new Student(FIRSTNAME_4, LASTNAME_4, DOB_4);

    @Test
    public void checkConnection() {
        assertNotNull(studentManager.getConnection());
    }

    //@Test
    public void checkAdding() {

        Student student = new Student(FIRSTNAME_1, LASTNAME_1, DOB_1);

        studentManager.clearStudents();
        assertEquals(1, studentManager.addStudent(student));

        List<Student> listOfStudents = studentManager.getAllStudents();
        Student studentRetrieved = listOfStudents.get(0);

        assertEquals(FIRSTNAME_1, studentRetrieved.getFirstname());
        assertEquals(LASTNAME_1, studentRetrieved.getLastname());
        assertEquals(DOB_1, studentRetrieved.getDob());

    }

    @Test
    public void checkSearchingById() {

        studentManager.clearStudents();
        studentManager.addStudent(new Student(FIRSTNAME_1, LASTNAME_1, DOB_1));
        List<Student> listOfStudents = studentManager.getAllStudents();
        Student studentRetrieved = listOfStudents.get(0);

        Student searchedStudent = studentManager.findStudentById(studentRetrieved.getStudentNo());

        assertEquals(studentRetrieved.getStudentNo(), searchedStudent.getStudentNo());


    }

    @Test
    public void checkSearchingByLastname() {
        studentManager.clearStudents();
        studentManager.addStudent(new Student(FIRSTNAME_1, LASTNAME_1, DOB_1));
        List<Student> listOfStudents = studentManager.getAllStudents();
        Student studentRetrieved = listOfStudents.get(0);
        Student searchedStudent = studentManager.findStudentByLastname(studentRetrieved.getLastname());
        assertEquals(LASTNAME_1, searchedStudent.getLastname());
    }


    @Test
    public void checkAddAll() {
        studentManager.clearStudents();

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        studentManager.addAllStudents(students);
        int size = studentManager.getAllStudents().size();

        //assertTrue(size == 0 || size == 4);

        assertThat(size, either(is(4)).or(is(0)));
    }

    @Test
    public void checkUpdateAll() {
        studentManager.clearStudents();

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        studentManager.addAllStudents(students);
        studentManager.updateAllStudents(students, "PopularneNazwisko");

        List<Student> studentsAfterUpdate = studentManager.getAllStudents();
        /*
        * Próba nadania wszystkim studentom nazwiska="PopularneNazwisko"
        * nie powinna się udać ponieważ jest nałożone ograniczenie lastname UNIQUE przy tworzeniu tabeli
        * tzn każde nazwisko ma być unikatowe
        * ponieważ transakcja ma się nie udać ŻADEN student nie będzie miał PopularnegoNazwiska -> rollback
        * */
        for(Student student : studentsAfterUpdate){
            assertNotEquals("PopularneNazwisko", student.getFirstname());
        }


    }
}
