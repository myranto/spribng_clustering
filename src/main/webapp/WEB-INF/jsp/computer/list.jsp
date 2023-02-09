<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.springmvc_v_finale.model.Type_event_detail" %>
<%@ page import="com.spring.springmvc_v_finale.controller.ComputerController" %>
<%@ page import="com.spring.springmvc_v_finale.model.Type" %>
<%@ page import="com.spring.springmvc_v_finale.model.view.Computer_view" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 27/01/2023
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value='/ressources/theme/css/CardStyle.css' />" rel="stylesheet">

</head>
<body>
<jsp:include page="../../jsp/commun/Header.jsp"/>
<main id="main" class="main-content position-sticky max-height-vh-100 h-100 border-radius-lg ">
<%--    <div>Web Application. Passed parameter : th:text="${count}"</div>--%>
    <%
        int pejy = ComputerController.modPage;
        long count_page = (ComputerController.sizeSql == -1) ? (long) request.getAttribute("count") : ComputerController.sizeSql;
        List<Type> lists = (List<Type>) request.getAttribute("type");
        List<Computer_view> list = (List<Computer_view>) request.getAttribute("list");

    %>
    <style>
        .btn-link {
            border: none;
            outline: none;
            background: none;
            cursor: pointer;
            color: #0000EE;
            padding: 0;
            text-decoration: underline;
            font-family: inherit;
            font-size: inherit;
        }
    </style>
    <center>
        <div >
            <h1  class="col-lg-7 mt-5 mt-lg-0 d-flex align-items-stretch" style="text-align: left">Rechercher produit</h1>
            <div class="col-lg-7 mt-5 mt-lg-0 d-flex align-items-stretch" style="text-align: left">

            <form class="form-control" action="<%= request.getContextPath() %>/search?first=0&last=6&action=search" method="post">
<%--        <p>type event :<select class="form-control" onchange="onSelected()" id="type" name="idtype">--%>
<%--            <option value="0">choix type</option>--%>
<%--                <% for (Type e:lists) {  %>--%>
<%--            <option value="<%=e.getId()%>"><%= e.getNom() %>            </option>--%>
<%--        <% } %>--%>
<%--        </p>--%>
        <p>mot cle : <input class="form-control" type="text" placeholder="rechercher mot cle" name="keyword"/></p>
<%--        <p>prix :<input class="form-control"  type="number" name="prix"/></p>--%>
<%--        <p>date de sortie de l'ordinateur: <input class="form-control" type="date" name="date_sortie"/></p>--%>
<%--        <p>date de debut : <input class="form-control" type="date" name="date_debut"/></p>--%>
<%--        <p>date de fin : <input class="form-control" type="date" id="date_fin" value="1000-01-02" hidden name="date_fin"/></p>--%>
<%--        <p>remise :<input class="form-control" type="number" id="remise" value="0" hidden name="remise"/>%</p>--%>
        <input type="submit" class="btn btn-outline-info" value="rechercher">
    </form>
            </div>
        </div>
    </center>

    <div class="container">
        <% for (Computer_view v:list) { %>
        <div class="card">
            <div class="card-header">
                <img alt="rover" src="<%= v.getImage()%>"/>
            </div>
            <div class="card-body">
                <span class="tag tag-teal"><%= v.getDate_debut() %> :
                    <% if (v.getIdtype()==2){ %>
                        <strong style="color: blue"> <%= v.getName() %></strong>
                    <% } else{ %>
                        <strong style="color: red"> <%= v.getName() %></strong>
                    <% } %>
                </span>
                <h4>
                    <%= v.getLieu() %>
                </h4>
                <p>
                    <%= v.getDescription() %>
                </p>
                <p>creer le :<strong><%= v.getDate_creation() %></strong></p>
                <p>publier le :<strong><%= v.getDate_sortie() %></strong></p>
                <div class="user">
                    <div class="user-info">
                        <% if (v.getIdtype()==2){ %>
                            <h5>fin :  <small><%= v.getDate_fin() %></small></h5>


                        <%  } %>
                    </div>
                </div>
            </div>
        </div>
        <% } %>

    </div>
    <ul>
        <% int first = 0;
            int last = pejy;
            for (int i = 0; i < count_page; i++) { %>
        <% if (ComputerController.sizeSql == -1) {%>
        <li><a href="<%= request.getContextPath() %>/list?first=<%= first %>&last=<%= last %>"><%= i + 1%>
        </a></li>
        <% } else { %>
        <form action="<%= request.getContextPath() %>/search?first=<%= first %>&last=<%= last %>&action=see"
              method="post">
            <li>
                <button type="submit" class="btn-link"><%= i + 1%>
                </button>
            </li>
        </form>
        <%--            <li><a href="<%= request.getContextPath() %>/search?first=<%= first %>&last=<%= last %>"><%= i + 1%></a></li>--%>
        <% }
            first = last;
//            pejy=first+pejy;
            last = pejy +first;
        } %>
    </ul>
</main>
<script>
    function onSelected() {
        let type = document.getElementById("type").value;
        let date_fin = document.getElementById("date_fin");

        let remise = document.getElementById("remise");
        if (type === "2") {
            date_fin.hidden = false;
            remise.hidden = false;
        } else {
            date_fin.hidden = true;
            remise.hidden = true;
        }
    }
</script>
</body>
</html>
