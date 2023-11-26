<%@ page import="com.example.laboratorio9_20210751.Beans.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.laboratorio9_20210751.Daos.DaoCursoHasDocente" %>
<%@ page import="com.example.laboratorio9_20210751.Daos.DaoCurso" %>
<%@ page import="com.example.laboratorio9_20210751.Daos.DaoFacultadHasDecano" %>
<%@ page import="com.example.laboratorio9_20210751.Beans.Curso" %><%--
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

    <%DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        DaoCurso daoCurso = new DaoCurso();
        DaoFacultadHasDecano daoFacultadHasDecano = new DaoFacultadHasDecano();
    %>

    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de Docentes</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/ListaDocentesServlet?action=agregar" class="btn btn-primary">Nuevo Docente</a>
        </div>
    </div>

<table class="table">
    <thead class="table-dark">

    <tr>
        <th class="text-center">#ID</th>
        <th class="text-center">Nombre</th>
        <th class="text-center">Correo</th>
        <th class="text-center">Último Ingreso</th>
        <th class="text-center">Cantidad Ingresos</th>
        <th class="text-center">Registro</th>
        <th class="text-center">Fecha Edición</th>
        <th></th>
        <th></th>

    </tr>


    </thead>
    <tbody>

    <%int facuDecano = daoFacultadHasDecano.facultadPorIdDecano(usuario.getIdUsuario());
        for (Usuario u : listaDocentes){
        int idcurso = daoCursoHasDocente.cursoPorIdDocente(u.getIdUsuario());
        int facultad_del_curso = daoCurso.obtenerIdFacultad(idcurso);

        if (idcurso==0 ||  facuDecano==facultad_del_curso){

    %>
    <tr>
        <td class="text-center" ><%=u.getIdUsuario()%></td>
        <td class="text-center"><%=u.getNombre()%></td>
        <td class="text-center"><%=u.getCorreo()%></td>
        <td class="text-center"><%=u.getUltimoIngreso()%></td>
        <td class="text-center"><%=u.getCantIngresos()%></td>
        <td class="text-center"><%=u.getFechaRegistro()%></td>
        <td class="text-center"><%=u.getFechaEdicion()%></td>
        <td class="text-center">
            <a href="<%=request.getContextPath()%>/ListaDocentesServlet?action=editar&id=<%= u.getIdUsuario()%>"
               type="button" class="btn btn-primary">
                <i class="bi bi-pencil-square"></i>
            </a>
        </td>

        <td class="text-center">
            <a onclick="return confirm('¿Estas seguro de borrar?');"
               href="<%=request.getContextPath()%>/ListaDocentesServlet?borrar=editar&id=<%= u.getIdUsuario()%>"
               type="button" class="btn btn-danger">
                <i class="bi bi-trash"></i>
            </a>
        </td>


    </tr>

    <%}%>
    <%}%>


    </tbody>
</table>

</div>
</body>
</html>
