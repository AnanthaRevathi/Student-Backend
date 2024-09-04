package com.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dao.StudentRepository;
import com.student.entity.Student;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Add a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Add multiple students
    public List<Student> addStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    // Update an existing student's details
 // StudentService.java
    public Student updateStudent(Student student) {
        Optional<Student> optional = studentRepository.findById(student.getId());
        
        if (optional.isPresent()) {
            Student existingStudent = optional.get();
            existingStudent.setName(student.getName());
            existingStudent.setContactDetails(student.getContactDetails());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setPincode(student.getPincode());
            
            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with ID: " + student.getId());
        }
    }


    // Delete a student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
