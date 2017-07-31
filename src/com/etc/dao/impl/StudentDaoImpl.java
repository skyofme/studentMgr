package com.etc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.etc.dao.StudentDao;
import com.etc.entity.Student;
import com.etc.util.DBUtils;

public class StudentDaoImpl implements StudentDao {

	DBUtils utils = new DBUtils();
	
	@Override
	public int addStudent(Student student) {
		String sql = "insert into student(name,age,score) values(?,?,?)";
		int result = utils.excuteUpdate(sql, student.getName(),student.getAge(),student.getScore());
		return result;
	}

	@Override
	public int delStudent(int studentId) {
		String sql = "delete from student where id = ?";
		int result = utils.excuteUpdate(sql, studentId);
		return result;
	}

	@Override
	public int updStudent(Student student) {
		String sql = "update student set name = ?,age = ?,score = ? where id = ?";
		int result = utils.excuteUpdate(sql, student.getName(),student.getAge(),student.getScore(),student.getId());
		return result;
	}

	@Override
	public Student getStudentById(int studentId) {
		String sql = "select * from student where id = ?";
		List<Object> students = utils.excuteQuery(sql, studentId);
		for(Object x : students){
			Map<String, Object> map = (Map<String,Object>)x;
			Student student = new Student();
			student.setAge(Integer.valueOf(map.get("age").toString()));
			student.setId(Integer.valueOf(map.get("id").toString()));
			student.setName(map.get("name").toString());
			student.setScore(Double.valueOf(map.get("score").toString()));
			return student;
		}
		return null;
	}

	@Override
	public List<Student> findAllStudent() {
		String sql = "select * from student";
		List<Student> studentList = new ArrayList<>();
		List<Object> students = utils.excuteQuery(sql);
		for(Object x : students){
			Map<String, Object> map = (Map<String,Object>)x;
			Student student = new Student();
			student.setAge(Integer.valueOf(map.get("age").toString()));
			student.setId(Integer.valueOf(map.get("id").toString()));
			student.setName(map.get("name").toString());
			student.setScore(Double.valueOf(map.get("score").toString()));
			studentList.add(student);
		}
		return studentList;
	}

}
