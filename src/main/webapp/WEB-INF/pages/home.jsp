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
		<table border="1">
			<th>No</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Address</th>
			<th>Telephone</th>
			<th>Email</th>
			<th>Sex</th>
			<th>Action</th>

			<c:forEach var="contact" items="${contacts}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${contact.firstName}</td>
					<td>${contact.lastName}</td>
					<td>${contact.phone}</td>
					<td>${contact.email}</td>
					<td>${contact.address.streetName}${contact.address.streetNo}</td>
					<td>${contact.city.name}</td>
					<td>${contact.country.name}</td>
					<td>${contact.sex}</td>
					<td><a href="editContact?id=${contact.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteContact?id=${contact.id}">Delete</a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>

	<div align="center">
		<h1>City List</h1>
		<h3>
			<a href="newCity">New City</a>
		</h3>
		<table border="1">
			<th>No</th>
			<th>Name</th>
			<th>Zip code</th>
			<th>Country</th>

			<c:forEach var="city" items="${cities}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${city.name}</td>
					<td>${city.zipcode}</td>
					<td>${city.country.name}</td>
					<td><a href="editCity?id=${city.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteCity?id=${city.id}">Delete</a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>

	<div align="center">
		<h1>Country List</h1>
		<h3>
			<a href="newCountry">New Country</a>
		</h3>
		<table border="1">
			<th>No</th>
			<th>Name</th>
			<th>Alpha_2</th>
			<th>Alpha_3</th>

			<c:forEach var="country" items="${countries}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${country.name}</td>
					<td>${country.alpha_2}</td>
					<td>${country.alpha_3}</td>
					<td><a href="editCountry?id=${country.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteCountry?id=${country.id}">Delete</a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
