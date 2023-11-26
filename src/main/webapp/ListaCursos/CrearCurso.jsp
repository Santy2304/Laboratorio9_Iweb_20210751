<%@ page import="com.example.laboratorio9_20210751.Beans.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.laboratorio9_20210751.Daos.DaoCursoHasDocente" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%int idFacultad   = (int) request.getAttribute("idFacultad");  %>
<%ArrayList<Usuario> listaDocentes   = (ArrayList<Usuario>) request.getAttribute("listaDocentes");  %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuevo Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
<div class='container'>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Nuevo Curso</h1>
            <hr>
            <form method="POST" action="<%=request.getContextPath()%>/ListaCursosServlet?action=crear">
                <div class="mb-3">
                    <label class="form-label" for="nombre">Nombre</label>
                    <input type="text" class="form-control form-control-sm" id="nombre" name="nombre">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="codigo">Código</label>
                    <input type="text" class="form-control form-control-sm" id="codigo" name="codigo">
                </div>


                <div class="mb-3">
                    <label class="form-label" for="iddocente">Docentes</label>
                    <select name="iddocente" id="iddocente" class="form-select">
                        <%
                            DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
                            for (Usuario usuario : listaDocentes)
                            {
                                if (daoCursoHasDocente.cursoPorIdDocente(usuario.getIdUsuario())==0){

                        %>
                                <option value="<%=usuario.getIdUsuario()%>"><%=usuario.getNombre()%>
                                </option>
                        <%}%>
                        <% }%>
                    </select>
                </div>

                <input type="hidden" name="idFacultad" value="<%=idFacultad%>"/>

                <a href="<%= request.getContextPath()%>/ListaCursosServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
</body>
</html>
