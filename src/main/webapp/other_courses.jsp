<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Course" %>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Other courses</title>
<style>
   *{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;
	}
   table {
    width: 100%;
    background: black;
    color: black;
    border-spacing: 1px;
   }
   td, th {
    background: white;
    padding: 5px;
   }
   select: {
   	display: inline-block;
   }
  </style>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<form class="select" action="/other_courses" method="get">
	
        <button name="sort" value="1" type="Submit">Sort courses by name</button>
	
        <button name="sort" value="2" type="Submit">Sort courses by duration</button>
    
		<input type="text" name="topic"/>
        <button name="sort" value="3" type="Submit">Search by theme</button>
    
    
		<input type="text" name="instructor"/>
        <button name="sort" value="4" type="Submit">Search by teacher</button>
   	</form>
   	
   	<% 
   		if ((Long) request.getSession().getAttribute("currentRole") == 2)
   			out.print("<form action=\"/student\" method=\"get\">");
   		else if ((Long) request.getSession().getAttribute("currentRole") == 1)
   			out.print("<form action=\"/instructor\" method=\"get\">");
   	%> 
   	
   		<p>
    	<button type="Submit">Back to Main Page</button>
    	</p>
    </form>
    
    <table>
   		<th>Course Name</th><th>Duration</th><th>Theme</th><th>Teacher</th>
  		<% 
    		List<Course> courses = (List<Course>) request.getAttribute("courses");
    		if (courses != null) {
    			for (int i = 0; i < courses.size(); i++) {
    				out.print("<tr><td>" + courses.get(i).getName() +
    					"</td><td>" + courses.get(i).getHours() +
    					"</td><td>" + courses.get(i).getTopic() + 
    					"</td><td>" + courses.get(i).getInstructor().getFullName());
    				out.println("</td></tr>");
    			}
    		}
    	%>
  	</table>
  	<%
	List<Course> newCourses = (List<Course>) request.getAttribute("courses");
	if (courses != null) {
		for (int i = 0; i < newCourses.size(); i++) {
			out.print("<form action=\"/course_registration\" method=\"get\"></td><td>" +
				"<button name=\"choose\" value=\"" + newCourses.get(i).getId() + "\" type=\"Submit\">Procceed</button>" +
				"</form>");
			//out.println("</td></tr>");
		}
	}
  	%>
</body>
</html>