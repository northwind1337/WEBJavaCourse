<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Student" %>    
<%@ page import="entities.Marks" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course</title>
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
   		<%
   		List<Marks> results = (List<Marks>) request.getAttribute("results");
   		    		List<Student> students = (List<Student>) request.getAttribute("students");
   		    		String courseId = (String) request.getAttribute("choose");
   		    		if ( results == null) {
   		    			out.print("<div>Course hasn't started, unvaible to set mark</div>");
   		    		} else if (students != null) {
   		    			out.print("<table><tr><th>Name</th><th>Course</th><th>Mark</th></tr>");
   		    			for (int i = 0; i < students.size(); i++) {
   		    				out.print("<tr><td>" + students.get(i).getFullName() +
   		    					"</td><td>" + students.get(i).getStudyYear() +
   		    					"</td><td>" + ((results.get(i) != null) ? results.get(i).getGrade() : "") + 
   		    					((results == null) ? "</td><td>null" : "</td><td><form action=\"/result_course\" method=\"post\"><input type=\"text\" name=\"assess\">" +
   		    					"<button name=\"but_choose\" value=\"" + students.get(i).getId() + ":" + courseId + "\" type=\"Submit\">Save Mark</button>" +
   		    					"</form>"));
   		    				out.println("</td></tr>");
   		    			}
   		    		}
   		    		out.print("</table><div>" +
   		  						"<form action=\"/instructor_courses\" method=\"get\">" +
   		  			"<button>Go Back to courses</button></form></div>");
   		%>
  	
</body>
</html>