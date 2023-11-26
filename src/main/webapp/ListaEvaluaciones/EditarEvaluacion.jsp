<%@ page import="com.example.laboratorio9_20210751.Beans.Evaluaciones" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%Evaluaciones evaluacion   = (Evaluaciones) request.getAttribute("evaluacion");  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Evaluación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<div class='container'>

    <div class="row mb-4">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Editar Evaluación</h1>
            <hr>
            <form method="POST" action="<%=request.getContextPath()%>/ListaEvaluacionesServlet?action=editar">
                <input type="hidden" name="idevaluacion" value="<%= evaluacion.getIdEvaluaciones()%>"/>
                <div class="mb-3">
                    <label class="form-label" for="nombre">Nombre</label>
                    <input type="text" class="form-control form-control-sm" id="nombre" name="nombre"
                           value="<%= evaluacion.getNombreEstudiante() == null ? "" : evaluacion.getNombreEstudiante()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="codigo">Código</label>
                    <input type="text" class="form-control form-control-sm" id="codigo" name="codigo"
                           value="<%= evaluacion.getCodigoEstudiante() == null ? "" : evaluacion.getCodigoEstudiante()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="correo">Correo</label>
                    <input type="text" class="form-control form-control-sm" id="correo" name="correo"
                           value="<%= evaluacion.getCorreoEstudiante() == null ? "" : evaluacion.getCorreoEstudiante()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="nota">Nota</label>
                    <input type="text" class="form-control form-control-sm" id="nota" name="nota"
                           value="<%=evaluacion.getNota()%>">
                </div>

                <a href="<%= request.getContextPath()%>/ListaEvaluacionesServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

</body>
</html>
