package com.example.student.reposiratory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.student.entity.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{

}
