package com.etc.test;

import org.junit.Test;

import com.etc.bean.Student;
import com.etc.dao.StudentDao;
import com.etc.dao.impl.StudentDaoImpl;

public class StudentTest {
	
	@Test
	public void addStudentTest(){
		Student student = new Student();
		student.setName("jack");
		student.setAge(23);
		student.setScore(90D);
		
		StudentDao dao = new StudentDaoImpl();
		
		if(dao.addStudent(student) == 1){
			System.out.println("添加学生成功！");
		}else{
			System.out.println("添加学生失败！");
		}
	}
	
	@Test
	public void getStudentById(){
		StudentDao dao = new StudentDaoImpl();
		Student student = dao.getStudentById(1);
		System.out.println("id->" + student.getId());
		System.out.println("name->" + student.getName());
	}

}