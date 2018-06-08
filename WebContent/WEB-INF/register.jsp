<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- TODO: Add the @taglib for form -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<!-- TODO: Add registration form -->
	<form:form action="register.do" method="POST" modelAttribute="user">
		<form:label path="email">Email:</form:label>
		<form:input path="email" />
		<form:errors path="email" />
		<br />
		<form:label path="firstName">First Name:</form:label>
		<form:input path="firstName" />
		<form:errors path="firstName" />
		<br />
		<form:label path="lastName">Last Name:</form:label>
		<form:input path="lastName" />
		<form:errors path="lastName" />
		<br />
		<form:label path="password">Password:</form:label>
		<form:password path="password" />
		<form:errors path="password" />
		<br />
		<form:label path="age">Age:</form:label>
		<form:input path="age" />
		<form:errors path="age" />
		<input type="submit" value="Register" />
	</form:form>
</body>
</html>