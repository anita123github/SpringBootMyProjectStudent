package com.example.student.service;

import java.util.List;

import com.example.student.entity.Student;

public interface StudentService {
	
	Student saveStudent(Student student);
	
	List<Student > getAllStudents();
	
	Student getStudentById(int id);

	int updateStudent(int id, Student student);

    String deleteStudentById(int id);
    
    List<Student>getAllStudentByPage(int page,int size);
    
    List<Student> getAllStudentBySorting();

	//List<Student> getAllStudentsSorting();
}
