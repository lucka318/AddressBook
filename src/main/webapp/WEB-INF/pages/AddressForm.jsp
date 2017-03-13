<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Address</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Address</h1>
		<form:form action="saveAddress" method="post" modelAttribute="address">
		<table>
			<form:hidden path="id"/>
			<tr>
				<td>Street Name:</td>
				<td><form:input path="streetName" /></td>
				<td><form:errors path="streetName"/></td>
			</tr>
			<tr>
				<td>Street Number:</td>
				<td><form:input path="streetNo" /></td>
				<td><form:errors path="streetNo"/></td>
			</tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>