<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Course" %>

<!DOCTYPE html>
<html>
<head>
<style>
	*{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;
	}
</style>
<meta charset="UTF-8">
<title>All Courses</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/logout" method="get">
        <button type="Submit">Log Out </button>
	</form>
	<form action="/my_courses" method="get">
	<p>
        <button name="content" value="1" type="Submit">Planned courses</button>
    </p>
    </form>
    
    <form action="/my_courses" method="get">
	<p>
        <button name="content" value="2" type="Submit">Active courses</button>
    </p>
    </form>
    
    <form action="/my_courses" method="get">
	<p>
        <button name="content" value="3" type="Submit">Finished courses</button>
    </p>
    </form>
    
    <% 
    	List<Course> courses = (List<Course>) request.getAttribute("courses");
    	if (courses != null) {
    		for (Course course: courses) {
    			out.print("Name: " + course.getName() +
    					"\t\tDuration: " + course.getHours() +
    					"\t\tTheme: " + course.getTopic() + 
    					"\t\tTeacher: " + course.getInstructor().getSurname() +
    					" " + course.getInstructor().getName());
    			out.println("<br>");
    		}
    
    
    	}
    %>
</div>
</body>
</html>