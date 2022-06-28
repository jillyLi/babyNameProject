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
<title>Insert title here</title>
</head>
<body>
	
	 <h1>${thisBabyName.newName }</h1>
	 <h2>( added by ${thisBabyName.owner})</h2>
	 <a href="/dashboard">Go back to dashboard</a>
	 <br/>
	 <h3>
	 	Gender: ${thisBabyName.gender } <br/>
	 	Origin:${thisBabyName.origin}<br/>
	 
	 </h3>
	 <c:if test="${voted==true}">
	 		<h3 style="color:pink">You voted for this name.</h3>
	 </c:if>
	 <c:if test="${voted==false }">
	 		<h3 style="color:purple">You can vote here: <a href="/babyName/upvote/${thisBabyName.id}">Upvote!</a></h3>
	 </c:if>
	 <h4> Meaning: ${thisBabyName.meaning}</h4>
	 
	 
	 <c:if test="${thisUser.userName == thisBabyName.owner }">
	 	<a href="/babyName/edit/${thisBabyName.id}">Edit</a>
	 </c:if>
</body>
</html>