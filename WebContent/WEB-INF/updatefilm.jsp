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
  <link rel="stylesheet" type="text/css" href="intro.css">
<title>Update Film</title>
</head>
<body>
<div class = "container">
    <form action="updatefilmdetails.do" method="POST">
       <span class = "headings">Film ID: </span><input type= "text" name="filmid" value="${filmupdate.id}" readonly/><br>
        <span class = "headings">Title: </span> <input type="text" type="text" name = "title" value="${filmupdate.title}"/> <br> 
        <span class = "headings">Description: </span> <input type="text" id ="description" name ="description" type="text" value="${filmupdate.description}" /><br> 
        <span class = "headings">Release Year: </span><input type="number" name="releaseYear" value="${filmupdate.releaseYear}"/><br> 
        <span class = "headings">Language ID: </span> <input type="number" name="languageId" value="${filmupdate.languageId}"/><br> 
         <span class = "headings">Rental Duration: </span>  <input type="number" name="rentalDuration" value="${filmupdate.rentalDuration}"/><br> 
        <span class = "headings">Rental Rate: </span><input type="number" name="rentalRate" step="any" value="${filmupdate.rentalRate}" /><br> 
        <span class = "headings">Length: </span> <input type="number" name="length" value="${filmupdate.length}"/><br> 
        <span class = "headings">Replacement Cost: </span><input type="number" name="replacementCost" step="any" value="${filmupdate.replacementCost}"/><br> 
        <span class = "headings">Rating: </span>  <input type="text" name="rating" value="${filmupdate.rating}"/><br> 
        <span class = "headings">Special Feature: </span>   <input type="text" name="specialFeatures" value="${filmupdate.specialFeatures}"/><br> 
        <input type="hidden" name="filmid" value="${filmupdate.id}"/>
        <input type="submit" value="Update" />
    </form>
      <form:form action="intro.html">
        <input type="submit" value="Back" />
    </form:form>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>