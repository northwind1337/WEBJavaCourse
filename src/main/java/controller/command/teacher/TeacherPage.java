package controller.command.teacher;

import javax.servlet.http.HttpServletRequest;

import controller.command.SendRequest;

public class TeacherPage implements SendRequest {

	@Override
	public String execute(HttpServletRequest request) {
		
		return "/instructor.jsp";
	}
}
