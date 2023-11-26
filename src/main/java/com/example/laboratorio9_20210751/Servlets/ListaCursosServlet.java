package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Beans.Evaluaciones;
import com.example.laboratorio9_20210751.Daos.DaoCurso;
import com.example.laboratorio9_20210751.Daos.DaoEvaluaciones;
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

        switch (action){

            case "lista":
                request.setAttribute("listaCursos",daoCurso.listarCursosPorFacultad());
                request.getRequestDispatcher("ListaCursos/ListaCursos.jsp").forward(request, response);
                break;

            case "crear":


                break;

            case "editar":


                break;

            case "borrar":


                break;


        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

