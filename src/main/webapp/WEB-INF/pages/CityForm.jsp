<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit City</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit City</h1>
		<form:form action="saveCity" method="post" modelAttribute="city">
		<table>
			<form:hidden path="id"/>
			<tr>
				<td>City Name:</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td>Zip code:</td>
				<td><form:input path="zipcode" /></td>
				<td><form:errors path="zipcode"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>