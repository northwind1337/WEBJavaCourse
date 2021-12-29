package controller.command;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import entities.Course;
import entities.Teacher;
import service.CourseService;
import service.TeachersService;
import service.ServiceException;
import service.implementation.CourseServiceImpl;
import service.implementation.TeachersServiceImpl;
import util.Validation;

public class CreateNewCourse implements SendRequest {
	private TeachersService instructorService = new TeachersServiceImpl();
	private CourseService courseService = new CourseServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if(request.getParameter("created") != null) {
			String name = request.getParameter("name");
			String hours = request.getParameter("hours");
			String topic = request.getParameter("topic");
			String instructorId = request.getParameter("instructor_id");
			String begin = request.getParameter("begin");
			String finish = request.getParameter("finish");
			String message = Validation.checkCourse(name, hours, topic, instructorId, begin, finish);
			if (message != null) {
				request.setAttribute("message", message);
			} else {
				Course createdCourse = new Course();
				createdCourse.setName(name);
				createdCourse.setHours(Integer.parseInt(hours));
				createdCourse.setTopic(topic);
				Teacher instructor = new Teacher();
				instructor.setId(Long.parseLong(instructorId));
				createdCourse.setInstructor(instructor);
				createdCourse.setBeginDate(LocalDate.parse(begin));
				createdCourse.setFinishDate(LocalDate.parse(finish));
				try {
					courseService.save(createdCourse);
				} catch (ServiceException e) {
					e.printStackTrace();
					return "Unable to create course, DB problem";
				}
				request.setAttribute("message", "Course succesfullz created");
			};
		}
		
		try {
			request.setAttribute("instructors", instructorService.findAll());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "/create_course.jsp";
	}

}
