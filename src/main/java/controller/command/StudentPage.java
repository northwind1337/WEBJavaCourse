package controller.command;

import javax.servlet.http.HttpServletRequest;

public class StudentPage implements SendRequest {

	@Override
	public String execute(HttpServletRequest request) {
        return "/student.jsp";
	}
	
}
