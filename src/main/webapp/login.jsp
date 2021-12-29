<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System Login</title>
<style>
	*{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;
	}
</style>
</head>
<body>

	<form style="padding-left: 480px; padding-top: 150px;" action="/login" method="post" accept-charset="UTF-8">
	
		
		<div>
            <p>
                Enter your login:
            </p>
        </div>
        <div>
            <input type="text" name="login"/>
        </div>
        <div>
            <p>
                Enter your password:
            </p>
        </div>
        <div>
            <div>
                <input type="text" name="password"/>
            </div>
        </div>
        
        <p>
        	<button name="Submit" value="Login" type="Submit">Sign in</button>
    	</p>
    </form>
        
        <form action="/registration" method="get">
        <button style="margin-left: 480px; width: 70px;" type="Submit">Sign Up</button>
    	</form>
    	
    	<div style="margin-left: 480px;">
            <div>
                <% 
                	if (request.getAttribute("wrong") != null)
	                	out.println(request.getAttribute("wrong")); 
                %>
            </div>
        </div>
</body>
</html>
