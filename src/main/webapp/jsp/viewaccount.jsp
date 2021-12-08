<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Account Number:${accountId}</h1>
	<div style="border: thick solid black;">
	<ul class="list-group">
		<li class="list-group-item">Account Number: ${transaction.fromAccount.accountId}</li>
		<li class="list-group-item">Account Status: ${transaction.fromAccount.accountStatus }</li>
		<li class="list-group-item">Account Type: ${transaction.fromAccount.accountType }</li>
		<li class="list-group-item">Account Balance: ${transaction.fromAccount.currentBalance}</li>
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
			<tr>
				<td>${transaction.transactionId}</td>
				<td>${transaction.transactionType}</td>
				<td>${transaction.transactionDate}</td>
				<td>${transaction.comments}</td>
				<td>${transaction.transactionAmount}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>