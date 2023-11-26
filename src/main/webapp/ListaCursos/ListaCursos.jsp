<%@ page import="com.example.laboratorio9_20210751.Beans.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.laboratorio9_20210751.Beans.Curso" %>
<%@ page import="com.example.laboratorio9_20210751.Daos.DaoFacultadHasDecano" %>
<%@ page import="com.example.laboratorio9_20210751.Daos.DaoCurso" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");%>
<%ArrayList<Curso> listaCursos   = (ArrayList<Curso>) request.getAttribute("listaCursos");  %>

<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>

    <jsp:include page="../Includes/NavBar.jsp"></jsp:include>

<div class="container mt-5">
    <%  DaoFacultadHasDecano daoFacultadHasDecano = new DaoFacultadHasDecano();%>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de Cursos</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/ListaCursosServlet?action=crear&idFacultad=<%=daoFacultadHasDecano.facultadPorIdDecano(usuario.getIdUsuario())%>" class="btn btn-primary">Nuevo Curso</a>
        </div>
    </div>

    <table class="table">
        <thead class="table-dark">

        <tr>
            <th class="text-center">#ID</th>
            <th class="text-center">Codigo</th>
            <th class="text-center">Nombre</th>

            <th class="text-center">Fecha Registro</th>
            <th class="text-center">Fecha Edición</th>
            <th></th>
            <th></th>

        </tr>


        </thead>
        <tbody>
        <%for (Curso c : listaCursos){

            int idFacultadUsuario = daoFacultadHasDecano.facultadPorIdDecano(usuario.getIdUsuario());
            if(c.getFacultad().getIdFacultad()==idFacultadUsuario){
        %>

        <tr>
            <td class="text-center" ><%=c.getIdCurso()%></td>
            <td class="text-center"><%=c.getCodigo()%></td>
            <td class="text-center"><%=c.getNombre()%></td>
            <td class="text-center"><%=c.getFechaRegistro()%></td>
            <td class="text-center"><%=c.getFechaEdicion()%></td>
            <td class="text-center">
                <a href="<%=request.getContextPath()%>/ListaCursosServlet?action=editar&id=<%= c.getIdCurso()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>

            <td class="text-center">
                <% DaoCurso daoCurso = new DaoCurso();
                if (daoCurso.validarCursoBorrar(c.getIdCurso())){
                %>

                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/ListaCursosServlet?action=borrar&id=<%= c.getIdCurso()%>"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>

                <%}else{%>
                Posee evaluaciones
                <%}%>
            </td>


        </tr>
        <%}%>
        <%}%>


        </tbody>
    </table>

</div>
</body>
</html>
