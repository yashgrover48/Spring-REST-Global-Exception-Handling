package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Student;
import com.demo.rest.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private List<Student> theStudents;
	
	// define @PostConstruct to load the student data only once
	@PostConstruct
	public void loadData(){
		theStudents=new ArrayList<>();
		theStudents.add(new Student("Yash","Grover"));
		theStudents.add(new Student("Rahul","Kumar"));
		theStudents.add(new Student("Nimish","Tuteja"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	// define an end point for /students/{studentId}
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId){
		
		// just index it into the list.. keep it simple for now
		
		// check the studentId against list size
		if((studentId>=theStudents.size())||(studentId<0)){
			throw new StudentNotFoundException("Student id not found - "+studentId);
		}
		return theStudents.get(studentId);
	}
}