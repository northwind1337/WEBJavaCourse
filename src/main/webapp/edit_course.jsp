<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Instructor" %>
<%@ page import="domain.Course" %>
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
<title>Course Edit</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<form action="/admin">
		<button>Go back to Main Page</button>
	</form>
	<% 
		Course course = (Course) request.getAttribute("course");
	%>
	<form action="/edit_course" method="post" accept-charset="UTF-8">
	<label>Name</label><br>
	<%
		out.print("<input maxlength=\"100\" type=\"text\" name=\"name\" value=\"" + course.getName() + "\" placeholder=\"" + course.getName() + "\" />");
	 %>
	<p>
    	<label>Duration</label><br>
    	<% 
    		out.print("<input maxlength=\"3\" type=\"text\" name=\"hours\" reqired value=\"" + course.getHours() + "\" placeholder=\"" + course.getHours() + "\" />");
    	%>
    </p>
    <p>
    	<label>Theme</label><br>
    	<% 
    		out.print("<input maxlength=\"30\" type=\"text\" name=\"topic\" value=\"" + course.getTopic() + "\" placeholder=\"" + course.getTopic() + "\" />");
    	%>
    </p>
    <p>
    <label>Teacher</label><br>
    	<p><select size="1" name="instructor_id" reqired >
    	<%	
    		out.print("<option selected value=\"" + course.getInstructor().getId() + "\">" + course.getInstructor().getFullName() + "</option>");
    	
    		List<Instructor> instructors = (List<Instructor>) request.getAttribute("instructors");
    		if (instructors != null) {
    			for (Instructor instr: instructors) {
    				out.println("<option value=\"" + instr.getId() + "\">" + instr.getFullName() + "</option>");
    			}
 			}
    	%>
   		</select></p>    
    </p>
    <p>
    <% 
    	out.print("<label>Starts at</label><br><input type=\"date\" name=\"begin\" value=\"" + course.getBeginDate() + "\" placeholder=\"" + course.getBeginDate() + "\" reqired />");
    %>		
    </p>
    <p>
    <% 
    		out.print("<label>Ends at</label><br><input type=\"date\" name=\"finish\" value=\"" + course.getFinishDate() + "\" placeholder=\"" + course.getFinishDate() + "\" reqired />");
    %>
    </p>
    <div>
   		<% out.print("<button name=\"edited\" value=\"" + course.getId() + "\" type=\"Submit\">Save changes</button>"); %>
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