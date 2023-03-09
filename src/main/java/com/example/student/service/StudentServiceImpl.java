package com.example.student.service;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.student.controller.StudentController;
//import com.example.student.controller.StudentController;
import com.example.student.entity.Student;
import com.example.student.reposiratory.StudentRepository;




@Service
public class StudentServiceImpl implements StudentService {
	
	private static final Logger Log=LoggerFactory.getLogger(StudentController.class);
		
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudent(Student student) {
		Student savedStudent= studentRepository.save(student);
		return savedStudent;
	}

	
	@Override
	public List<Student> getAllStudents() {
		Iterable <Student> iterable =studentRepository.findAll();
		return  (List<Student>) iterable;
	}


	@Override
	public Student getStudentById(int id) {
		Log.info("Start:StudentServiceImpl--->getStudById id is{}",id); 
		Optional<Student>optional =studentRepository.findById(id);
		if(optional.isPresent()) {
		Log.info("End:StudentServiceImpl--->getStudById id is{}",id);	
		return optional.get();
		}
		return null;
}
		//return studentRepository.findById(id).orElseThrow(()->new NoSuchElementException("Element with given id not found"));



	@Override
	public int updateStudent(int id, Student student) {

		Optional< Student > response = studentRepository.findById(id);
		if (response.isPresent()) {
			 Student  result = studentRepository.save(student);
			return result.getId();
		}

		return 0;
	}


	@Override
	public String deleteStudentById(int id) {
		studentRepository.deleteById(id);
		return "Student deleted";
	}
	
	

	@Override
	public List<Student> getAllStudentByPage(int page, int size) {
		PageRequest request=PageRequest.of(page, size);
		Page<Student>pageResponse=studentRepository .findAll(request);
		List<Student>listStudents =pageResponse.getContent();
		return listStudents;
	}


	@Override
	public List<Student> getAllStudentBySorting() {
		List<Student>findAll=studentRepository.findAll(Sort.by("name").ascending());
		return findAll;
	}


	/*@Override
	public List<Student> getAllStudentsSorting() {
		List<Student>findAll=studentRepository.findAll(Sort.by("name").ascending());
		return findAll;
	}
*/
}
