package com.btwice.sbrdemo.service;

import com.btwice.sbrdemo.model.Student;

import java.util.List;

public interface IStudentService {
    Student addStudent(Student student);
    List<Student> getStudents();
    Student upDateStudent(Student student, Long id);
    Student getStudentById(Long id);
    void deleteStudent(Long id);

}
