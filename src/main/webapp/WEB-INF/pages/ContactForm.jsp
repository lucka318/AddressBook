<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Contact</h1>
		<form:form action="saveContact" method="post" modelAttribute="contact">
		<table>
			<form:hidden path="id"/>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName"/></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address"/></td>
			</tr>
			<tr>
				<td>Telephone:</td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone"/></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td><form:input path="sex" /></td>
				<td><form:errors path="sex"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>