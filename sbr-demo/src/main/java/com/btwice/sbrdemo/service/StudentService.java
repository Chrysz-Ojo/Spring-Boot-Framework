package com.btwice.sbrdemo.service;

import com.btwice.exception.StudentAlreadyExistsException;
import com.btwice.exception.StudentNotFoundException;
import com.btwice.sbrdemo.model.Student;
import com.btwice.sbrdemo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())) {
            throw new StudentAlreadyExistsException( student.getEmail()+ "already exists!");
        }
        return studentRepository.save(student);
    }


    @Override
    public Student upDateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(student1 ->{
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setDepartment(student.getDepartment());
            return studentRepository.save(student1);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException("Sorry, no student found with the Id :" + id ));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, student not found");
        }

    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

}
