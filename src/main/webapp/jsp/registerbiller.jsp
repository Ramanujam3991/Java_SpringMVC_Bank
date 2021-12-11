<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<meta charset="ISO-8859-1">
<title>Lambton Bank</title>
</head>
<body>
	<div class="container">
		<h1 align="center">Register Biller</h1>
	</div>
	<form:form id="regForm" modelAttribute="myBills"
		action="registerbiller" method="post">

		<table align="center">
			<tr><td></td><td style="color:red">${message}</td></tr>
			<tr>
				<td><form:label path="toUser.username">Biller information </form:label></td>
				<td><form:input class="form-control" type="text" 
						path="toUser.username" name="toUser.username"
						 /></td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><input type="submit" value="Register Biller"
					class=" btn btn-success"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>

	</form:form>
</body>
</html>