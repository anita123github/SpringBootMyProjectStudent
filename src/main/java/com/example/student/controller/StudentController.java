package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;


@RestController
@RequestMapping("/Stud")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/save")
	public Student addStudent (@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	

	@GetMapping("/allEmp")
	public List<Student> getAll() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getEmpById(@PathVariable int id) {
		Student student = studentService.getStudentById(id);
		return student;
	}

	@GetMapping("/request")
	public Student getById(@RequestParam("studcode") int id) {
		Student student =studentService.getStudentById(id);
		return student;
	}

	@PutMapping("/update")
	public String updateEmp(@RequestBody Student student) {
		int id = studentService.updateStudent(student.getId(),student);
		if (id ==0) {
			return "employee not found";
		}
		return String.format("Student is updated for the id ::%d", id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmp( @PathVariable int id) {
		String response = studentService.deleteStudentById(id);
		return response;

	}
}
