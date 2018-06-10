<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="filmdetails.css">

<title>Film</title>
</head>
<body>
	<div class="container">
	<div class = "row">
		<div class="col-sm-6">
			<c:forEach items="${filmsbyid}" var="film">
				<td>Film ID: <c:out value="${film.id}" />
				<td><br>
				<td>Title: <c:out value="${film.title}" />
				<td><br>
				<td>Description: <c:out value="${film.description}" />
				<td><br>
				<td>Release Year: <c:out value="${film.releaseYear}" />
				<td><br>
				<td>Language ID: <c:out value="${film.languageId}" />
				<td><br>
				<td>Rental Duration: <c:out value="${film.rentalDuration}" />
				<td><br>
				<td>Rental Rate: <c:out value="${film.rentalRate}" />
				<td><br>
				<td>Length: <c:out value="${film.length}" />
				<td><br>
				<td>Replacement Cost: <c:out value="${film.replacementCost}" />
				<td><br>
				<td>Rating: <c:out value="${film.rating}" />
				<td><br>
				<td>Actors: <c:out value="${film.actors}" />
				<td><br>
				<td>Categories: <c:out value="${film.categories}" />
				<td><br>
				<td><br>
				<td>Inventory: <c:out value="${film.inventoryItems}" />
				<td><br> <br>
				<td>
<<<<<<< HEAD
				
    <form action="updatefilm.do" method="POST">
        <input type="submit" value="Update" />
        <input type="hidden" name="filmid" value="${film.id}"/>
    </form>
=======
    <form:form action="updatefilm.do" method="GET">
        <input type="hidden" name="filmid" value="${film.id}"/>
        <input type="submit" value="Update" />
    </form:form>
>>>>>>> 24bfb4b365e40338fa4aa0e143334e76c91cefe4
</td>
				<td><br> <br>
			</c:forEach>
		</div>
		<div class="col-sm-6"></div>
		</div>
	</div>
</body>
</html>