<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ChatApp</title>
<link rel="StyleSheet" href="chatview/Form.css">
</head>      
<body>
<form action="login" method="post">

<div class="row" style="margin-top: 150px;">
 <label for="name">UserName</label>
<input type="text" name="userName" required>
</div>

<div class="row">
 <label for="name">Password</label>
<input type="text" name="password" required>
</div>

<div class="row">
<input type="submit" name="Login" value="Login" style="width: 100%; margin: 8px 0px;">
</div>

<div>
<h4>Don't have an account?<a href="chatview/signup.jsp" style="margin-left: 25px;">Sign up</a></h4>
</div>


</form>
</body>
</html>