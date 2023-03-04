package com.example.student.reposiratory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
	
	public List<Student>findByName(String StudentName);
	
	public Student findByNameAndAddress(String Name,String address);
	

}
