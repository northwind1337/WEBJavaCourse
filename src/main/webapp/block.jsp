<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kick student</title>
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
	<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<form action="/admin" method="get">
        <button type="Submit">Go Back </button>
	</form>
	
	<table>
   		<tr><th>Student name</th><th>Course</th><th>Kick</th></tr>
  		<% 
    		List<Student> students = (List<Student>) request.getAttribute("students");
    		
    			for (Student student: students) {
    				out.print("<tr><td>" + student.getFullName() +
    					"</td><td>" + student.getStudyYear() +
    					"<form action=\"/block\" method=\"get\"></td><td>" +
    					"<button name=\"block_student\" value=\"" + student.getId() + "\" type=\"Submit\">Kick</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    	%>
  	</table>
  	
</body>
</html>