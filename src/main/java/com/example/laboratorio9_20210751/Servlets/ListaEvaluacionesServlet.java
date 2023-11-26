package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Curso;
import com.example.laboratorio9_20210751.Beans.Evaluaciones;
import com.example.laboratorio9_20210751.Daos.DaoEvaluaciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ListaEvaluacionesServlet", value = "/ListaEvaluacionesServlet")
public class ListaEvaluacionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoEvaluaciones daoEvaluaciones = new DaoEvaluaciones();

        switch (action){

            case "lista":
                request.setAttribute("listaEvaluaciones",daoEvaluaciones.listarEvaluaciones());
                request.getRequestDispatcher("ListaEvaluaciones/ListaEvaluaciones.jsp").forward(request, response);
                break;

            case "crear":

                String idcursoStr = request.getParameter("idcurso");
                int idcurso = Integer.parseInt(idcursoStr);

                request.setAttribute("idcurso",idcurso);
                request.getRequestDispatcher("ListaEvaluaciones/CrearEvaluacion.jsp").forward(request,response);

                break;

            case "editar":
                String idEvaluacionStr = request.getParameter("id");

                int idEvaluacion = Integer.parseInt(idEvaluacionStr);

                Evaluaciones evaluacion = daoEvaluaciones.obtenerEvaluacion(idEvaluacion);

                request.setAttribute("evaluacion",evaluacion);
                request.getRequestDispatcher("ListaEvaluaciones/EditarEvaluacion.jsp").forward(request,response);

                break;

            case "borrar":

                String iddEvaluacionStr = request.getParameter("id");
                int iddEvaluacion = Integer.parseInt(iddEvaluacionStr);

                Evaluaciones evaParaBorrar = daoEvaluaciones.obtenerEvaluacion(iddEvaluacion);

                if(evaParaBorrar != null){
                    try {
                        daoEvaluaciones.borrar(iddEvaluacion);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                response.sendRedirect(request.getContextPath() + "/ListaEvaluacionesServlet");

                break;


        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoEvaluaciones daoEvaluaciones = new DaoEvaluaciones();

        switch (action){

            case "crear":

                String nombreC = request.getParameter("nombre");
                String codigoC = request.getParameter("codigo");
                String correoC = request.getParameter("correo");
                String notaStrC = request.getParameter("nota");
                String idCursoStr = request.getParameter("idcurso");
                int notaC = Integer.parseInt(notaStrC);
                int idCurso = Integer.parseInt(idCursoStr);

                Curso curso = new Curso();
                curso.setIdCurso(idCurso);

                Evaluaciones evaluacion = new Evaluaciones();
                evaluacion.setNombreEstudiante(nombreC);
                evaluacion.setCodigoEstudiante(codigoC);
                evaluacion.setCorreoEstudiante(correoC);
                evaluacion.setNota(notaC);
                evaluacion.setCurso(curso);

                try {
                    daoEvaluaciones.guardarEvaluacion(evaluacion);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect(request.getContextPath() + "/ListaEvaluacionesServlet");

                break;

            case "editar":

                String idevaluacion = request.getParameter("idevaluacion");
                String nombre = request.getParameter("nombre");
                String codigo = request.getParameter("codigo");
                String correo = request.getParameter("correo");
                String notaStr = request.getParameter("nota");

                int nota = Integer.parseInt(notaStr);

                Evaluaciones evaluaciones = new Evaluaciones();

                evaluaciones.setIdEvaluaciones(Integer.parseInt(idevaluacion));
                evaluaciones.setNombreEstudiante(nombre);
                evaluaciones.setCodigoEstudiante(codigo);
                evaluaciones.setCorreoEstudiante(correo);
                evaluaciones.setNota(nota);

                try {
                    daoEvaluaciones.actualizarEvaluacion(evaluaciones);
                    response.sendRedirect(request.getContextPath() + "/ListaEvaluacionesServlet");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

        }


    }
}

