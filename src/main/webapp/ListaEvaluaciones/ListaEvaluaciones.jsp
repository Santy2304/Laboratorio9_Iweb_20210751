<%@ page import="com.example.laboratorio9_20210751.Beans.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.laboratorio9_20210751.Beans.Evaluaciones" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");%>
<%ArrayList<Evaluaciones> listaEvaluaciones   = (ArrayList<Evaluaciones>) request.getAttribute("listaEvaluaciones");  %>
<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>



<jsp:include page="../Includes/NavBar.jsp"></jsp:include>


<div class="container mt-5">
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de Evaluaciones</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/ListaDocentesServlet?action=agregar" class="btn btn-primary">Agregar
                nuevo Docente</a>
        </div>
    </div>

    <table class="table">
        <thead class="table-dark">

        <tr>
            <th class="text-center">#ID</th>
            <th class="text-center">Nombre Estudiante</th>
            <th class="text-center">Código Estudiante</th>
            <th class="text-center">Correo Estudiante</th>
            <th class="text-center">Nota</th>
            <th class="text-center">Semestre</th>
            <th class="text-center">Fecha Registro</th>
            <th class="text-center">Fecha Edición</th>
            <th></th>
            <th></th>


        </tr>


        </thead>
        <tbody>

        <%for (Evaluaciones e : listaEvaluaciones){

          //  if (e.getCurso().getIdCurso()==

        %>

        <tr>
            <td class="text-center" ><%=e.getIdEvaluaciones()%></td>
            <td class="text-center"><%=e.getNombreEstudiante()%></td>
            <td class="text-center"><%=e.getCodigoEstudiante()%></td>
            <td class="text-center"><%=e.getCorreoEstudiante()%></td>
            <td class="text-center"><%=e.getNota()%></td>

            <td class="text-center"><%=e.getSemestre().getNombre()%></td>
            <td class="text-center"><%=e.getFechaRegistro()%></td>
            <td class="text-center"><%=e.getFechaEdicion()%></td>
            <td class="text-center">
                <a href="#"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>

            <td class="text-center">
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="#"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>


        </tr>

        <%}%>
        </tbody>
    </table>

</div>
</body>
</html>