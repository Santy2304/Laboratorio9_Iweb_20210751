<%@ page import="com.example.laboratorio9_20210751.Beans.Usuario" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");%>
<%ArrayList<Usuario> listaDocentes   = (ArrayList<Usuario>) request.getAttribute("listaDocentes");  %>

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
            <h1>Lista de Docentes</h1>
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
        <th class="text-center">Nombre</th>
        <th class="text-center">Correo</th>
        <th class="text-center">Curso</th>
        <th class="text-center">Último Ingreso</th>
        <th class="text-center">Cantidad Ingresos</th>
        <th class="text-center">Registro</th>
        <th class="text-center">Fecha Edición</th>
        <th></th>
        <th></th>

    </tr>


    </thead>
    <tbody>

    <%for (Usuario u : listaDocentes){

    %>
    <tr>
        <td class="text-center" >1</td>
        <td class="text-center">Josh</td>
        <td class="text-center">corred</td>
        <td class="text-center">nose</td>
        <td class="text-center">hoy</td>
        <td class="text-center">joiefre</td>
        <td class="text-center">ferjfo</td>
        <td class="text-center">ifi</td>
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
