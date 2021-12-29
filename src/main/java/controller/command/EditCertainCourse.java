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

public class EditCertainCourse implements SendRequest {
	private CourseService courseService = new CourseServiceImpl();
	private TeachersService instructorService = new TeachersServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long choose;
		try {
			choose = Long.parseLong(request.getParameter("choose"));
		} catch (NumberFormatException e) {
			choose = Long.parseLong(request.getParameter("edited"));
		}
		if(request.getParameter("edited") != null) {
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
				createdCourse.setId(choose);
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
					return "Error DB problem";
				}
				request.setAttribute("message", "Course changed succesfully");
			};
		}
		
		try {
			Course course = courseService.findById(choose);
			request.setAttribute("course", course);
			request.setAttribute("instructor", course.getInstructor().getFullName());
			request.setAttribute("instructors", instructorService.findAll());
			request.setAttribute("instructorId", course.getInstructor().getId());
		} catch (ServiceException e) {
			e.printStackTrace();
			return "/all_courses.jsp";
		}
		return "/edit_course.jsp";
	
	}

}
