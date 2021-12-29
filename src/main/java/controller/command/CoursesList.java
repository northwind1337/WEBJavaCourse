package controller.command;

import javax.servlet.http.HttpServletRequest;

import service.CourseService;
import service.TeachersService;
import service.ServiceException;
import service.implementation.CourseServiceImpl;
import service.implementation.TeachersServiceImpl;

public class CoursesList implements SendRequest {
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
