<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- TODO: Add the @taglib for form -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Film</title>
</head>
<body>
	<form:form action="addfilm.do" method="POST" modelAttribute="user">
		<form:label path="title">Title:</form:label>
		<form:input path="title" />
		<form:errors path="title" />
		<br />
		<form:label path="description">Description:</form:label>
		<form:input path="description" />
		<form:errors path="description" />
		<br />
		<form:label path="releaseYear">Release Year:</form:label>
		<form:input path="releaseYear" />
		<form:errors path="releaseYear" />
		<br />
		<form:label path="languageID">Language ID (1 for English):</form:label>
		<form:password path="languageID" />
		<form:errors path="languageID" />
		<br />
		<form:label path="rentalDuration">Rental Duration:</form:label>
		<form:input path="rentalDuration" />
		<form:errors path="rentalDuration" />
		<br />
		<form:label path="rentalRate">Rental Rate:</form:label>
		<form:input path="rentalRate" />
		<form:errors path="rentalRate" />
		<br />
		<form:label path="length">Length:</form:label>
		<form:input path="length" />
		<form:errors path="length" />
		<br />
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<form:input path="replacementCost" />
		<form:errors path="replacementCost" />
		<br />
		<form:label path="rating">Rating:</form:label>
		<form:input path="rating" />
		<form:errors path="rating" />
		<br />
		<form:label path="specialFeatures">Special Features:</form:label>
		<form:input path="specialFeatures" />
		<form:errors path="specialFeatures" />
		<input type="submit" value="Add Film" />
	</form:form>
</body>
</html>