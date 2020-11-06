<%-- 
    Document   : login
    Created on : Dec 10, 2007, 8:47:37 PM
    Updated on : July 12, 2019
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
    <head>
		<meta charset="ISO-8859-1">
        <title>Login JSP Page</title>
        <jsp:useBean id="login" class="com.dalesko.hw10.Login" scope="session" />
    </head>
    <body>
        <h2>Welcome to MVC Model 2 demo</h2>
        <% 
        if (login.getName() != null) {
        %>
        Incorrect password, please try again <br />
        <%    
        }
        %>
        <form action="http://localhost:8080/MVC2/Controller" method=GET>
            userid: <input type="TEXT" name="login"> <br />
            password:  <input type="password" name="password"> <br />
            <input type="SUBMIT">
        </form>
    </body>
</html>
