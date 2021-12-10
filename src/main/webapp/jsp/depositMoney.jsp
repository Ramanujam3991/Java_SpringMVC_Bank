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
		<h1 align="center">Deposit Money</h1>
		<p>Note: Go to the nearest branch and deposit the amount after creating a request below</p>
	</div>
	<form:form id="regForm" modelAttribute="transaction"
		action="depositAmount" method="post">

		<table align="center">
			<tr>
				<td><form:label path="fromAccount.accountId">Select Account</form:label></td>
				<td><form:select class="form-control" path="fromAccount.accountId"
						name="fromAccount.accountId" id="fromAccount">
						<c:forEach items="${accountLst}" var="acc">
							<form:option value="${acc.accountId}">Account Id: ${acc.accountId} | Type: ${acc.accountType} | Balance: ${acc.currentBalance}</form:option>
						</c:forEach>

					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="transactionAmount">Enter the amount to deposit</form:label></td>
				<td><form:input class="form-control" type="number"
						path="transactionAmount" name="transactionAmount"
						id="transactionAmount" /></td>
			</tr>
			<tr>
				<td><form:label path="comments">Enter Comments</form:label></td>
				<td><form:input class="form-control" type="text" value="money deposited"
						path="comments" name="comments"
						id="comments" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Deposit Amount"
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