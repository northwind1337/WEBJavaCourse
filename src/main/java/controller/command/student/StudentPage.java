package controller.command.student;

import javax.servlet.http.HttpServletRequest;

import controller.command.SendRequest;

public class StudentPage implements SendRequest {

	@Override
	public String execute(HttpServletRequest request) {
        return "/student.jsp";
	}
}
