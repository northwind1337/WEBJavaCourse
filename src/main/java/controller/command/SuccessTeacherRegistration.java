package controller.command;

import javax.servlet.http.HttpServletRequest;

import entities.Student;
import entities.Teacher;
import entities.User;
import enums.Roles;
import service.TeachersService;
import service.UserService;
import service.implementation.TeachersServiceImpl;
import service.implementation.UserServiceImpl;
import util.SessionUsing;
import util.Validation;

public class SuccessTeacherRegistration implements SendRequest {

	private UserService userService = new UserServiceImpl();
	private TeachersService instructorService = new TeachersServiceImpl();

	@Override
	public String execute(HttpServletRequest request) {
		if (request.getMethod().equals("GET")) {
            return "/registration.jsp";
        }
		
		String login = (String)request.getParameter("login");
        String password = (String)request.getParameter("password");
        String surname = (String)request.getParameter("surname");
        String name = (String)request.getParameter("name");
		
        String validationResult = Validation.checkInstructor(login, password, surname, name);
		if (validationResult != null) { 
			request.setAttribute("message", validationResult); 
			return "/registration_instructor.jsp";
		}
		
		try {
            User createdUser = new User();
            
            createdUser.setLogin(login);
            createdUser.setPassword(password);		
            createdUser.setRole(Roles.INSTRUCTOR);
            Long id = userService .save(createdUser);
            createdUser.setId(id);
            
            Teacher createdInstructor = new Teacher();
            createdInstructor.setSurname((String)request.getParameter("surname"));
            createdInstructor.setName((String)request.getParameter("name"));
            createdInstructor.setId(id);
            instructorService.create(createdInstructor);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "/registration_instructor.jsp";
        }
		request.setAttribute("message", "Teacher Registrated successfully");
        return "/registration_instructor.jsp";
	}

}
