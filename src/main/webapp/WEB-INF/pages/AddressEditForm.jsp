<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Address</title>
</head>
<body>
	<div align="center">
		<h1>Edit Address</h1>
		<form:form action="saveEditAddress" method="post"
			modelAttribute="addressEntity">
			<table>
				<tr>
					<td>Street Name:</td>
					<td><form:input value="${streetName}" path="streetName" /></td>
					<td><form:errors path="streetName" /></td>
				</tr>
				<tr>
					<td>Street Number:</td>
					<td><form:input path="streetNo" /></td>
					<td><form:errors path="streetNo" /></td>
				</tr>
				<tr>
					<td>Choose city:</td>
					<td><select name="cities">
							<c:forEach items="${cities}" var="city">
								<c:choose>
									<c:when test="${city.id == addressEntity.cityID}">
										<option value="${city.id}" selected>${city.name},
											${city.country.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${city.id}">${city.name},
											${city.country.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
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