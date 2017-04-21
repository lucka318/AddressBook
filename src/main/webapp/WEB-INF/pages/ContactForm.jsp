<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Contact</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Contact</h1>
		<form:form action="saveContact" method="post"
			modelAttribute="contactEntity">
			<table>
				<tr>
					<td>First Name:</td>
					<td><form:input path="contactEntity.firstName" /></td>
					<td><form:errors path="contactEntity.firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="contactEntity.lastName" /></td>
					<td><form:errors path="contactEntity.lastName" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="contactEntity.email" /></td>
					<td><form:errors path="contactEntity.email" /></td>
				</tr>
				<tr>
					<td>Telephone:</td>
					<td><form:input path="contactEntity.phone" /></td>
					<td><form:errors path="contactEntity.phone" /></td>
				</tr>
				<tr>
					<td><form:radiobutton path="name" value="Male"
							checked="checked" />Male <form:radiobutton path="name"
							value="Female" />Female</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>