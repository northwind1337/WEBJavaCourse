<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	*{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<form action="/block" method="get">
		<p>
			<button type="Submit">Kick student</button>
		</p>
	</form>
	<form action="/unblock" method="get">
		<p>
			<button type="Submit">Get back student</button>
		</p>
	</form>
	<form action="/create_course" method="get">
		<p>
			<button type="Submit">Create course</button>
		</p>
	</form>
	<form action="/all_courses" method="get">
		<p>
			<button type="Submit">Update course</button>
		</p>
	</form>
	<form action="/delete_course" method="get">
		<p>
			<button type="Submit">Delete course</button>
		</p>
	</form>
	<form action="/registration_instructor" method="get">
		
			<button type="Submit">Register teacher</button>
		
	</form>
</div>
</body>
</html>
