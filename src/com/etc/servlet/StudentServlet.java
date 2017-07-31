package com.etc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.entity.Student;
import com.etc.service.StudentService;
import com.etc.service.StudentServiceImpl;

public class StudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		StudentService service = new StudentServiceImpl();
		
		HttpSession session = request.getSession();
		
		String operation = request.getParameter("op");
		
		if("add".equalsIgnoreCase(operation)){
			Student student = new Student();
			student.setAge(Integer.valueOf(request.getParameter("age")));
			student.setName(request.getParameter("name"));
			student.setScore(Double.valueOf(request.getParameter("score")));
			session.setAttribute("msg",service.addStudent(student));
			response.sendRedirect("list");
		}else if("upd".equalsIgnoreCase(operation)){
			Student student = new Student();
			student.setAge(Integer.valueOf(request.getParameter("age")));
			student.setName(request.getParameter("name"));
			student.setId(Integer.valueOf(request.getParameter("id")));
			student.setScore(Double.valueOf(request.getParameter("score")));
			session.setAttribute("msg",service.updStudent(student));
			response.sendRedirect("list");
		}else if("del".equals(operation)){
			int studentId = Integer.valueOf(request.getParameter("id"));
			session.setAttribute("msg", service.delStudent(studentId));
			response.sendRedirect("list");
		}else if("getOne".equals(operation)){
			int studentId = Integer.valueOf(request.getParameter("id"));
			Student student = service.getStudentById(studentId);
			request.setAttribute("student",student);
			request.getRequestDispatcher("/upd.jsp").forward(request, response);
		}else{
			request.setAttribute("students", service.findAllStudent());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
	}

}
