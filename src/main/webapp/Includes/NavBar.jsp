<%@ page import="com.example.laboratorio9_20210751.Beans.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" style="height: 6%;">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item mx-2">
                    <!--<a class="nav-link active" aria-current="page" href="#">Menú</a>-->
                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/ListaCursosServlet">Cursos</a>
                </li>
                <li class="nav-item mx-2">
                    <a class="nav-link active" href="<%=request.getContextPath()%>/ListaDocentesServlet">Docentes</a>
                </li>
                <li class="nav-item mx-2">
                    <!--<a class="nav-link disabled" aria-disabled="true">Disabled</a>-->
                    <a class="nav-link active" href="<%=request.getContextPath()%>/ListaEvaluacionesServlet">Evaluaciones</a>
                </li>

            </ul>

            <button type="button" class="btn btn-danger" onclick="cerrarSesion()">Cerrar Sesión</button>

            <script>
                function cerrarSesion() {
                    // Redirige a tu enlace deseado
                    window.location.href = "<%=request.getContextPath()%>/?action=logout";
                }
            </script>
        </div>
    </div>
</nav>
