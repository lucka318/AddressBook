<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Manager Home</title>
</head>
<body>
	<div align="center">
		<h1>Contact List</h1>
		<h3>
			<a href="newContact">New Contact</a>
		</h3>
		<h3><span>${error}</span></h3>
		<table border="1">
			<tr>
				<th>No</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Telephone</th>
				<th>Email</th>
				<th>Street Name</th>
				<th>Street No.</th>
				<th>City</th>
				<th>Country</th>
				<th>Sex</th>
				<th>Action</th>
			</tr>

			<c:forEach var="contact" items="${contacts}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${contact.firstName}</td>
					<td>${contact.lastName}</td>
					<td>${contact.phone}</td>
					<td>${contact.email}</td>
					<td>${contact.address.streetName}</td>
					<td>${contact.address.streetNo}</td>
					<td>${contact.address.city.name}</td>
					<td>${contact.address.city.country.name}</td>
					<td>${contact.sex.name}</td>
					<td><a href="editContact?id=${contact.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteContact?id=${contact.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div align="center">
		<h1>Address List</h1>
		<h3>
			<a href="newAddress">New Address</a>
		</h3>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Street Name</th>
				<th>Street No.</th>
				<th>City Name</th>
				<th>City Zipcode</th>
				<th>Country Name</th>
				<th>Country ALPHA_2</th>
				<th>Action</th>
			</tr>

			<c:forEach var="address" items="${addresses}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${address.streetName}</td>
					<td>${address.streetNo}</td>
					<td>${address.city.name}</td>
					<td>${address.city.zipcode}</td>
					<td>${address.city.country.name}</td>
					<td>${address.city.country.alpha_2}</td>
					<td><a href="editAddress?id=${address.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteAddress?id=${address.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
