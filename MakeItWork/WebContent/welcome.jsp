<%-- 
    Document   : menu
    Created on : Dec 10, 2007, 8:48:11 PM
	Updated on : July 12, 2019
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
    <head>
		<meta charset="ISO-8859-1">
        <title>Succesful login!</title>
        <jsp:useBean id="hikeInfo" class="com.dalesko.hw10.HikeInfo" scope="request" />        
    </head>
    <body>
        <h2>You are logged in as user <jsp:getProperty name="hikeInfo" property="startDate" /></h2>
    </body>
</html>
