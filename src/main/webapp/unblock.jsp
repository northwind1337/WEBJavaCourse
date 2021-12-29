<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get back students</title>
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
</style>
</head>
<body>
<div style = "margin-left: 0px; margin-top: 150px">
<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<form action="/admin" method="get">
  		<button type="Submit">Go Back</button>
  	</form>
	
	<table>
		<tr><th>Name</th><th>Course</th><th>Get Back</th></tr>  		
		<% 
    		List<Student> students = (List<Student>) request.getAttribute("students");
    		
    			for (Student student: students) {
    				out.print("<tr><td>" + student.getFullName() +
    					"</td><td>" + student.getStudyYear() +
    					"<form action=\"/unblock\" method=\"get\"></td><td>" +
    					"<button name=\"unblock_student\" value=\"" + student.getId() + "\" type=\"Submit\">Get Back</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    	%>
  	</table>
  	
  	</div>
</body>
</html>