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
	<h1>${fromAccount.accountType } account - ${fromAccount.accountId} details:</h1>
	<div style="border: thick solid black;">
	<ul class="list-group">
		<li class="list-group-item">Account Number: ${fromAccount.accountId}</li>
		<li class="list-group-item">Account Status: ${fromAccount.accountStatus }</li>
		<li class="list-group-item">Account Type: ${fromAccount.accountType }</li>
		<li class="list-group-item">Account Balance: ${fromAccount.currentBalance}</li>
	</ul>
	</div>
	<h1>Recent Transactions</h1>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Transaction Id</th>
				<th scope="col">Transaction Type</th>
				<th scope="col">Transaction Date</th>
				<th scope="col">Comments</th>
				<th scope="col">Transaction Amount</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${transaction }" var="transaction">
		<tr>
				<td>${transaction.transactionId}</td>
				<td>${transaction.transactionType}</td>
				<td>${transaction.transactionDate}</td>
				<td>${transaction.comments}</td>
				<td>${transaction.transactionAmount}</td>
			</tr>
		</c:forEach>
			
		</tbody>
	</table>
</body>
</html>