<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Instructor" %>
<%@ page import="java.util.List" %>

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
<title>Create Course</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/create_course" method="post" accept-charset="UTF-8">
	<label>Name</label><br><input maxlength="100" type="text" name="name" />
    <p>
    	<label>Duration</label><br><input maxlength="3" type="text" name="hours" reqired />
    </p>
    <p>
    	<label>Theme</label><br><input maxlength="30" type="text" name="topic" />
    </p>
    <p>
    <label>Teacher</label><br>
    	<p><select size="1" name="instructor_id" reqired >
    		<option value="-1"></option>
    	<%
    		List<Instructor> instructors = (List<Instructor>) request.getAttribute("instructors");
 			out.print("");
    		if (instructors != null) {
    			for(Instructor instructor: instructors) {
    				out.print("<option value=\"" + instructor.getId() + "\">" + instructor.getFullName() + "</option>");
    			}
 			}
    	%>
   		</select></p>    
    </p>
    <p>
    <label>Start at</label><br><input type="date" name="begin" reqired />     
    </p>
    <p>
    <label>Ends at</label><br><input type="date" name="finish" reqired />     
    </p>
    <div>
   		<button name="created" value="true" type="Submit">Register</button>
    </div>
    
    
    <br>
    <br>
    </form>
    
    <form action="/admin" method="get">
    <p>
    		<button type="Submit">Go Back</button>
    </p>
    </form>
    <p>
    	<%
    		String message = (String) request.getAttribute("message");
    		if (message != null)
    			out.println(message);
    	%>
    </p>
    </div>
</body>
</html>