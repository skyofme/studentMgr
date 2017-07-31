package com.etc.service;

import java.util.List;

import com.etc.entity.Student;

public interface StudentService {

	public String addStudent(Student student);
	
	public String delStudent(int studentId);
	
	public String updStudent(Student student);
	
	public Student getStudentById(int studentId);
	
	public List<Student> findAllStudent();
}
