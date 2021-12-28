package controller.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import domain.Result;

import domain.Assignment;
import domain.Course;
import domain.Student;
import service.AssignmentService;
import service.ResultService;
import service.ServiceException;
import service.implementation.AssignmentServiceImpl;
import service.implementation.ResultServiceImpl;


public class MyCourses implements Command {

	private AssignmentService assignmentService = new AssignmentServiceImpl();
	private ResultService resultService = new ResultServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		List<Assignment> assignments;
		
		try {
			assignments = getAllCoursesByStudent(request);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "/my_courses.jsp";
		}
		
		List<Course> courses = new ArrayList<>();
		if (request.getParameter("content") == null) {
			return "/my_courses.jsp";
		}

		int content = Integer.parseInt(request.getParameter("content"));
		if (content == 1) {
			assignments = assignments.stream().filter(x -> (x.getCourse().getBeginDate().isAfter(LocalDate.now()))).collect(Collectors.toList());
			courses = assignments.stream().map(x -> x.getCourse()).collect(Collectors.toList());
		} else if (content == 2) {
			assignments = assignments.stream().filter(x -> (x.getCourse().getBeginDate().isBefore(LocalDate.now()) && x.getCourse().getFinishDate().isAfter(LocalDate.now()))).collect(Collectors.toList());
			courses = assignments.stream().map(x -> x.getCourse()).collect(Collectors.toList());
		} else if (content == 3) {
			assignments = assignments.stream().filter(x -> (x.getCourse().getFinishDate().isBefore(LocalDate.now()))).collect(Collectors.toList());
			courses = assignments.stream().map(x -> x.getCourse()).collect(Collectors.toList());
		}
		
		List<Result> results = new ArrayList<>();
		
		for (Assignment assignment: assignments) {
			try {
				results.add(resultService.findByAssignmentId(assignment.getId()));
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("results", results);
		request.setAttribute("courses", courses);
		
		return "/my_courses.jsp";
	}
	
	private List<Assignment> getAllCoursesByStudent(HttpServletRequest request) throws ServiceException {
		try {
			Long id = ((Student)request.getSession().getAttribute("currentEntity")).getId();
			return assignmentService.findByStudent(id);
		} catch (ServiceException e) {
			throw e;
		}
	}
}
