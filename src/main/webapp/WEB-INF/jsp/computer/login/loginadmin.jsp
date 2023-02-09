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
</head>
<body>
<jsp:include page="../../commun/Header.jsp" />
<main id="main" class="main-content position-sticky max-height-vh-100 h-100 border-radius-lg ">
    <form action="<%= request.getContextPath() %>/validate_admin" method="post">
        <p>email:<input type="email" name="email" value="admin@gmail.com" ></p>
        <p>mot de passe:<input type="password" name="mdp" value="admin" ></p>
        <a href="<%= request.getContextPath() %>/">se connecter en tant que client</a>

        <input type="submit" value="log in">
    </form>
</main>
</body>
</html>
