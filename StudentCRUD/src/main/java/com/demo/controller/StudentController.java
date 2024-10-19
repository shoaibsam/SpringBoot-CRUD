package com.demo.controller;

import com.demo.model.Student;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping("/login")
    public String login() {
        return "login";  // Avoid unnecessary redirects
    }

    @GetMapping("/student-list")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "student-list";
    }

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add-student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/student-list";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "update-student";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return "redirect:/student-list";
    }
}
