package com.example.student.reposiratory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Student;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
@Repository
//public interface StudentRepository extends CrudRepository<Student, Integer>{

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	//Find by method	
	public List<Student>findByName(String StudentName);
	
	public Student findByNameAndAddress(String Name,String address);

	//ResponseEntity and CustumEntity
	
	@Query(value = "Select * from Student where stud_Name =:name and stud_Address =:address,nativeQuery=true")
	public Student getdata(@Param( value = "name")String name, @Param( value = "address")String address);
	
	
	}


