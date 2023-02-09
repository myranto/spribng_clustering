
<%@ page import="java.util.List" %>
<%@ page import="com.spring.springmvc_v_finale.model.Commune" %><%--
  Created by IntelliJ IDEA.
  User: aina
  Date: 2023-01-11
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste commune</title>
</head>
<%
  List<Commune> communes = (List<Commune>) request.getAttribute("communes");
%>
<body>

<table>
  <tr>
    <td>Id</td>
    <td>District id</td>
    <td>Nom</td>
  </tr>
  <% for (Commune commune : communes) { %>
    <tr>
      <td><%= commune.getId() %></td>
      <td><%= commune.getIdDistrict() %></td>
      <td><%= commune.getNom() %></td>
    </tr>
  <%  } %>
</table>
</body>
</html>
