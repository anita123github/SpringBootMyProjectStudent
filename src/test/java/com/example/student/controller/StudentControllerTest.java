package com.example.student.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.example.student.entity.Student;
import com.example.student.reposiratory.StudentRepository;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/application-junit.properties")
public class StudentControllerTest {
	@LocalServerPort
	private int port;
	
	private RequestSpecification requestSpecification;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostConstruct
	public void initRequistSpecificatoin() {
		final RequestSpecBuilder tempSpec=new RequestSpecBuilder();
		requestSpecification =tempSpec.setBaseUri("http://localhost:"+port+"/stud").build();
	}
    @Test
    public void testAddEmployee() {
    	//Given
    	Student student =new Student();
    	student.setName("vishal");
		student.setAddress("mumbai");
	
		//When
		ValidatableResponse result=RestAssured.given(requestSpecification)
				.contentType("application/json").accept("application/json")
				.body(student).post("/save").then();
			
		//then
		Student studresult=result.extract().as(Student .class);
		assertNotNull(result);
		assertEquals("vishal",studresult.getName());
	
    	}
    @Test
    public void testGetStudById() {
	//Given
    	Student student =new Student();
		student.setName("Manisha");
		student.setAddress("mumbai");
		studentRepository.save(student);
		assertEquals(1, studentRepository.count());
		
		//When
		ValidatableResponse result=RestAssured.given(requestSpecification)
				.get("/1").then();
		
		//Then
		Student as=result .extract().as(Student.class);
		assertNotNull(as);
    }
		public void testGetById() {
			//Given
	    	Student student =new Student();
			student.setName("niku");
			student.setAddress("nanded");
			studentRepository.save(student);
			assertEquals(1, studentRepository.count());
			
			//When
			ValidatableResponse result=RestAssured.given(requestSpecification)
					.queryParam("studcode",1).get("/request").then();
			

			//Then
			Student as=result .extract().as(Student.class);
			assertNotNull(as);
		}
    }

