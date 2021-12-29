<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Page</title>
<style>
	*{
		background: linear-gradient(#9A5044, #E8D9A9) fixed;
		font-weight: 400;
	}
	form {
		display: inline-block;
	}
</style>
</head>
<body>
	<form style="margin-left: 525px;" action="/logout" method="get">
        <button type="Submit">Log Out </button>
	</form>
	<div style="margin-left: 480px; margin-top: 150px">
	<div>
		<p>Surname and name:
			<% 
				Student student = (Student)request
				.getSession()
				.getAttribute("currentEntity");
				out.println(student.getSurname() + " " + student.getName());
			%>
		</p>
	</div>
	
	<div>
		<p>Course:
			<% 
				out.println(student.getStudyYear());
			%>
		</p>
	</div>
	</div>
	
	
	<form style="margin-left: 450px; margin-top: 5px;" action="/my_courses" method="get">
		<p>
			<button type="Submit">View my courses</button>
		</p>
	</form>
	<form action="/other_courses" method="get">
		<p>
			<button type="Submit">View another courses</button>
		</p>
	</form>
</body>
</html>
