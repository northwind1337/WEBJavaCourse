<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Course" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<div style = "margin-left: 0px; margin-top: 150px">
<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<form action="/admin">
		<button>Go to Main Page</button>
	</form>
	<table>
   		<tr><th>Name</th><th>Duration</th><th>Theme</th><th>Teacher</th><th>Delete</th></tr>
  		<% 
    		List<Course> courses = (List<Course>) request.getAttribute("courses");
    		if (courses != null) {
    			for (int i = 0; i < courses.size(); i++) {
    				out.print("<tr><td>" + courses.get(i).getName() +
    					"</td><td>" + courses.get(i).getHours() +
    					"</td><td>" + courses.get(i).getTopic() + 
    					"</td><td>" + courses.get(i).getInstructor().getFullName() +
    					" " + courses.get(i).getInstructor().getName() +
    					"<form action=\"/delete_course\"></td><td>" +
    					"<button name=\"deleted\" value=\"" + courses.get(i).getId() + "\" type=\"Submit\">Delete course</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    		}
    	%>
  	</table>
  	</div>
</body>
</html>