<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 28/01/2023
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <link rel="stylesheet" href="ressources/theme/main.css">--%>
    <link rel="stylesheet" href="<c:url  value='/ressources/assets/css/main.css' />">
</head>
<body>
<jsp:include page="../../commun/Header.jsp" />

<%
    String error = (request.getAttribute("error")!=null)? (String) request.getAttribute("error") :"";
%>
<main id="main" class="main-content position-sticky max-height-vh-100 h-100 border-radius-lg ">
    <form action="<%= request.getContextPath() %>/validate_cli" method="post">
        <p>email:<input type="email" name="email" value="my@gmail.com" ></p>
        <p>mot de passe:<input type="password" name="mdp" value="mmm" ></p>
        <h3><%= error%></h3>
        <a href="<%= request.getContextPath() %>/logadmin">se connecter en tant qu'admin</a>
        <input type="submit" value="log in">
    </form>
</main>
</body>
</html>
