<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>

<jsp:useBean id="hikeinfo" class="com.dalesko.hw10.HikeInfo"/>

<html lang="en">
<%@ include file="header.jsp" %>

<body>
<%@ include file="nav.jsp" %>
<div class="main container-fluid" style="margin-top:120px">

    <%@ include file="options.jsp" %>
    <%@ include file="calculator.jsp" %>
    
    <h3>Error: <jsp:getProperty name="hikeinfo" property="errStr" /></h3>
</div>
</body>
</html>