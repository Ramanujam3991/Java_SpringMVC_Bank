<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lambton College</title>

</head>
<body>
	<div class="container">
		<h1 align="center">Create a new Account</h1>
	</div>
	<form:form id="regForm" modelAttribute="account" action="registerProcess"
		method="post">

		<table align="center">
			<tr>
				<td><form:label path="user.username">Username</form:label></td>
				<td><form:input class="form-control" path="user.username"
						name="user.username" id="username" /></td>
			</tr>
			<tr>
				<td><form:label path="user.password">Password</form:label></td>
				<td><form:password class="form-control" path="user.password"
						name="user.password" id="password" /></td>
			</tr>
			<tr>
				<td><form:label path="user.firstname">FirstName</form:label></td>
				<td><form:input class="form-control" path="user.firstname"
						name="user.firstname" id="firstname" /></td>
			</tr>
			<tr>
				<td><form:label path="user.lastname">LastName</form:label></td>
				<td><form:input class="form-control" path="user.lastname"
						name="user.lastname" id="lastname" /></td>
			</tr>
			<tr>
				<td><form:label path="user.email">Email</form:label></td>
				<td><form:input class="form-control" path="user.email"
						name="user.email" id="email" /></td>
			</tr>
			<tr>
				<td><form:label path="user.address">Address</form:label></td>
				<td><form:input class="form-control" path="user.address"
						name="user.address" id="address" /></td>
			</tr>
			<tr>
				<td><form:label path="user.phone">Phone</form:label></td>
				<td><form:input class="form-control" path="user.phone"
						maxlength="10" type="number" name="user.phone" id="phone" /></td>
			</tr>
			<tr>
				<td><form:label path="accountType">Account Type</form:label></td>
				<td><form:select class="form-control" path="accountType"
						name="accountType" id="accountType">
						<form:option value="checking">Checking</form:option>
						<form:option value="saving">Saving</form:option>
						<form:option value="saving">GIC</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button class="form-control btn btn-warning"
						id="register" name="register">Create Account</form:button></td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
	</form:form>

</body>
</html>