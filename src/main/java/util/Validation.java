package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import service.ServiceException;
import service.UserService;
import service.implementation.UserServiceImpl;

public class Validation {
	public static String checkStudent(String login, String password, String surname, String name, String studyYear) {
		if (login.equals("") || password.equals("") || surname.equals("") || name.equals("") || studyYear.equals("")) {
			return "All fields must be filled";
		}
		
		if (!tryParse(studyYear)) {
			return "Course is a number";
		}
		
		if (Integer.parseInt(studyYear) < 0 || Integer.parseInt(studyYear) > 6) {
			return "Course might be >= 1 and <=6";
		}
		
		UserService userService = new UserServiceImpl();
		
		try {
			if (userService.findByLoginAndPass(login, password) != null) {
				return "User with such login is already created";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			return "DB problems";
		}
		
		return null;
	}
	
	public static String checkInstructor(String login, String password, String surname, String name) {
		if (login.equals("") || password.equals("") || surname.equals("") || name.equals("")) {
			return "All fields must be filled";
		}
		
		UserService userService = new UserServiceImpl();
		
		try {
			if (userService.findByLoginAndPass(login, password) != null) {
				return "User with such login is already created";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			return "DB problem";
		}
		return null;
	}
	
	private static boolean tryParse(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static String checkCourse(String name, String hours, String topic, String instructorId, String begin,
			String finish) {
		
		if (name.equals("") || hours.equals("") || topic.equals("") || instructorId.equals("-1") || begin.equals("") || finish.equals("")) {
			return "All fields must be filled";
		}
		 
		LocalDate beginDate = LocalDate.parse(begin);
		LocalDate finishDate = LocalDate.parse(finish);
		
		if (beginDate.isBefore(LocalDate.now()))
			return "Date of course start may be later";
		
		if (beginDate.isAfter(finishDate))
			return "Date of startin might be earlier than ending date";
		
		try {
			Integer.parseInt(hours);
		} catch (NumberFormatException e) {
			return "Course duration might be a number";
		}
		
		return null;
	}
}
