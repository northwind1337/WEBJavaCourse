package controller.command;

import javax.servlet.http.HttpServletRequest;

import entities.Assignment;
import entities.Course;
import entities.Student;
import service.AssignmentService;
import service.CourseService;
import service.ServiceException;
import service.implementation.AssignmentServiceImpl;
import service.implementation.CourseServiceImpl;
import util.SessionUsing;

public class SuccessRegistration implements SendRequest {

	private AssignmentService assignmentService = new AssignmentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("course"));
		Student student = (Student)request.getSession().getAttribute("currentEntity");
		if (assignmentService.checkAssign(student.getId(), id) == 1) {
			return "/course.jsp";
		};
		CourseService courseService = new CourseServiceImpl();
		Course course;
		try {
			course = courseService.findById(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("choose", id);
			
			return "/course.jsp";
		}
		
		Assignment assignment = new Assignment();
		assignment.setCourse(course);
		assignment.setStudent(student);
		
		try {
			assignment.setId(assignmentService.save(assignment));
		} catch (ServiceException e) {
			e.printStackTrace();
			return "/course.jsp";
		}
		
		return "/success_registration.jsp";
	}
	
}
