<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1 align="center">Login</h1>
	<form:form id="loginForm" modelAttribute="login" action="loginProcess"
		method="post">
		<br/>
		<br/>
		<table align="center">
			<tr>
				<td><form:label  path="username">Username: </form:label></td>
				<td><form:input class="form-control" path="username" name="username" id="username" /></td>
			</tr>
			<tr>
				<td><form:label  path="password">Password:</form:label></td>
				<td><form:password class="form-control" path="password" name="password"
						id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><form:button id="login" class="btn btn-warning" name="login">Login</form:button></td>
			</tr>

			<tr></tr>
			<tr>
				<td></td>
				<td><a href="register">New User?</a></td>
			</tr>
		</table>
	</form:form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>

</body>
</html>