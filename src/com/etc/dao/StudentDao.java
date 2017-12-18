package com.etc.dao;

import java.util.List;

import com.etc.bean.Student;

public interface StudentDao {

	public int addStudent(Student student);

	public int delStudent(int studentId);

	public int updStudent(Student student);

	public Student getStudentById(int studentId);

	public List<Student> findAllStudent();
}
