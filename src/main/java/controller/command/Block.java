package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.Student;
import service.ServiceException;
import service.StudentService;
import service.implementation.StudentServiceImpl;

public class Block implements Command {

	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if (request.getParameter("block_student") != null) {
			int studentId = Integer.parseInt(request.getParameter("block_student"));
			try {
				studentService.setBlock(studentId);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		try {
			List<Student> students = studentService.getUnblockedStudents();
			request.setAttribute("students", students);
			return "/block.jsp";
		} catch(ServiceException e) {
			e.printStackTrace();
		}
		return "/admin.jsp";
	}

}
