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
		<div class="row">
			<div class="col-sm-6">
				<c:if test="${empty filmsbyid }">
					<td>No Films Found Matching that Criteria</td>
					<form:form action="intro.html" method="GET">
						<input type="submit" value="Back" />
					</form:form>
				</c:if>
				<c:forEach items="${filmsbyid}" var="film">
					<td> <span class = "headings">Film ID: </span><c:out value="${film.id}" />
					<td><br>
					<td> <span class = "headings">Title: </span><c:out value="${film.title}" />
					<td><br>
					<td> <span class = "headings">Description: </span><c:out value="${film.description}" />
					<td><br>
					<td> <span class = "headings">Release Year: </span><c:out value="${film.releaseYear}" />
					<td><br>
					<td> <span class = "headings">Language ID: </span><c:out value="${film.languageId}" />
					<td><br>
					<td> <span class = "headings">Rental Duration: </span> <c:out value="${film.rentalDuration}" />
					<td><br>
					<td> <span class = "headings">Rental Rate: </span> <c:out value="${film.rentalRate}" />
					<td><br>
					<td> <span class = "headings">Length: </span><c:out value="${film.length}" />
					<td><br>
					<td> <span class = "headings">Replacement Cost: </span><c:out value="${film.replacementCost}" />
					<td><br>
					<td> <span class = "headings">Rating: </span> <c:out value="${film.rating}" />
					<td><br>
					<td> <span class = "headings">Special Features: </span> <c:out value="${film.specialFeatures}" />
					<td><br>
					<td> <span class = "headings">Actors: </span><c:out value="${film.actors}" />
					<td><br>
					<td> <span class = "headings">Categories: </span><c:out value="${film.categories}" />
					<td><br>
					<td><br>
					<td> <span class = "headings">Inventory: </span> <c:out value="${film.inventoryItems}" />
					<td><br> <br>
					<td><form:form action="updatefilm.do" method="GET">
							<input type="hidden" name="filmid" value="${film.id}" />
							<input type="submit" value="Update" />
						</form:form> <form:form action="deletefilm.do" method="GET">
							<input type="hidden" name="filmid" value="${film.id}" />
							<input type="submit" value="Delete" />
						</form:form> <form:form action="intro.html" method="GET">
							<input type="submit" value="Back" />
						</form:form></td>
					<td><br> <br>
				</c:forEach>
			</div>
			<div class="col-sm-6"></div>
		</div>
	</div>
</body>
</html>