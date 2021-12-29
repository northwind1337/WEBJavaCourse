package controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements SendRequest {

	@Override
	public String execute(HttpServletRequest request) {
		return "/admin.jsp";
	}
}
