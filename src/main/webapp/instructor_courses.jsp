<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Course" %>	
<%@ page import="domain.Result" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Courses</title>
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
   form {
   	display: block;
   }
   corner: {
   	position: absolute; 
   	right: 0;
   }
  </style>
</head>
<body>
<div style = "margin-left: 0px; margin-top: 150px">
	<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	
	<form action="/instructor_courses" method="get">
	
        <button name="content" value="1" type="Submit">Planned courses</button>
    
	
        <button name="content" value="2" type="Submit">Active courses</button>
    
	
        <button name="content" value="3" type="Submit">Finished courses</button>

    </form>
    
    <form action="/instructor" method="get">
    	<p>
        <button type="Submit">Go back to Main Page</button>
    	</p>
    </form>
    
    <table>
   		<tr><th>Name</th><th>Duration</th><th>Theme</th><th>Details</th></tr>
   		
  		<% 
    		List<Course> courses = (List<Course>) request.getAttribute("courses");
    		if (courses != null) {
    			for (int i = 0; i < courses.size(); i++) {
    				out.print("<tr><td>" + courses.get(i).getName() +
    					"</td><td>" + courses.get(i).getHours() +
    					"</td><td>" + courses.get(i).getTopic() +
    					"</td><td>" + "<form action=\"/result_course\" method=\"get\">" +
    					"<button name=\"choose\" value=\"" + courses.get(i).getId() + "\" type=\"Submit\">Procceed</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    		}
    	%>
  	
  	
  	</table>
  	</div>>
</body>
</html>