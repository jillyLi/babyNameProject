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
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome, ${thisUser.userName}. Here are some name suggestions..</h1>
	<h4><a href="/logout">log out</a></h4>
	<a href="/babyName/new">+ new name</a>
	
	
	<h2>Baby Names</h2>
	
	
		<h3>======= Not Voted Names ========</h3>
	
		<c:forEach var="eachName" items="${notVotedNames }">
				
			<div class="nameContainer">
				<a href="/babyName/upvote/${eachName.id}">upvote!</a>&emsp;&emsp;
				<span><a href="/babyName/${eachName.id}">${eachName.newName }</a></span>&emsp;&emsp;
				<span>${eachName.gender }</span>&emsp;&emsp;
				<span>Origin: ${eachName.origin }</span>&emsp;&emsp;
				<span>Votes: ${eachName.votes }</span>
			</div>
			<hr/>
			
			
				
		</c:forEach>
		<h3>======= Voted Names ========</h3>
		<c:forEach var="eachName" items="${votedNames }">
				
			<div class="nameContainer">
				<span>voted!</span>&emsp;&emsp;
				<span><a href="/babyName/${eachName.id}">${eachName.newName }</a></span>&emsp;&emsp;
				<span>${eachName.gender }</span>&emsp;&emsp;
				<span>Origin: ${eachName.origin }</span>&emsp;&emsp;
				<span>Votes: ${eachName.votes }</span>
			</div>
			<hr/>
			
			
				
		</c:forEach>
			
				
				
	
</body>
</html>