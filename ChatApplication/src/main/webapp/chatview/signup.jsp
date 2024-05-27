<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatApp</title>
<link rel="StyleSheet" href="Form.css">
</head>
<body>
<form action="signup" method="post">

<div class="row" style="margin-top: 150px;">
 <label for="name">Name</label>
<input type="text" name="name" required>
</div>

<div class="row">
 <label for="username">UserName</label>
<input type="text" name="username" required>
</div>

<div class="row">
 <label for="email">Email</label>
<input type="text" name="email" required>
</div>

<div class="row">
 <label for="password">Password</label>
<input type="text" name="password" required>
</div>

<div class="row">
 <label for="repassword">Re-enter password</label>
<input type="text" name="repassword" required>
</div>

<div class="row">
<input type="submit" name="signup" value="signup" style="width: 100%; margin: 8px 0px;">
</div>

</form>
</body>
</html>