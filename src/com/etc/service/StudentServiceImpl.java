package com.etc.service;

import java.util.List;

import com.etc.dao.StudentDao;
import com.etc.dao.impl.StudentDaoImpl;
import com.etc.entity.Student;

public class StudentServiceImpl implements StudentService{
	
	private StudentDao dao = new StudentDaoImpl();

	@Override
	public String addStudent(Student student) {
		if(student.getName() == null){
			return "学生名称不能为空";
		}
		if(dao.addStudent(student) == 1){
			return "添加成功！";
		}
		return "添加失败！";
	}

	@Override
	public String delStudent(int studentId) {
		if(studentId == 0){
			return "学生ID不能为空";
		}
		if(dao.delStudent(studentId) == 1){
			return "学生删除成功";
		}
		return "学生删除失败";
	}

	@Override
	public String updStudent(Student student) {
		if(student.getName() == null){
			return "学生名称不能为空";
		}
		if(dao.updStudent(student) == 1){
			return "修改成功！";
		}
		return "修改失败！";
	}

	@Override
	public Student getStudentById(int studentId) {
		return dao.getStudentById(studentId);
	}

	@Override
	public List<Student> findAllStudent() {
		return dao.findAllStudent();
	}

}
