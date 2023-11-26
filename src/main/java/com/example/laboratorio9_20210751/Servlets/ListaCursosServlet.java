package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Curso;
import com.example.laboratorio9_20210751.Beans.Evaluaciones;
import com.example.laboratorio9_20210751.Beans.Facultad;
import com.example.laboratorio9_20210751.Beans.Usuario;
import com.example.laboratorio9_20210751.Daos.DaoCurso;
import com.example.laboratorio9_20210751.Daos.DaoCursoHasDocente;
import com.example.laboratorio9_20210751.Daos.DaoEvaluaciones;
import com.example.laboratorio9_20210751.Daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ListaCursosServlet", value = "/ListaCursosServlet")
public class ListaCursosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoCurso daoCurso = new DaoCurso();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        switch (action){

            case "lista":
                request.setAttribute("listaCursos",daoCurso.listarCursosPorFacultad());
                request.getRequestDispatcher("ListaCursos/ListaCursos.jsp").forward(request, response);
                break;

            case "crear":
                String idFacuStr = request.getParameter("idFacultad");
                int idFacultad = Integer.parseInt(idFacuStr);

                request.setAttribute("listaDocentes", daoUsuario.listarDocentes());
                request.setAttribute("idFacultad",idFacultad);
                request.getRequestDispatcher("ListaCursos/CrearCurso.jsp").forward(request,response);


                break;

            case "editar":

                String idCursoStr = request.getParameter("id");

                int idCurso = Integer.parseInt(idCursoStr);

                Curso curso = daoCurso.obtenerCurso(idCurso);

                request.setAttribute("curso",curso);
                request.getRequestDispatcher("ListaCursos/EditarCurso.jsp").forward(request,response);

                break;

            case "borrar":

                String iddCursoStr = request.getParameter("id");
                int iddCurso = Integer.parseInt(iddCursoStr);

                Curso cursoParaBorrar = daoCurso.obtenerCurso(iddCurso);

                if(cursoParaBorrar != null){
                    try {
                        daoCursoHasDocente.borrar(iddCurso);
                        daoCurso.borrar(iddCurso);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
                response.sendRedirect(request.getContextPath() + "/ListaCursosServlet");

                break;
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoCurso daoCurso = new DaoCurso();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        switch (action){

            case "crear":

                String nombreC = request.getParameter("nombre");
                String codigoC = request.getParameter("codigo");
                String idFacultadStr = request.getParameter("idFacultad");
                int idFacultad = Integer.parseInt(idFacultadStr);

                Facultad facultad = new Facultad();
                facultad.setIdFacultad(idFacultad);

                String iddocenteStr = request.getParameter("iddocente");
                Usuario docente = new Usuario();
                docente.setIdUsuario(Integer.parseInt(iddocenteStr));


                Curso cursoC = new Curso();

                cursoC.setNombre(nombreC);
                cursoC.setCodigo(codigoC);
                cursoC.setFacultad(facultad);

                int idcursoC = daoCurso.nuevoId();
                cursoC.setIdCurso(idcursoC);

                try {
                    daoCurso.guardarCurso(cursoC);
                    daoCursoHasDocente.actualizarTabla(Integer.parseInt(iddocenteStr),idcursoC);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                response.sendRedirect(request.getContextPath() + "/ListaCursosServlet");

                break;


            case "editar":

                String idcurso = request.getParameter("idcurso");
                String nombre = request.getParameter("nombre");


                Curso curso = new Curso();

                curso.setIdCurso(Integer.parseInt(idcurso));
                curso.setNombre(nombre);

                try {
                    daoCurso.actualizarCurso(curso);
                    response.sendRedirect(request.getContextPath() + "/ListaCursosServlet");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

        }


    }



}


