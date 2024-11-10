package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Student;
import com.example.service.StudentService;


@Controller
public class StudentController {
	public final StudentService studentservice;
	
	public StudentController(StudentService studentservice) {
		this.studentservice = studentservice;
	}
	
	@GetMapping("/index")
	public String viewHomePage(Model model) {
	    model.addAttribute("students", studentservice.getAllStudents());
	    return "index";  // This must match 'index.html'
	}

	@GetMapping("/addStudent")
	public String addStudentForm(Model model) {
	    model.addAttribute("student", new Student());
	    return "add_student";  // This must match 'add_student.html'
	}

	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
	    studentservice.saveStudent(student);
	    return "redirect:/index";  // Redirect to Home Page ("/")
	}

	
	@GetMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable Long id, Model model) {
		Student student = studentservice.getStudetnId(id);
		model.addAttribute("student", student);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentservice.deleteStudent(id);
		return "redirect:/index";
	}
}
