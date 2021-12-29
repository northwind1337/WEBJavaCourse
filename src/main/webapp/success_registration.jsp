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
<title>Success registration</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<div>
		<p>
			Congratulations!!! You were registered on course
		</p>
	</div>
	
	<div>
	<form action="/other_courses" method="get">
		<p>
			<button name="choose" value=null type="Submit">See another courses</button>
		</p>
	</form>
	</div>
	</div>
</body>
</html>