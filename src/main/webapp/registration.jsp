<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
	*{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;'
	}
</style>
</head>
<body>
	<form style="padding-left: 480px; padding-top: 150px;" action="/registration" method="post" accept-charset="UTF-8">
	<label>Surname</label><br><input type="text" name="surname" />
        
    <p>
    	<label>Name</label><br><input type="text" name="name" />
      
    </p>
    <p>
    	<label>Course</label><br><input type="text" name="studyYear" /> 
    </p> 
    <p>
    	<label>Login</label><br><input type="text" name="login" />
    </p>
    <p>
    <label>Password</label><br><input type="text" name="password" />
            
    </p>
    
    <div>
    	<p>
    		<button type="Submit">Sign Up</button>
    	</p>
    </div>
    <br>
    <br>
    </form>
    
    
    
    <form style="margin-left: 480px; width: 70px;" action="/login" method="get">
    	<button type="Submit">Cancel</button>
    </form>
    <p>
    	<%
    		String message = (String) request.getAttribute("errorMessage");
    		if (message != null)
    			out.println(message);
    	%>
    </p>
</body>
</html>
