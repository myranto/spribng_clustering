<%@ page import="java.util.List" %>
<%@ page import="com.spring.springmvc_v_finale.model.Type" %><%--
  Created by IntelliJ IDEA.
  User: 6805
  Date: 27/01/2023
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../commun/Header.jsp" />
<%
    List<Type> list = (List<Type>) request.getAttribute("list");
%>
<main id="main"  class="main-content position-sticky max-height-vh-100 h-100 border-radius-lg ">
    <center>
        <div >
            <h1  class="col-lg-7 mt-5 mt-lg-0 d-flex align-items-stretch" style="text-align: left">Ajout produit</h1>
            <div class="col-lg-7 mt-5 mt-lg-0 d-flex align-items-stretch" style="text-align: left">
                <form action="<%= request.getContextPath() %>/validate_insert"  class="form-control" method="post" enctype="multipart/form-data" >
                    <p>   titre <input type="text" name="name" /></p>
                    <p><input  class="form-control" hidden value="200" type="number" name="prix" /></p>
                    <p>description : <input class="form-control"  type="text" name="description" /></p>
                    <p>lieu : <input class="form-control"  type="text" name="lieu" /></p>
<%--                    <p>date de creation de l'article : <input type="datetime-local" value="" name="date_creation" /></p>--%>
                    <input type="file" class="form-control" id="selectImage"   name="images"  >
                    <input type="hidden" name="image" id="imageCode">
                    <p>type:
                        input:<select class="form-control"  onchange="onSelected()" id="type" name="idtype">
                            <% for (Type e:list) {  %>
                            <option value="<%=e.getId()%>"><%= e.getNom() %></option>
                            <%    }    %>
                        </select>
                    </p>
                    <p>date de debut : <input class="form-control"  type="date" name="date_debut" /></p>
                    <p>date de fin : <input  class="form-control" type="date" id="date_fin" value="1000-01-02" hidden name="date_fin" /></p>
                    <p><input class="form-control"  type="number" id="remise" value="0" hidden name="remise" />%</p>

                    <input type="submit" class="btn btn-outline-info" value="valider">
                </form>
            </div>
        </div>
    </center>

</main>
<script>
    function onSelected(){
        let type=document.getElementById("type").value;
        let date_fin = document.getElementById("date_fin");

        let remise = document.getElementById("remise");
        if (type==="2")
        {
            date_fin.hidden = false;
            // remise.hidden=false;
        }else{
            date_fin.hidden = true;
            remise.hidden = true;
        }
    }
    const input = document.getElementById("selectImage");
    const imageCode = document.getElementById("imageCode");
    const convertBase64 = (file) => {
        return new Promise((resolve, reject) => {
            const fileReader = new FileReader();
            fileReader.readAsDataURL(file);

            fileReader.onload = () => {
                resolve(fileReader.result);
            };

            fileReader.onerror = (error) => {
                reject(error);
            };
        });
    };
    const uploadImage = async (event) => {
        const file = event.target.files[0];
        const base64 = await convertBase64(file);
        imageCode.value = base64;
        console.log(imageCode.value);
    };
    input.addEventListener("change", (e) => {
        uploadImage(e);
    });
</script>
</body>
</html>
