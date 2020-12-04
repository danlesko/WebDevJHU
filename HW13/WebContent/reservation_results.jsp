<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!doctype html>

<html lang="en">
<head>
    <link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
    <meta charset="utf-8">
    <!-- CSS only -->
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <!-- JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>
    <title>The Beartooth Hiking Company (BHC)</title>
    <meta name="description" content="The Beartooth Hiking Company (BHC)">
	<jsp:useBean id="reservationInfo" class="com.dalesko.bhc.reservation.ReservationInfo" scope="session" />    
</head>

<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <a class="navbar-brand" href="#"><img class="header-image" src="images/logo.png" alt="Beartooth"/></a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="./">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="https://www.nps.gov/findapark/index.htm" target="_blank">Find Other Parks</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="https://www.fs.usda.gov/recarea/shoshone/recarea/?recid=80899" target="_blank">More Info</a>
        </li>
    </ul>
</nav>
<div class="main container-fluid" style="margin-top:120px">

    <h5>Find Hikes On Or After A Given Date</h5>
    <form action="ReservationController" method=GET>
		
		<label for="start">Start date:</label>
		<input type="date" id="startDate" name="startDate"
		       min="2000-01-01" max="2025-12-31" value="${reservationInfo.startDate}" required>
		    <br/>   
		
		<input type="SUBMIT">
		<input type="reset">
	</form>
	
    <br />
    <% if(reservationInfo.isSuccess()) { %>
    <table class="table table-bordered table-striped table-responsive-md" >
            <thead>
            <tr>
            	<th>Start Day</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Location</th>
                <th>Guide First</th>
                <th>Guide Last</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reservations}" var="reservation">
		        <tr class="table-info">
		            <td><c:out value="${reservation.startDay}" /></td>
		            <td><c:out value="${reservation.first}" /></td>
		            <td><c:out value="${reservation.last}" /></td>
		            <td><c:out value="${reservation.location}" /></td>
		            <td><c:out value="${reservation.guideFirst}" /></td>
		            <td><c:out value="${reservation.guideLast}" /></td>
		        </tr>
		    </c:forEach>
            </tbody>
        </table>
        <% } else { %>
        <h5>There were no hikes after the selected date</h5>
        <% } %>
    
</div>
</body>
</html>

