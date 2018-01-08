package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Student;
import java.util.List;

public interface StudentManager {

    /* batch insert - transactional */
    public void addAllStudents(List<Student> students);

    public void updateAllStudents(List<Student> students, String lastname);
}
