<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Vente application</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value='/ressources/theme/img/favicon.png' />" rel="icon">
    <link href="<c:url value='/ressources/theme/img/apple-touch-icon.png' />" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="<c:url value='/ressources/theme/vendor/aos/aos.css' />" rel="stylesheet">
    <link href="<c:url value='/ressources/theme/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/ressources/theme/vendor/bootstrap-icons/bootstrap-icons.css' />" rel="stylesheet">
    <link href="<c:url value='/ressources/theme/vendor/boxicons/css/boxicons.min.css' />" rel="stylesheet">
    <link href="<c:url value='/ressources/theme/vendor/glightbox/css/glightbox.min.css' />" rel="stylesheet">
    <link href="<c:url value='/ressources/theme/vendor/swiper/swiper-bundle.min.css' />" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="<c:url value='/ressources/theme/css/style.css' />" rel="stylesheet">
</head>
<%  String action = (String) session.getAttribute("action");

%>
<header id="header">
    <div class="d-flex flex-column">

        <div class="profile">
<%--            <img src="assets/img/shop.jpg" alt="">--%>
            <h1 class="text-light"><a href="index.html">Module Computer</a></h1>
            <div class="social-links mt-3 text-center">
                <a href="" class="twitter"><i class="bx bxl-twitter"></i></a>
                <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
                <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
                <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
            </div>
        </div>
        <nav id="navbar" class="nav-menu navbar">
            <ul>
                <% if (action!=null) {%>
                <li><a href="<%= request.getContextPath() %>/list?first=0&last=6" class="nav-link scrollto active"><i class="bx bx-add-to-queue"></i> <span>voir listes article</span></a></li>
                <% if(action.equals("admin")){ %>
                <li><a href="<%= request.getContextPath() %>/list_cree?first=0" class="nav-link scrollto"><i class="bx bx-list-ol"></i> <span>liste creer</span></a></li>
                <% } %>
                <li><a href="<%= request.getContextPath() %>/insert" class="nav-link scrollto"><i class="bx bx-list-ol"></i> <span>inserer</span></a></li>
                <li><a href="<%= request.getContextPath() %>/logout" class="nav-link scrollto active"><i class="bx bx-add-to-queue"></i> <span>se deconnecter</span></a></li>
                <% } %>
            </ul>
        </nav><!-- .nav-menu -->
    </div>
</header>
 <!-- End Header -->

<footer id="footer">
    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong><span>Module Vente</span></strong>
        </div>
        <div class="credits">
            Designed by <a href="https://bootstrapmade.com/">ITU Vente's group</a>
        </div>
    </div>
</footer>