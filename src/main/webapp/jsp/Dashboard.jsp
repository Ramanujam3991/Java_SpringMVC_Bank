<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lambton Bank</title>
</head>
<body>
<h1 align="center">Dashboard</h1>

	<table class="table">
		<tr>
			<th scope="col">Account Id(click Account Id to know details)</th>
			<th scope="col">Account Status</th>
			<th scope="col">Account type</th>
			<th scope="col">Account Balance</th>
		
		</tr>
			
			<tr>
			<c:forEach var="account" items="${accountlst}">

			<tr scope="row">
				<td><a href="accountDetails/${account.accountType }">${account.accountId }</a></td>
				<td>${account.accountStatus }</td>
				<td>${account.accountType }</td>
				<td>${account.currentBalance }</td>

			</tr>
					</c:forEach>
			

	</table>
	
	
	

</body>
</html>