package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	private StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
	
	public Student getStudetnId(Long id ) {
		return studentRepository.findById(id).orElse(null);
	}
	
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
}
