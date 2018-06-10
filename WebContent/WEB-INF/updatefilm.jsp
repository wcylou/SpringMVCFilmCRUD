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
        Title <input type="text" type="text" name = "title" value="${filmupdate.title}"/> <br> 
        Description <input type="text" id ="description" name ="description" type="text" value="${filmupdate.description}" /><br> 
        Release Year <input type="number" name="releaseYear" value="${filmupdate.releaseYear}"/><br> 
        Language Id <input type="number" name="languageId" value="${filmupdate.languageId}"/><br> 
        Rental Duration <input type="number" name="rentalDuration" value="${filmupdate.rentalDuration}"/><br> 
        Rental Rate <input type="number" name="rentalRate" step="any" value="${filmupdate.rentalRate}" /><br> 
        Length <input type="number" name="length" value="${filmupdate.length}"/><br> 
        Replacement Cost <input type="number" name="replacementCost" step="any" value="${filmupdate.replacementCost}"/><br> 
        Rating <input type="text" name="rating" value="${filmupdate.rating}"/><br> 
        Special Features <input type="text" name="specialFeatures" value="${filmupdate.specialFeatures}"/><br> 
        
        <input type="hidden" name="filmid" value="${filmupdate.id}"/>
        <input type="submit" value="Update" />
    </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>