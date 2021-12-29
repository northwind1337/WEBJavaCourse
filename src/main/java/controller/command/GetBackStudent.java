package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import entities.Student;
import service.ServiceException;
import service.StudentService;
import service.implementation.StudentServiceImpl;

public class GetBackStudent implements SendRequest {

	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if (request.getParameter("unblock_student") != null) {
			int studentId = Integer.parseInt(request.getParameter("unblock_student"));
			try {
				studentService.setUnblock(studentId);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		try {
			List<Student> students = studentService.getBlockedStudents();
			request.setAttribute("students", students);
			return "/unblock.jsp";
		} catch(ServiceException e) {
			e.printStackTrace();
		}
		return "/admin.jsp";
	}

}
