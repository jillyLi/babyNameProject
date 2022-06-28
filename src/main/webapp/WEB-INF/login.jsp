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
<title>Login And Registration</title>
</head>
<body>
	<h1>Joy Bundler Names</h1>
	
	<div class="container">
		<div class="leftContainer">
			<h1>Register</h1>
			<form:form action="/register" method="post" modelAttribute="newUser">
				<p>
			        <form:label path="userName">Name:</form:label>
			        <form:errors  path="userName" class="error"/>
			        <form:input path="userName"/>
			    </p>
			    <p>
			        <form:label path="email">Email:</form:label>
			        <form:errors  path="email" class="error"/>
			        <form:input path="email"/>
			    </p>
			    <p>
			        <form:label path="password">Password:</form:label>
			        <form:errors  path="password" class="error"/>
			        <form:input path="password"/>
			    </p>
			    <p>
			        <form:label path="confirm">Confirm PW:</form:label>
			        <form:errors  path="confirm" class="error"/>
			        <form:input path="confirm"/>
			    </p>
			    <button>Register</button>
			</form:form>
		
		</div>
		<div class="rightContainer">
		<h1>Log in</h1>
			<form:form action="/login" method="post" modelAttribute="newLogin">
				
			    <p>
			        <form:label path="email">Email:</form:label>
			        <form:errors  path="email" class="error"/>
			        <form:input path="email"/>
			    </p>
			    <p>
			        <form:label path="password">Password:</form:label>
			        <form:errors  path="password" class="error"/>
			        <form:input path="password"/>
			    </p>
			    <button>Log In</button>
			</form:form>
		
		
		
		</div>
	
	
	</div>
</body>
</html>