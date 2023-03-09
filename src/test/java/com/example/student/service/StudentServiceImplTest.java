package com.example.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.student.entity.Student;
import com.example.student.reposiratory.StudentRepository;

@SpringBootTest
@TestPropertySource("classpath:/application-junit.properties")
public class StudentServiceImplTest {
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@BeforeEach
	public void setup() {
		studentRepository.deleteAll();
	}
	
	@Test
	public void testSaveStudent() {
		//Given
		Student student =new Student();
		student.setName("vinod");
		student.setAddress("mumbai");
		
		//When
		Student result=studentServiceImpl.saveStudent(student);
		
		//Then
		assertNotNull(result);
		assertEquals("vinod",result.getName());
		
	}
	@Test
	public void testGetAllStudent() {
		//Given
		Student student1 =new Student();
		student1.setName("vinod");
		student1.setAddress("mumbai");
		
		Student student2 =new Student();
		student2.setName("vinod");
		student2.setAddress("mumbai");
		
		List<Student>list=new ArrayList<>();
		list.add(student1);
		list.add(student2);
		
		List<Student>savedList=studentRepository.saveAll(list);
		assertEquals(2,savedList.size());
		
		//When
		List<Student>result=studentServiceImpl.getAllStudents();
		
		//Then
		assertNotNull(result);
		assertEquals(2,result.size());
			
		}
		}


