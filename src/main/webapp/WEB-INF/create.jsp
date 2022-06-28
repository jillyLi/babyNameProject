<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<title>Create</title>
</head>
<body>
	<h1>Add a name!</h1>
	<form:form action="/babyName/new" method="post" modelAttribute="newBabyName">
	<p>
        <form:label path="newName">New Name:</form:label>
        <form:errors  path="newName" class="error"/>
        <form:input path="newName"/>
    </p>
     <p>
			<form:label path="gender"> Typical Gender:</form:label>
			<form:errors path="gender" class="error"/>
			<form:select path="gender">
					<form:option value="" path="gender">
						<c:out value="-"/>
					</form:option>
					<form:option value="Female" path="gender">
						<c:out value="Female"/>
					</form:option>
					<form:option value="Male" path="gender">
						<c:out value="Male"/>
					</form:option>
					<form:option value="Neutral" path="gender">
						<c:out value="Neutral"/>
					</form:option>
				
			</form:select>
		
		</p>
    <p>
        <form:label path="origin">Origin:</form:label>
        <form:errors  path="origin" class="error"/>
        <form:input path="origin"/>
    </p>
    <p>
        <form:label path="meaning">Meaning:</form:label>
        <form:errors  path="meaning" class="error"/>
        <form:input path="meaning"/>
    </p>
   
	 <form:input type="hidden" path="votes" value="0"/>
	 <form:input type="hidden" path="owner" value="${thisUser.userName }"/>
	<%--  <form:input type="hidden" path="users" value="${thisUser.id}"/> --%>
	
	<a href="/dashboard">Cancel</a>
	<button>Submit</button>
	</form:form>
</body>
</html>