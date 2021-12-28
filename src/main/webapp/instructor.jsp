<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Instructor" %>    

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
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<div>
		<p>Name:
			<% 
				Instructor instructor = (Instructor)request
				.getSession()
				.getAttribute("currentEntity");
				out.println(instructor.getFullName());
			%>
		</p>
	</div>
	
	<form action="/instructor_courses" method="get">
		<p>
			<button type="Submit">View my courses</button>
		</p>
	</form>
	<form action="/other_courses" method="get">
		<p>
			<button type="Submit">View other courses</button>
		</p>
	</form>
	</div>
</body>
</html>
