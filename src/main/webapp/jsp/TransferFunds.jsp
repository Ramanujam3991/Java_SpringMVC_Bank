<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Lambton Bank</title>
</head>
<body>
	<div align="center"></div>
	<h1>Transfer Funds</h1>
	<form action="forexProcess" method="post">
		<table style="with: 80%">
		<tr>
		<td></td><td style="color:red">${message }</td>
		</tr>
			<tr>
				<td>Receiving Account ID:</td>
				<td><input type="text" class="form-control" name="accout2_id" /></td>
			</tr>
			<tr>
				<td>Amount:</td>
				<td><input type="text" class="form-control" name="amount" /></td>
			</tr>			
		</table>
		<input type="submit" name="action" value="Confirm" class="form-control btn btn-success btn-sm" ><br>
		<br>
    	<input type="submit" name="action" value="Cancel" class="form-control btn btn-warning">
  	</form>
</body>
</html>