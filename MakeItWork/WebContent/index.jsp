<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">
<jsp:useBean id="hikeinfo" class="com.dalesko.hw10.HikeInfo" scope="session"/>
<%@ include file="header.jsp" %>

<body>
<%@ include file="nav.jsp" %>
<div class="main container-fluid" style="margin-top:120px">

    <%@ include file="options.jsp" %>
    <%@ include file="calculator.jsp" %>
</div>
</body>
</html>