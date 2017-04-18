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
		<form:form action="saveContact" method="post" modelAttribute="addressBookEntity">
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
				<td>Telephone:</td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone"/></td>
			</tr>
			<tr>
					<td><form:radiobutton path="name" value="Male" checked="checked" />Male <form:radiobutton
							path="name" value="Female" />Female</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"></td>
			</tr>
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
				<td>Country Name:</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td>Alpha_2:</td>
				<td><form:input path="alpha_2" /></td>
				<td><form:errors path="alpha_2"/></td>
			</tr>
			<tr>
				<td>Alpha_3</td>
				<td><form:input path="alpha_3" /></td>
				<td><form:errors path="alpha_3"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>