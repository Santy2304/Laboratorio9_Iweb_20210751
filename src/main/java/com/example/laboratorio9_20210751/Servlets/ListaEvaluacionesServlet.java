package com.example.laboratorio9_20210751.Servlets;

import com.example.laboratorio9_20210751.Daos.DaoEvaluaciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

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

                break;

            case "editar":

                break;

            case "borrar":

                break;


        }


        request.getRequestDispatcher("ListaEvaluaciones/ListaEvaluaciones.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

