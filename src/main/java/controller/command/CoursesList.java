package controller.command;

import javax.servlet.http.HttpServletRequest;

import service.CourseService;
import service.InstructorService;
import service.ServiceException;
import service.implementation.CourseServiceImpl;
import service.implementation.InstructorServiceImpl;

public class CoursesList implements Command {
	private CourseService courseService = new CourseServiceImpl();
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setAttribute("courses", courseService.findAll());
		} catch (ServiceException e) {
			e.printStackTrace();
			return "admin.jsp";
		}
		return "/all_courses.jsp";
	}

}
