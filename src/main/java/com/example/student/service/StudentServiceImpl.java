package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.entity.Student;
import com.example.student.reposiratory.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
		
		
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
		Optional<Student> response =studentRepository.findById(id);
		return response.isPresent() ? response.get() : null;
	}


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

}
