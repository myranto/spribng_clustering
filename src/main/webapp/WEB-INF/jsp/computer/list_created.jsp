<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.springmvc_v_finale.controller.ComputerController" %>
<%@ page import="com.spring.springmvc_v_finale.model.view.Computer_view" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 01/02/2023
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value='/ressources/theme/css/CardStyle.css' />" rel="stylesheet">

</head>
<style>
    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    }

    /* Modal Content */
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto; /* 15% from the top and centered */
        padding: 20px;
        border: 1px solid #888;
        width: 80%; /* Could be more or less, depending on screen size */
    }

    /* The Close Button */
    .close {
        color: #aaa;
        background-color: red;
        float: right;
        font-size: 28px;
        width: 50px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }

</style>
<body>
<%
    int pejy = ComputerController.modcree;
    long count_page =  (long) request.getAttribute("count");
    List<Computer_view> list = (List<Computer_view>) request.getAttribute("list");
%>
<jsp:include page="../../jsp/commun/Header.jsp"/>

<main id="main" class="main-content position-sticky max-height-vh-100 h-100 border-radius-lg ">
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

            <div class="user">
                <div class="user-info">
<%--                    <h3><a id="btn" href="<%= request.getContextPath() %>/publier/<%= v.getId_cp() %>">publier</a></h3>--%>
                    <h3><button class="btn btn-info" id="btn" onclick="onPubliate(<%= v.getId_cp() %>)" type="button">publier</button></h3>
                    <% if (v.getIdtype()==2){ %>
                    <h5>fin :   <small><%= v.getDate_fin() %></small></h5>


                    <%  } %>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>
    <center>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form method="get" action="<%= request.getContextPath() %>/publiates" >
                <input type="number" name="id" id="id_cp" hidden>
                    date publication<input type="date" name="date_sortie">
                    <input type="time" name="time">
                <br/>
                <input type="submit" class="btn btn-info" value="publier">
            </form>
        </div>
    </div>
    </center>
<ul>
    <% int first = 0;
        int last = pejy;
        for (int i = 0; i < count_page; i++) { %>
    <li><a href="<%= request.getContextPath() %>/list_cree?first=<%= first %>"><%= i + 1%>
    </a></li>
    <%
        first = last;
        last = pejy +first;
    } %>
</ul>
</main>
<script>
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("btn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    function onPubliate(id_cp){
        document.getElementById('id_cp').value = id_cp;
        // alert(document.getElementById('id_cp').value)
        modal.style.display = "block";

    }
    // btn.onclick = function() {
    //     modal.style.display = "block";
    // }

    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
<%--<th><a href="<%= request.getContextPath() %>/publier/<%= v.getId_cp() %>">publier</a></th>--%>
