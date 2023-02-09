
<%@ page import="java.util.List" %>
<%@ page import="com.spring.springmvc_v_finale.model.District" %><%--
  Created by IntelliJ IDEA.
  User: aina
  Date: 2023-01-11
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    List<District> districts = (List<District>) request.getAttribute("districts");
%>
<body>
<form action="<%= request.getContextPath() %>/commune " method="post">
    nom <input type="text" name="nom">
    district
    <select name="idDistrict" >
        <%
            for (District district : districts
                 ) { %>
        <option value="<%= district.getId() %>"><%= district.getNom() %></option>
         <%   } %>
    </select>
    <input type="submit" value="Valider">
</form>

</body>
</html>
