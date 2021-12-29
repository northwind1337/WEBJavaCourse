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
<title>Teacher registration</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/success_instructor_registration" method="post" accept-charset="UTF-8">
	<label>Surname</label><br><input type="text" name="surname" />
        
    <p>
    	<label>Name</label><br><input type="text" name="name" />
      
    </p>
    <p>
    	<label>Login</label><br><input type="text" name="login" />
    </p>
    <p>
    <label>Password</label><br><input type="text" name="password" />
            
    </p>
    
    <div>
   		<button type="Submit">Register</button>
   
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