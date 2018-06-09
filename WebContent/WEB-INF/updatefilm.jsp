<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- TODO: Add the @taglib for form -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="main.css">
<title>Update Film</title>
</head>
<body>
	<form:form action="updatefilmdetails.do" method="POST" modelAttribute="film">
		<form:label path="title">Title:</form:label>
		<form:input path="title" value="${film.title}" />
		<form:errors path="title" />
		<br />
		<form:label path="description">Description:</form:label>
		<form:input path="description" value="${film.description}" />
		<form:errors path="description" />
		<br />
		<form:label path="releaseYear">Release Year:</form:label>
		<form:input path="releaseYear" value="${film.releaseYear}"/>
		<form:errors path="releaseYear" />
		<br />
		<form:label path="languageId">Language ID (1 for English):</form:label>
		<form:input path="languageId" value="${film.languageId}"/>
		<form:errors path="languageId" />
		<br />
		<form:label path="rentalDuration">Rental Duration:</form:label>
		<form:input path="rentalDuration" value="${film.rentalDuration}"/>
		<form:errors path="rentalDuration" />
		<br />
		<form:label path="rentalRate">Rental Rate:</form:label>
		<form:input path="rentalRate" value="${film.rentalRate}" />
		<form:errors path="rentalRate" />
		<br />
		<form:label path="length">Length:</form:label>
		<form:input path="length" value="${film.length}"/>
		<form:errors path="length" />
		<br />
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<form:input path="replacementCost" value="${film.replacementCost}"/>
		<form:errors path="replacementCost" />
		<br />
		<form:label path="rating">Rating:</form:label>
		<form:input path="rating" value="${film.rating}"/>
		<form:errors path="rating" />
		<br />
		<form:label path="specialFeatures">Special Features:</form:label>
		<form:input path="specialFeatures" value="${film.specialFeatures}"/>
		<form:errors path="specialFeatures" />
		<input type="submit" value="Update Film" />
	</form:form>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>