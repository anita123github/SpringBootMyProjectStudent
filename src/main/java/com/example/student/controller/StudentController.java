package com.example.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.student.exception.NoSuchElementFoundException;
import com.example.student.reposiratory.StudentRepository;
import com.example.student.service.StudentService;

@RestController
@RequestMapping("/stud")
public class StudentController {

	private static final Logger Log=LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping(path="/save",consumes= {MediaType.APPLICATION_JSON_VALUE},produces ={MediaType.APPLICATION_JSON_VALUE} )
	public Student addStudent (@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	

	@GetMapping("/allStud")
	public List<Student> getAll() {
		//return studentService.getAllStudents();
		return studentService.getAllStudentBySorting();
	}
	
		
	@GetMapping("/allStud/{Page}")
	public ResponseEntity<List<Student>> getStudentsByPage(@PathVariable int page) {
	   List<Student>bypage=studentService.getAllStudentByPage(page, 2);
		return new ResponseEntity<List<Student>>(bypage,HttpStatus.OK);
	}
	
	@GetMapping (path="/{name}")
	public List<Student>test(@PathVariable String name){
		List<Student>findByName=studentRepository .findByName(name);
		return findByName;
		
	}
	/*
	@GetMapping (path="/{name}/{address}")
	public Student test(@PathVariable String name,@PathVariable String address){
		Student findByName=studentRepository .findByNameAndAddress(name,address);
		return findByName;
	}*/

	@GetMapping (path="/{name}/{address}")
	public ResponseEntity<Student> test(@PathVariable String name,@PathVariable String address){
		Student findByName=studentRepository .getdata(name,address);
		return new ResponseEntity<Student>(findByName,HttpStatus.OK);
	}

	@GetMapping(path="/id/{id}")
	public ResponseEntity<Student>getStudById(@PathVariable int id){
		Log.info("Start:StudentController--->getStudById id is{}",id);
		Student student = studentService.getStudentById(id);
		if(student!=null) {
			Log.info("End:StudentController--->getStudById id is{}",id);	
			return new ResponseEntity<Student>(student,HttpStatus.OK);
			}
			else {
				Log.error("NoSuchElementFoundException:Student with id is not found");
				throw new NoSuchElementFoundException("Student with id is not found");
			}
		
		
		}
		
	

	@GetMapping("/request")
	public Student getById(@RequestParam("studcode") int id) {
		Student student =studentService.getStudentById(id);
		return student;
	}

	@PutMapping("/update")
	public String updateStud(@RequestBody Student student) {
		int id = studentService.updateStudent(student.getId(),student);
		if (id ==0) {
			return "student not found";
		}
		return String.format("Student is updated for the id ::%d", id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteStud( @PathVariable int id) {
		String response = studentService.deleteStudentById(id);
		return response;

	}
}
