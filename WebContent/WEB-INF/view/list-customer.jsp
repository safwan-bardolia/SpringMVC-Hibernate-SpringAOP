<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>List customer</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	</head>
	
	<body>
	
		<div class="container">										<!-- send to addcutomer in controller customer/AddCustomer -->
			<button type="button" class="btn btn-primary" onclick="window.location.href='AddCustomer'">Add Customer</button>
			<table class="table table-bordered">
		
				<thead class="thead-dark">
					<tr>
						<th>First Name </th>
						<th>Last Name </th>
						<th>Email </th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
		
				<tbody>
					<c:forEach items="${customers}" var="i">
						
						<c:url var="updateLink" value="UpdateCustomer">	<!-- means customer/UpdateCustomer -->
							<c:param name="customerId" value="${i.id}"></c:param>
						</c:url>
						
						<c:url var="deleteLink" value="DeleteCustomer">	<!-- means customer/UpdateCustomer -->
							<c:param name="customerId" value="${i.id}"></c:param>
						</c:url>
						
						<tr>
							<td> <c:out value="${i.f_name}"></c:out> </td>
							<td> <c:out value="${i.l_name}"></c:out> </td>
							<td> <c:out value="${i.email}"></c:out> </td>
							<td> <button class="btn btn-info" onclick="window.location.href='${updateLink}'">Update</button></td>
						    <td> <button class="btn btn-danger" onclick="window.location.href='${deleteLink}'">Delete</button></td>
						</tr>		
					</c:forEach>
				</tbody>
		
			</table>
		</div>
		
	</body>
</html>