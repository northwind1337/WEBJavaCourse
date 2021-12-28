package controller.command;

import javax.servlet.http.HttpServletRequest;

import domain.Course;
import service.CourseService;
import service.ServiceException;
import service.implementation.CourseServiceImpl;
import util.SessionManager;

public class CoursePage implements Command{

	private CourseService courseService = new CourseServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long courseChoose = Long.parseLong(request.getParameter("choose"));
		Course course;
		try {
			course = courseService.findById(courseChoose);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "/other_courses.jsp";
		}
		
		request.setAttribute("course", course);
				
		return "/course.jsp";
	}
}
