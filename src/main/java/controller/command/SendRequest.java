package controller.command;

import javax.servlet.http.HttpServletRequest;

public interface SendRequest {
	String execute(HttpServletRequest request);
}
