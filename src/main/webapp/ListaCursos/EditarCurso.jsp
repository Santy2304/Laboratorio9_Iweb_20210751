<%@ page import="com.example.laboratorio9_20210751.Beans.Curso" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 26/11/2023
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%Curso curso   = (Curso) request.getAttribute("curso");  %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<div class='container'>

    <div class="row mb-4">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Editar Curso</h1>
            <hr>
            <form method="POST" action="<%=request.getContextPath()%>/ListaCursosServlet?action=editar">
                <input type="hidden" name="idcurso" value="<%= curso.getIdCurso()%>"/>
                <div class="mb-3">
                    <label class="form-label" for="nombre">Nombre</label>
                    <input type="text" class="form-control form-control-sm" id="nombre" name="nombre"
                           value="<%= curso.getNombre() == null ? "" : curso.getNombre()%>">
                </div>


                <a href="<%= request.getContextPath()%>/ListaCursosServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

</body>
</html>
