package controller.command;

import javax.servlet.http.HttpServletRequest;

import entities.Admin;
import entities.Entity;
import entities.User;
import service.TeachersService;
import service.StudentService;
import service.UserService;
import service.implementation.TeachersServiceImpl;
import service.implementation.StudentServiceImpl;
import service.implementation.UserServiceImpl;
import util.SessionUsing;

public class UserLoginPage implements SendRequest {
	private UserService userService = new UserServiceImpl();
	private TeachersService instructorService = new TeachersServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if (request.getMethod().equals("GET")) {
            return "/login.jsp";
        }
		
		try {
            User user = userService.findByLoginAndPass(request.getParameter("login"),
            		request.getParameter("password"));
            if (user == null) {
            	request.setAttribute("wrong", "Login or password is incorrect! Try again!");
            	return "/login.jsp";
            }
            
            Entity entity = null;
            boolean blocked = false;
            if (user.getRole().getId() == 1) {
            	entity = instructorService.findById(user.getId());
            } else if (user.getRole().getId() == 2) {
            	entity = studentService.findById(user.getId());
            	blocked = studentService.isBlocked(user.getId());
            } else if (user.getRole().getId() == 0) {
            	entity = new Admin();
            }
            
            if (blocked) {
            	request.setAttribute("wrong", "You were blocked by Admin");
            	return "login.jsp";
            }
            
            SessionUsing.addUserSession(request.getSession(), user, entity);
            
            if (user.getRole().getId() == 0) {
            	return "/admin.jsp";
            } else if (user.getRole().getId() == 1) {
            	return "/instructor.jsp";
            } else if (user.getRole().getId() == 2) {
            	return "/student.jsp";
            }

        } catch (Exception ex) {
            request.setAttribute("wrong", "Username not found");
            return "/login.jsp";
        }
		
		return "/login.jsp";
	}
}
