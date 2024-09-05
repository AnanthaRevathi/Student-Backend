package com.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.dao.StudentRepository;
import com.student.entity.Student;
import com.student.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3002", allowedHeaders = "*", exposedHeaders = "X-Get-Header")
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;  // Corrected typo
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/save-data")
	public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
	    List<Student> savedStudents = studentService.addStudents(students);  // Update service method to handle list
	    return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
	}
	
	@GetMapping("/get-data")
	@ResponseBody
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = studentRepository.findAll();
		return ResponseEntity.ok(students);
	}
	@CrossOrigin(origins = "http://localhost:3001") 
    @PostMapping("/update-data")
    public ResponseEntity<List<Student>> updateStudents(@RequestBody List<Student> students) {
        List<Student> updatedStudents = new ArrayList<>();
        for (Student student : students) {
            Student updatedStudent = studentService.updateStudent(student);
            updatedStudents.add(updatedStudent);
        }
        return new ResponseEntity<>(updatedStudents, HttpStatus.OK);
    }
	@DeleteMapping("/delete-data/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable String id) {  // Changed int to String
		studentService.deleteStudent(Long.parseLong(id));  // Convert to Long if necessary
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
