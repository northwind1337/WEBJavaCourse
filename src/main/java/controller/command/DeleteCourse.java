package controller.command;

import javax.servlet.http.HttpServletRequest;

import service.CourseService;
import service.InstructorService;
import service.ServiceException;
import service.implementation.CourseServiceImpl;
import service.implementation.InstructorServiceImpl;

public class DeleteCourse implements Command {
	private CourseService courseService = new CourseServiceImpl();
	private InstructorService instructorService = new InstructorServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		if (request.getParameter("deleted") != null) {
			try {
				Long choose = Long.parseLong((String)request.getParameter("deleted"));
				courseService.delete(choose);
			} catch (ServiceException e) {
				e.printStackTrace();
				return "Unable to delete course, problem with db";
			}
		}
		
		try {
			request.setAttribute("courses", courseService.findAll());
			request.setAttribute("instructors", instructorService.findAll());
		} catch (ServiceException e) {
			e.printStackTrace();
			return "/admin.jsp";
		}
		return "/delete_course.jsp";
	}

}
