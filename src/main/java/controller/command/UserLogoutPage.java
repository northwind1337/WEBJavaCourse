package controller.command;

import javax.servlet.http.HttpServletRequest;

import util.SessionUsing;

public class UserLogoutPage implements SendRequest {

	@Override
	public String execute(HttpServletRequest request) {
		SessionUsing.deleteUserSession(request.getSession());
		return "/logout.jsp";
	}
}
